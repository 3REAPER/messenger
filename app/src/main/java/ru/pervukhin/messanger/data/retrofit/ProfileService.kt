package ru.pervukhin.messanger.data.retrofit

import retrofit2.Response
import retrofit2.http.*
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.data.retrofit.model.ResultPasswordEmail

interface ProfileService {

    @GET("/profile/{login}/{password}")
    suspend fun isRightLoginAndPassword(@Path("login") login: String, @Path("password") password: String) : Response<ResultPasswordEmail>

    @POST("/profile")
    suspend fun registrationProfile(@Query("name") name: String, @Query("login") login: String, @Query("number") number: String, @Query("password") password: String) : Response<String>

    @POST("/profile/numbers")
    @JvmSuppressWildcards
    suspend fun getProfileByNumber(@Body json: List<Map<String,String>>): Response<List<Profile>>
}