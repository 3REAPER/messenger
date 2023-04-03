package ru.pervukhin.messanger.domain


class GroupChat : Chat {
    var name: String
    var description: String
    var isPrivate: Boolean
    var admin: Profile

    constructor(id: Int, usersId: List<Profile>, messages: List<Message>, isGroup: Boolean, name: String, description: String, isPrivate: Boolean, admin: Profile) : super(id, usersId, messages, isGroup) {
        this.name = name
        this.description = description
        this.isPrivate = isPrivate
        this.admin = admin
    }

    constructor(usersId: List<Profile>, messages: List<Message>, isGroup: Boolean, name: String, description: String, isPrivate: Boolean, admin: Profile) : super(usersId, messages, isGroup) {
        this.name = name
        this.description = description
        this.isPrivate = isPrivate
        this.admin = admin
    }

    override fun toString(): String {
        return description
    }
}
