package ru.pervukhin.messanger.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProfileEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getProfileDao(): ProfileDao
}