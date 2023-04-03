package ru.pervukhin.messanger.data.retrofit.model

import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.GroupChat
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.domain.Profile

class ChatDto(
    id: Int,
    var usersId: List<Profile>,
    var messages: List<Message>,
    var isGroup: Boolean,
    var name: String,
    var description: String,
    var isPrivate: Boolean,
    var admin: Profile
) {
    var id = id
        private set

    fun toDomainObject(): Chat{
        return if (isGroup){
            GroupChat(id, usersId, messages, isGroup, name, description, isPrivate, admin)
        }else{
            Chat(id, usersId, messages, isGroup)
        }
    }

    companion object {
        fun toDomainObject(list: List<ChatDto>): List<Chat> {
            var result: List<Chat> = listOf()
            list.forEach {
                result = result.plus(it.toDomainObject())
            }
            return result
        }
    }

}