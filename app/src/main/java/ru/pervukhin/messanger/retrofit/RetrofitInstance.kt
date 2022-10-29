package ru.pervukhin.messanger.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val retrofit by lazy {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder().build()

        Retrofit.Builder()
            .baseUrl("http://192.168.0.199:8080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))// Перевести результат запроса через Gson
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    }



    val  apiProfile: ProfileService by lazy {
        retrofit.create(ProfileService::class.java)
    }

    val  apiChat: ChatService by lazy {
        retrofit.create(ChatService::class.java)
    }

    val apiMessage: MessageService by lazy {
        retrofit.create(MessageService::class.java)
    }
}

