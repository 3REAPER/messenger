package ru.pervukhin.messanger.domain

import com.google.gson.annotations.SerializedName
import java.util.concurrent.locks.Condition

class Message {
    var id = 0
        private set
    var message: String
    var time: String
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

    constructor(id: Int, message: String, time: String, isEdit: Boolean, profile: Profile, conditionSend: Int, chatId: Int) {
        this.id = id
        this.message = message
        this.time = time
        this.isEdit = isEdit
        this.profile = profile
        this.conditionSend = conditionSend
        this.chatId = chatId
    }

    constructor(message: String, time: String, isEdit: Boolean, profile: Profile, conditionSend: Int, chatId: Int) {
        this.message = message
        this.time = time
        this.isEdit = isEdit
        this.profile = profile
        this.conditionSend = conditionSend
        this.chatId = chatId
    }
}