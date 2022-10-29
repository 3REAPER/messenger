package ru.pervukhin.messanger.retrofit

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import ru.pervukhin.messanger.domain.Chat

interface ChatService {

    @GET("/chat/user/{id}")
    suspend fun getAllChatByUser(@Path("id") id: Int) : Response<List<Chat>>

    @DELETE("/chat/{chatId}/user/{userId}")
    suspend fun deleteUserFromChat(@Path("chatId") chatId: Int, @Path("userId") userId: Int)
}