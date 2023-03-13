package ru.pervukhin.messanger.fragments.chatList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.repository.Repository
import java.util.*

class ChatListViewModel : ViewModel() {
    val repository = Repository()
    val liveData: MutableLiveData<Response<List<Chat>>> = MutableLiveData()
    val timer = Timer()

    fun getAllChatByUserId(id: Int){
        viewModelScope.launch {
            liveData.value = repository.getAllChatByUser(id)
        }
    }
}