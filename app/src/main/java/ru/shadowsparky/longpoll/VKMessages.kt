package ru.shadowsparky.longpoll


class VKMessages (
    val mid: Int,
    val date: Long,
    val out: Int,
    val uid: Int,
    val read_state: Int,
    val title: String,
    val body: String
)