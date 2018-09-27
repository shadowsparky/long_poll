package ru.shadowsparky.longpoll

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Log.DEBUG
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    var retrofit: Retrofit? = null
    var long_poll: Retrofit? = null
    var sub: Disposable? = null

    val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    companion object {
        val TOKEN = "access_token"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.println(DEBUG, "MAIN_TAG", "test")
        setContentView(R.layout.activity_main)
        retrofit = Retrofit.Builder()
                .baseUrl("https://api.vk.com/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        sendRequest()

    }

    fun sendRequest() = GlobalScope.async(Dispatchers.Default) {
        val it = retrofit!!.create(VKLongPollApi::class.java).getServer(TOKEN).blockingFirst()
        Log.println(DEBUG, "MAIN_TAG", "${it.raw().request().url()}")
        val response = it.body()!!.response
        val path = response.server.split('/')
        initLongPoll(path[0], client)
        val created = long_poll!!.create(VKLongPollApi::class.java)
        while (true) {
            try {
                loggy("${path[1]}, ${response.key}, ${response.ts}")
                val it2 = created.getLongPoll(path[1], response.key, response.ts).blockingFirst()
                Log.println(DEBUG, "RESULT", "${it2.raw().request().url()}")
                Log.println(DEBUG, "RESULT", "size ${it2.body()!!.updates}")
                Log.println(DEBUG, "RESULT", "body ${it2.body()!!.ts}")
                loggy("handled")
            } catch (e: Exception) {
                loggy("EXCEPTION: $e")
            }
        }
    }

    fun loggy(text: String) {
        Log.println(DEBUG, "MAIN_TAG", text);
    }

    fun initLongPoll(path: String, client: OkHttpClient) {
        long_poll = Retrofit.Builder().baseUrl("https://$path/")
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
    }
}
