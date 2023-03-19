package ru.pervukhin.messanger.domain

class Chat {
    var id = 0
        private set
    var name: String
    var description: String
    var isPrivate: String
    var admin: Profile
    var usersId: List<Profile>
    var messages: List<Message>

    constructor(id: Int, name: String, description: String, isPrivate: String, admin: Profile, usersId: List<Profile>, messages: List<Message>) {
        this.id = id
        this.name = name
        this.description = description
        this.isPrivate = isPrivate
        this.admin = admin
        this.usersId = usersId
        this.messages = messages
    }

    constructor(name: String, description: String, isPrivate: String, admin: Profile, usersId: List<Profile>, messages: List<Message>) {
        this.name = name
        this.description = description
        this.isPrivate = isPrivate
        this.admin = admin
        this.usersId = usersId
        this.messages = messages
    }

    override fun equals(other: Any?): Boolean {
        return other is Chat && (id == other.id && name == other.name && description == other.description && isPrivate == other.isPrivate && admin == other.admin && usersId == other.usersId && messages == other.messages)
    }

    override fun toString(): String {
        return messages.get(messages.size).time.toString()
    }
}