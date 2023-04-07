package ru.pervukhin.messanger.data.room

import androidx.room.*

@Dao
interface ProfileDao {

    @Insert
    suspend fun insert(profileEntity: ProfileEntity)

    @Update
    suspend fun update(profileEntity: ProfileEntity)

    @Delete
    suspend fun delete(profileEntity: ProfileEntity)

    @Query("SELECT * FROM profileEntity ")
    suspend fun getAll(): List<ProfileEntity>

    @Query("SELECT * FROM profileEntity WHERE id=:id")
    suspend fun getById(id: Int): ProfileEntity?
}