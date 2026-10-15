package com.example.aichatbotgroq

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val messages = mutableListOf<ChatMessage>()
    private lateinit var adapter: MessageAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var tvThinking: TextView
    private lateinit var btnSend: ImageButton
    private lateinit var btnMic: ImageButton

    private val client by lazy { GroqClient(BuildConfig.GROQ_API_KEY) }
    private var isLoading = false

    // Receives the text the user spoke and sends it to the AI
    private val speechLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val spoken = result.data
                ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                ?.firstOrNull()
            if (!spoken.isNullOrBlank()) sendMessage(spoken)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect the views from activity_main.xml
        recycler = findViewById(R.id.recyclerMessages)
        etMessage = findViewById(R.id.etMessage)
        tvThinking = findViewById(R.id.tvThinking)
        btnSend = findViewById(R.id.btnSend)
        btnMic = findViewById(R.id.btnMic)

        adapter = MessageAdapter(messages)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        addMessage(ChatMessage("Hi! I'm your AI assistant. Ask me anything.", isUser = false))

        btnSend.setOnClickListener { sendFromTextBox() }
        btnMic.setOnClickListener { startListening() }
        findViewById<ImageButton>(R.id.btnClear).setOnClickListener { clearChat() }

        etMessage.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendFromTextBox()
                true
            } else false
        }
    }

    private fun sendFromTextBox() {
        val text = etMessage.text.toString()
        etMessage.text.clear()
        sendMessage(text)
    }

    private fun sendMessage(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty() || isLoading) return

        addMessage(ChatMessage(trimmed, isUser = true))
        setLoading(true)

        lifecycleScope.launch {
            try {
                val reply = client.chat(messages.toList())
                addMessage(ChatMessage(reply, isUser = false))
            } catch (e: Exception) {
                addMessage(
                    ChatMessage("Error: ${e.message ?: "Something went wrong"}", isUser = false, isError = true)
                )
            } finally {
                setLoading(false)
            }
        }
    }

    private fun addMessage(message: ChatMessage) {
        messages.add(message)
        adapter.notifyItemInserted(messages.size - 1)
        recycler.scrollToPosition(messages.size - 1)
    }

    private fun clearChat() {
        messages.clear()
        adapter.notifyDataSetChanged()
        addMessage(ChatMessage("Chat cleared. How can I help?", isUser = false))
    }

    private fun setLoading(loading: Boolean) {
        isLoading = loading
        tvThinking.visibility = if (loading) View.VISIBLE else View.GONE
        btnSend.isEnabled = !loading
        btnMic.isEnabled = !loading
    }

    private fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to the AI…")
        }
        try {
            speechLauncher.launch(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Voice input isn't available on this device", Toast.LENGTH_SHORT).show()
        }
    }
}