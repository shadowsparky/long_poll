package ru.shadowsparky.longpoll

data class LongPollDataResponse(
    val ts: Int,
    val updates: List<ArrayList<Int>>
)