package ru.pervukhin.messanger

import android.app.Application
import ru.pervukhin.messanger.di.AppComponent
import ru.pervukhin.messanger.di.BaseModule
import ru.pervukhin.messanger.di.DaggerAppComponent
import ru.pervukhin.messanger.di.RoomModule
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Profile

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }
    var user: Profile? = null
    var chat: Chat? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .baseModule(BaseModule(this))
            .roomModule(RoomModule(this))
            .build()

    }
}