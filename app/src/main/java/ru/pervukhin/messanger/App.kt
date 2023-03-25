package ru.pervukhin.messanger

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pervukhin.messanger.di.AppComponent
import ru.pervukhin.messanger.di.BaseModule
import ru.pervukhin.messanger.di.DaggerAppComponent
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.retrofit.ProfileService

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }
    lateinit var user: Profile
    lateinit var chat: Chat

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .baseModule(BaseModule(this))
            .build()

    }
}