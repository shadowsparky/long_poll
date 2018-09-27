package ru.shadowsparky.longpoll

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VKLongPollApi {
    @GET("method/messages.getLongPollServer")
    fun getServer(
            @Query("access_token") token: String,
            @Query("need_pts") pts: Boolean = true,
            @Query("lp_version") lp_version: Int = 2,
            @Query("v") version: Double = 5.85
    ) : Observable<Response<GetLongPollResponse>>

    @GET("method/messages.getLongPollHistory")
    fun getLongPoll(
//            @Path("method") method: String,
            @Query("key") key: String,
            @Query("ts") ts: Int,
            @Query("access_token") token: String,
            @Query("preview_length") preview_length: Int = 20,
            @Query("fields") fields: String = "photo,photo_medium_rec,sex,online,screen_name",
            @Query("events_limit") events_limit: Int = 1000,
            @Query("msgs_limit") msgs_limit: Int = 200,
//            @Query("wait") wait: Int = 25,
//            @Query("mode") mode: Int = 2,
            @Query("version") version: Double = 5.85
//            @Query("act") act: String = "a_check"
    ) : Observable<Response<GetLongPollHistoryResponse>>
}