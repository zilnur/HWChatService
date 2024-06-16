package ru.netology.chatService

import java.util.UUID

data class Message(
    val id: Int,
    val autorId: Int,
    var text: String,
    var isReadIt: Boolean
)
