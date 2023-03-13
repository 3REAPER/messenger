package ru.pervukhin.messanger.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.pervukhin.messanger.retrofit.ChatService
import ru.pervukhin.messanger.retrofit.MessageService
import ru.pervukhin.messanger.retrofit.ProfileService
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileService{
        return retrofit.create(ProfileService::class.java)
    }

    @Provides
    @Singleton
    fun provideMessageService(retrofit: Retrofit): MessageService{
        return retrofit.create(MessageService::class.java)
    }

    @Provides
    @Singleton
    fun provideChatService(retrofit: Retrofit): ChatService{
        return retrofit.create(ChatService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.199:8080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))// Перевести результат запроса через Gson
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}