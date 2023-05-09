package ru.pervukhin.messanger.data.retrofit

import retrofit2.Response
import retrofit2.http.*
import ru.pervukhin.messanger.domain.Message

interface MessageService {

    @GET("/message/chat/{id}")
    suspend fun getAllMessageChatId(@Path("id") id: Int, @Query("login") login: String, @Query("password") password: String) : Response<List<Message>>

    @POST("/message")
    suspend fun sendMessage(@Body message: Message, @Query("login") login: String, @Query("password") password: String)

    @GET("/message/unread/{profileId}")
    suspend fun getUnread(@Path("profileId") profileId: Int, @Query("login") login: String, @Query("password") password: String): Response<List<Message>>

    @POST("/message/{id}")
    suspend fun updateMessage(@Body message: Message, @Query("login") login: String, @Query("password") password: String)
}