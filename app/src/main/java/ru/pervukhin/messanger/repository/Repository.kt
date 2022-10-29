package ru.pervukhin.messanger.repository

import retrofit2.Response
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.retrofit.RetrofitInstance
import ru.pervukhin.messanger.retrofit.model.ResultPasswordEmail

class Repository {

    suspend fun isRightPasswordAndLogin(login:String,password:String): Response<ResultPasswordEmail>{
        return RetrofitInstance.apiProfile.isRightLoginAndPassword(login,password)
    }

    suspend fun getUser(id: Int): Response<Profile>{
        return RetrofitInstance.apiProfile.getUserById(id)
    }

    suspend fun getAllChatByUser(id: Int): Response<List<Chat>>{
        return RetrofitInstance.apiChat.getAllChatByUser(id)
    }

    suspend fun getAllMessageChatId(id: Int): Response<List<Message>>{
        return RetrofitInstance.apiMessage.getAllMessageChatId(id)
    }

    suspend fun sendMessage(message: Message, chatId: Int){
        RetrofitInstance.apiMessage.sendMessage(message.message, message.time, message.isEdit, message.profile.id, message.conditionSend, chatId)
    }

    suspend fun createProfile(profile: Profile) : Response<String>{
        return RetrofitInstance.apiProfile.registrationProfile(profile.name, profile.login, profile.number, profile.password)
    }

    suspend fun deleteUserFromChat(chatId: Int, userId: Int){
        RetrofitInstance.apiChat.deleteUserFromChat(chatId, userId)
    }
}