package ru.pervukhin.messanger.data.retrofit

import retrofit2.Response
import retrofit2.http.*
import ru.pervukhin.messanger.data.retrofit.model.ChatDto

interface ChatService {

    @GET("/chat/user/{id}")
    suspend fun getAllChatByUser(@Path("id") id: Int) : Response<List<ChatDto>>

    @DELETE("/chat/{chatId}/user/{userId}")
    suspend fun deleteUserFromChat(@Path("chatId") chatId: Int, @Path("userId") userId: Int)

    @GET("chat/user/{myId}/{userId}")
    suspend fun getChatByUsers(@Path("myId") myId: Int,@Path("userId") userId: Int): Response<ChatDto>

    @GET("chat/private/{name}")
    suspend fun search(@Path("name") name: String): Response<List<ChatDto>>

    @POST("chat")
    suspend fun createGroupChat(@Body chatDto: ChatDto)
}