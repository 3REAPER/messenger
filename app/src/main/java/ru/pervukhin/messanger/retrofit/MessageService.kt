package ru.pervukhin.messanger.retrofit

import retrofit2.Response
import retrofit2.http.*
import ru.pervukhin.messanger.domain.Message

interface MessageService {

    @GET("/message/chat/{id}")
    suspend fun getAllMessageChatId(@Path("id") id: Int) : Response<List<Message>>

    @POST("/message")
    suspend fun sendMessage(@Query("messageStr") message: String, @Query("isEdit") isEdit: Boolean, @Query("authorId") authorId: Int, @Query("conditionSend") conditionSend: Int , @Query("chatId") chatId: Int)

    @GET("/message/unread/{profileId}")
    suspend fun getUnread(@Path("profileId") profileId: Int): Response<List<Message>>

    @POST("/message/{id}")
    suspend fun updateMessage(@Path("id") id: Int, @Query("messageStr") message: String, @Query("isEdit") isEdit: Boolean, @Query("authorId") authorId: Int, @Query("conditionSend") conditionSend: Int , @Query("chatId") chatId: Int)
}