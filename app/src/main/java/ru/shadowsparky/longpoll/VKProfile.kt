package ru.shadowsparky.longpoll

data class VKProfile(
    val id: Int?,
    val first_name: String?,
    val last_name: String?,
    val sex: Int?,
    val screen_name: String?,
    val photo_50: String?,
    val photo_100: String?,
    val online: Int?,
    val online_mobile: Int?
)