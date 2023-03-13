package ru.pervukhin.messanger.fragments.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.repository.Repository

class DialogViewModel : ViewModel() {
    val liveData: MutableLiveData<Response<List<Message>>> = MutableLiveData()
    private val repository = Repository()

    fun getAllMessageChatId(id: Int){
        viewModelScope.launch {
            liveData.value = repository.getAllMessageChatId(id)
        }
    }

    fun sendMessage(message: Message){
        viewModelScope.launch{
            repository.sendMessage(message)
        }
    }
}