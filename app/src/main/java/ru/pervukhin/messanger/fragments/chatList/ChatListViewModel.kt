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
            liveData.value = repository.getAllChatByUser(id).body()
        }
    }

    fun startTimerChat(id: Int) {
        viewModelScope.launch {
            timer.schedule(object : TimerTask() {
                override fun run() {
                    viewModelScope.launch {
                        val chats = repository.getAllChatByUser(id).body()
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