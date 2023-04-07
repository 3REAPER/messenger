package ru.pervukhin.messanger.fragments.chatList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.repository.RepositoryRetrofit
import java.util.*

class ChatListViewModel : ViewModel() {
    val liveData: MutableLiveData<List<Chat>?> = MutableLiveData()
    private val repository = RepositoryRetrofit()
    private var timer = Timer()

    fun getAllChatByUserId(id: Int){
        viewModelScope.launch {
            liveData.value = repository.getAllChatByUser(id)
        }
    }

    fun search(name: String){
        viewModelScope.launch {
            liveData.value = repository.search(name)
        }
    }

    fun startTimerChat(id: Int) {
        timer.schedule(object : TimerTask() {
            override fun run() {
                viewModelScope.launch {
                    val chats = repository.getAllChatByUser(id)
                    if (chats.isNotEmpty() && (liveData.value != chats)) {
                        liveData.value = chats

                    }
                }
            }


        }, 1, 1L * 1000)


    }

    fun stopTimer(){
        timer.cancel()
        timer = Timer()
    }
}