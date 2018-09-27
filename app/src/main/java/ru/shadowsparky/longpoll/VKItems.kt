package ru.shadowsparky.longpoll

data class VKItems(
    val conversation: VKConversation?,
    val last_message: VKMessage?
)