package ru.pervukhin.messanger.domain

open class Chat {
    var id = 0
        private set
    var usersId: List<Profile>
    var messages: List<Message>
    var isGroup: Boolean

    constructor(id: Int, usersId: List<Profile>, messages: List<Message>, isGroup: Boolean) {
        this.id = id
        this.usersId = usersId
        this.messages = messages
        this.isGroup = isGroup
    }

    constructor(usersId: List<Profile>, messages: List<Message>, isGroup: Boolean) {
        this.usersId = usersId
        this.messages = messages
        this.isGroup = isGroup
    }

    override fun equals(other: Any?): Boolean {
        return other is Chat && (id == other.id && usersId == other.usersId && messages == other.messages && other.isGroup == isGroup)
    }

    override fun hashCode(): Int {
        var result = id
        result += usersId.hashCode()
        result += messages.hashCode()
        result += isGroup.hashCode()
        return result
    }

    override fun toString(): String {
        return isGroup.toString()
    }
}