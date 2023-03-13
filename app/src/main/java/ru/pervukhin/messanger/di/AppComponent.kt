package ru.pervukhin.messanger.di

import dagger.Component
import retrofit2.Retrofit
import ru.pervukhin.messanger.repository.Repository
import ru.pervukhin.messanger.retrofit.ChatService
import ru.pervukhin.messanger.retrofit.MessageService
import ru.pervukhin.messanger.retrofit.ProfileService
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface AppComponent {

    fun inject(repository: Repository)

    fun retrofit(): Retrofit
    fun profileService(): ProfileService
    fun messageService(): MessageService
    fun chatService(): ChatService
}