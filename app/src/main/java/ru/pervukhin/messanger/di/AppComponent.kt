package ru.pervukhin.messanger.di

import dagger.Component
import retrofit2.Retrofit
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MessageNotificationService
import ru.pervukhin.messanger.adapter.ChatListAdapter
import ru.pervukhin.messanger.adapter.DialogAdapter
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.fragments.chatList.ChatListFragment
import ru.pervukhin.messanger.fragments.dialog.DialogFragment
import ru.pervukhin.messanger.repository.Repository
import ru.pervukhin.messanger.data.retrofit.ChatService
import ru.pervukhin.messanger.data.retrofit.MessageService
import ru.pervukhin.messanger.data.retrofit.ProfileService
import ru.pervukhin.messanger.data.room.AppDataBase
import ru.pervukhin.messanger.data.room.ProfileDao
import ru.pervukhin.messanger.fragments.contactList.ContactListViewModel
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class, BaseModule::class])
@Singleton
interface AppComponent {

    fun inject(repository: Repository)
    fun inject(dialogAdapter: DialogAdapter)
    fun inject(dialogFragment: DialogFragment)
    fun inject(chatListAdapter: ChatListAdapter)
    fun inject(chatListFragment: ChatListFragment)
    fun inject(messageNotificationService: MessageNotificationService)
    fun inject(contactListViewModel: ContactListViewModel)

    fun retrofit(): Retrofit
    fun profileService(): ProfileService
    fun messageService(): MessageService
    fun chatService(): ChatService

    fun user(): Profile
    fun chat(): Chat
    fun app(): App

    fun appDataBase(): AppDataBase
    fun profileDao(): ProfileDao


}