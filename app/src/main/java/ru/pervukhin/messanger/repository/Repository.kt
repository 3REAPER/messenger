package ru.pervukhin.messanger.repository

import retrofit2.Response
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.data.retrofit.ChatService
import ru.pervukhin.messanger.data.retrofit.MessageService
import ru.pervukhin.messanger.data.retrofit.ProfileService
import ru.pervukhin.messanger.data.retrofit.model.ResultPasswordEmail
import javax.inject.Inject

class Repository {
    @Inject
    lateinit var profileService: ProfileService
    @Inject
    lateinit var messageService: MessageService
    @Inject
    lateinit var chatService: ChatService
    init {
        App.appComponent.inject(this)
    }


    suspend fun isRightPasswordAndLogin(login:String,password:String): Response<ResultPasswordEmail>{
        return profileService.isRightLoginAndPassword(login,password)
    }

    suspend fun getUser(id: Int): Response<Profile>{
        return profileService.getUserById(id)
    }

    suspend fun getAllChatByUser(id: Int): Response<List<Chat>>{
        return chatService.getAllChatByUser(id)
    }

    suspend fun getAllMessageChatId(id: Int): Response<List<Message>>{
        return messageService.getAllMessageChatId(id)
    }

    suspend fun sendMessage(message: Message){
        messageService.sendMessage(message.message, message.isEdit, message.profile.id, message.conditionSend, message.chatId)
    }

    suspend fun getUnread(profileId: Int): Response<List<Message>>{
        return messageService.getUnread(profileId)
    }

    suspend fun updateMessage(message: Message){
        messageService.updateMessage(message.id,message.message, message.isEdit, message.profile.id, message.conditionSend, message.chatId)
    }

    suspend fun createProfile(profile: Profile) : Response<String>{
        return profileService.registrationProfile(profile.name, profile.login, profile.number, profile.password)
    }

    suspend fun deleteUserFromChat(chatId: Int, userId: Int){
        chatService.deleteUserFromChat(chatId, userId)
    }

    suspend fun getProfileByNumber(json :List<Map<String,String>>): Response<List<Profile>>{
        return profileService.getProfileByNumber(json)
    }
}