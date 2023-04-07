package ru.pervukhin.messanger.fragments.chatSettings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.pervukhin.messanger.repository.RepositoryRetrofit

class ChatSettingsViewModel : ViewModel() {
    val repository = RepositoryRetrofit()
    val liveData: MutableLiveData<Response<String>> = MutableLiveData()

    fun deleteUserFromChat(chatId: Int, userId: Int){
        viewModelScope.launch {
            repository.deleteUserFromChat(chatId, userId)
        }
    }
}