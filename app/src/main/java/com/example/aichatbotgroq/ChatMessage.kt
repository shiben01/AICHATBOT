package com.example.aichatbotgroq

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val isError: Boolean = false
)