package ru.shadowsparky.longpoll

data class VKLongPoll(
    val key: String,
    val server: String,
    val ts: Int,
    val pts: Int
)