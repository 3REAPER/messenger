package ru.pervukhin.messanger.domain

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class Message {
    var id = 0
        private set
    var message: String
    var time: Date
    var isEdit: Boolean
    var authorId: Profile
    var conditionSend: List<ConditionSend>
    var chatId: Int

    constructor(id: Int, message: String, time: Date, isEdit: Boolean, authorId: Profile, conditionSend: List<ConditionSend>, chatId: Int) {
        this.id = id
        this.message = message
        this.time = time
        this.isEdit = isEdit
        this.authorId = authorId
        this.conditionSend = conditionSend
        this.chatId = chatId
    }

    constructor(message: String, time: Date, isEdit: Boolean, authorId: Profile, conditionSend: List<ConditionSend>, chatId: Int) {
        this.message = message
        this.time = time
        this.isEdit = isEdit
        this.authorId = authorId
        this.conditionSend = conditionSend
        this.chatId = chatId
    }

    fun getDateString(): String{
        return SimpleDateFormat("dd MMMM yyyy").format(time)
    }

    fun getTimeString(): String{
        return  SimpleDateFormat("k:mm").format(time)
    }

    override fun equals(other: Any?): Boolean {
        return other is Message && (id == other.id && message == other.message && time == other.time && isEdit == other.isEdit && authorId == other.authorId && conditionSend == other.conditionSend && chatId == other.chatId )
    }

    override fun hashCode(): Int {
        var result = id
        result += message.hashCode()
        result += time.hashCode()
        result += isEdit.hashCode()
        result += authorId.hashCode()
        result += conditionSend.hashCode()
        result += chatId
        return result
    }
}