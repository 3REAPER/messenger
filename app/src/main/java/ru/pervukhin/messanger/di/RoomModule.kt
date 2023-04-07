package ru.pervukhin.messanger.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.pervukhin.messanger.data.room.AppDataBase
import ru.pervukhin.messanger.data.room.ProfileDao
import javax.inject.Singleton

@Module
class RoomModule(val context: Context) {

    @Singleton
    @Provides
    fun provideAppDataBase(): AppDataBase{
        return Room.databaseBuilder(context, AppDataBase::class.java,"database.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideProfileDao(appDataBase: AppDataBase): ProfileDao{
        return appDataBase.getProfileDao()
    }
}