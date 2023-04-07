package ru.pervukhin.messanger.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val number: String
    )