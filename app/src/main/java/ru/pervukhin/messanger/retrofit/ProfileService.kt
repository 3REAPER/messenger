package ru.pervukhin.messanger.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.retrofit.model.ResultPasswordEmail

interface ProfileService {

    @GET("/profile/{login}/{password}")
    suspend fun isRightLoginAndPassword(@Path("login") login: String, @Path("password") password: String) : Response<ResultPasswordEmail>

    @GET("/profile/{id}")
    suspend fun getUserById(@Path("id") id: Int) : Response<Profile>

    @POST("/profile")
    suspend fun registrationProfile(@Query("name") name: String, @Query("login") login: String, @Query("number") number: String, @Query("password") password: String) : Response<String>
}