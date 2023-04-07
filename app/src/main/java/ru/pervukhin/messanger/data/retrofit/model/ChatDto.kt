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
    var name: String?,
    var description: String?,
    var isPrivate: Boolean?,
    var admin: Profile?
) {
    var id = id
        private set

    companion object {
        fun toDomainObject(chatDto: ChatDto): Chat{
            return if (chatDto.isGroup){
                GroupChat(chatDto.id, chatDto.usersId, chatDto.messages, chatDto.isGroup, chatDto.name!!, chatDto.description!!, chatDto.isPrivate!!, chatDto.admin!!)
            }else{
                Chat(chatDto.id, chatDto.usersId, chatDto.messages, chatDto.isGroup)
            }
        }

        fun toDto(chat: Chat): ChatDto{
            return if (chat.isGroup){
                val groupChat = chat as GroupChat
                ChatDto(groupChat.id, groupChat.usersId, groupChat.messages, groupChat.isGroup, groupChat.name, groupChat.description, groupChat.isPrivate, groupChat.admin)
            }else{
                ChatDto(chat.id, chat.usersId, chat.messages, chat.isGroup, null, null,null ,null)
            }
        }

        fun toDomainObject(list: List<ChatDto>): List<Chat> {
            var result: List<Chat> = listOf()
            list.forEach {
                result = result.plus(toDomainObject(it))
            }
            return result
        }
    }

}