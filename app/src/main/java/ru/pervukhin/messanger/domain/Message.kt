package ru.pervukhin.messanger.domain

import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.locks.Condition

class Message {
    var id = 0
        private set
    var message: String
    var time: Date
    var isEdit: Boolean
    @SerializedName("author")
    var profile: Profile
    var conditionSend: Int
    var chatId: Int

    companion object {
        const val CREATE = 0
        const val SEND = 1
        const val READ = 2
    }

    constructor(id: Int, message: String, time: Date, isEdit: Boolean, profile: Profile, conditionSend: Int, chatId: Int) {
        this.id = id
        this.message = message
        this.time = time
        this.isEdit = isEdit
        this.profile = profile
        this.conditionSend = conditionSend
        this.chatId = chatId
    }

    constructor(message: String, time: Date, isEdit: Boolean, profile: Profile, conditionSend: Int, chatId: Int) {
        this.message = message
        this.time = time
        this.isEdit = isEdit
        this.profile = profile
        this.conditionSend = conditionSend
        this.chatId = chatId
    }

    fun getDateString(): String{
        val arrayDate = time.toString().split(" ")
        return arrayDate[2].toInt().toString() +" " +arrayDate[1] +" " +arrayDate[5]
    }

    fun getTimeString(): String{
        val arrayTime = time.toString().split(" ")[3].split(":")
        return arrayTime[0] +":" +arrayTime[1]
    }

    override fun equals(other: Any?): Boolean {
        return other is Message && (id == other.id && message == other.message && time == other.time && isEdit == other.isEdit && profile == other.profile && conditionSend == other.conditionSend && chatId == other.chatId )
    }

    override fun toString(): String {
        return id.toString()
    }
}