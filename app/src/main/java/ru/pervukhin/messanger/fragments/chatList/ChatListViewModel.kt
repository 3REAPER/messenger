package ru.pervukhin.messanger.fragments.chatList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.repository.Repository
import java.util.*

class ChatListViewModel : ViewModel() {
    val liveData: MutableLiveData<List<Chat>?> = MutableLiveData()
    private val repository = Repository()
    private val timer = Timer()

    fun getAllChatByUserId(id: Int){
        viewModelScope.launch {
            liveData.value = repository.getAllChatByUser(id).body()?.let { renameDuoChat(it, id) }
        }
    }

    private fun renameDuoChat(list: List<Chat>, userId: Int): List<Chat>{
        var result = list
        result.forEach{ chat->
            if (chat.usersId.size == 2){
                chat.usersId.forEach{
                    if (it.id != userId){
                        result = result.minus(chat)
                        chat.name = it.name
                        result = result.plus(chat)
                    }
                }
            }
        }
        return result
    }

    fun startTimerChat(id: Int) {
        viewModelScope.launch {
            timer.schedule(object : TimerTask() {
                override fun run() {
                    viewModelScope.launch {
                        val chats = repository.getAllChatByUser(id).body()?.let { renameDuoChat(it, id) }
                        if (chats != null) {
                            if (chats.isNotEmpty() && (liveData.value != chats)) {
                                liveData.value = chats

                            }
                        }
                    }

                }
            }, 1, 1L * 1000)

        }
    }

    fun stopTimer(){
        timer.cancel()
    }
}