package ru.pervukhin.messanger.fragments.chatSettings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.pervukhin.messanger.repository.Repository

class ChatSettingsViewModel : ViewModel() {
    val repository = Repository()
    val liveData: MutableLiveData<Response<String>> = MutableLiveData()

    fun deleteUserFromChat(chatId: Int, userId: Int){
        viewModelScope.launch {
            repository.deleteUserFromChat(chatId, userId)
        }
    }
}