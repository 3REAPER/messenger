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

    constructor(id: Int, message: String, time: String, isEdit: Boolean, profile: Profile, conditionSend: Int) {
        this.id = id
        this.message = message
        this.time = time
        this.isEdit = isEdit
        this.profile = profile
        this.conditionSend = conditionSend
    }

    constructor(message: String, time: String, isEdit: Boolean, profile: Profile, conditionSend: Int) {
        this.message = message
        this.time = time
        this.isEdit = isEdit
        this.profile = profile
        this.conditionSend = conditionSend
    }

    companion object {
        const val CREATE = 0
        const val READ = 2
        const val SEND = 1
    }

}