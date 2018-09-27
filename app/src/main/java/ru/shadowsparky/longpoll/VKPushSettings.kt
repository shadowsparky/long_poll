package ru.shadowsparky.longpoll

data class VKPushSettings(
    val disabled_until: Int?,
    val disabled_forever: Boolean?,
    val no_sound: Boolean?
)