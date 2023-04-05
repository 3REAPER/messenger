package ru.pervukhin.messanger.di

import dagger.Module
import dagger.Provides
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Profile

@Module
class BaseModule(val app: App) {

    @Provides
    fun provideUser(): Profile{
        return app.user!!
    }

    @Provides
    fun provideChat(): Chat{
        return app.chat!!
    }

    @Provides
    fun provideApp(): App{
        return app
    }
}