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
//            @Query("access_token") token: String,
            @Query("v") version: Double = 5.85
       //     @Query("extended") extended: Boolean = true
    ) : Observable<Response<GetLongPollResponse>>

    @GET("/{method}")
    fun getLongPoll(
            @Path("method") method: String,
            @Query("key") key: String,
            @Query("ts") ts: Int,
            @Query("wait") wait: Int = 25,
            @Query("mode") mode: Int = 2,
            @Query("version") version: Int = 2,
            @Query("act") act: String = "a_check"
    ) : Observable<Response<LongPollDataResponse>>
}