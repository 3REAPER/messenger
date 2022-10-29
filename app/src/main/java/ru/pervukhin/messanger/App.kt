package ru.pervukhin.messanger

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.retrofit.ProfileService

class App : Application() {

    private var profileApi: ProfileService? = null
    lateinit var user: Profile
    lateinit var chat: Chat

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }

    private fun configureRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.198:8080/")
            .addConverterFactory(GsonConverterFactory.create())// Перевести результат запроса через Gson
            .build()

        profileApi = retrofit.create(ru.pervukhin.messanger.retrofit.ProfileService::class.java)
    }

    public fun getApi(): ProfileService?{
        return profileApi
    }
}