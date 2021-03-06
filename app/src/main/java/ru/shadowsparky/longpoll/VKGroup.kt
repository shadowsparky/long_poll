package ru.shadowsparky.longpoll

data class VKGroup(
    val id: Int?,
    val name: String?,
    val screen_name: String?,
    val is_closed: Int?,
    val deactivated: String?,
    val is_admin: Int?,
    val admin_level: Int?,
    val is_member: Int?,
    val invited_by: Int?,
    val type: String?,
    val photo_50: String?,
    val photo_100: String?,
    val photo_200: String?
)