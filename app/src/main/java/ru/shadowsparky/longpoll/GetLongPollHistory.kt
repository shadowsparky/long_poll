package ru.shadowsparky.longpoll

data class GetLongPollHistory(
    val history: ArrayList<List<Int>>,
    val messages: ArrayList<Any>,
    val profiles: ArrayList<VKProfile>,
    val new_pts: Int
)