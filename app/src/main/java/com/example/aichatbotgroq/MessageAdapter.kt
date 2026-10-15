package com.example.aichatbotgroq

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/** Shows each ChatMessage as a bubble: user on the right, AI on the left. */
class MessageAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<MessageAdapter.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.tvMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val msg = messages[position]
        val ctx = holder.itemView.context
        holder.text.text = msg.text

        val params = holder.text.layoutParams as FrameLayout.LayoutParams
        when {
            msg.isError -> {
                holder.text.setBackgroundResource(R.drawable.bubble_error)
                holder.text.setTextColor(ContextCompat.getColor(ctx, R.color.error_text))
                params.gravity = Gravity.START
            }
            msg.isUser -> {
                holder.text.setBackgroundResource(R.drawable.bubble_user)
                holder.text.setTextColor(ContextCompat.getColor(ctx, R.color.white))
                params.gravity = Gravity.END
            }
            else -> {
                holder.text.setBackgroundResource(R.drawable.bubble_ai)
                holder.text.setTextColor(ContextCompat.getColor(ctx, R.color.ai_text))
                params.gravity = Gravity.START
            }
        }
        holder.text.layoutParams = params
    }
}