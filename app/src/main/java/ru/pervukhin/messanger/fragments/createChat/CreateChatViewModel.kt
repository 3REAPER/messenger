package ru.pervukhin.messanger.fragments.createChat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.GroupChat
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.repository.RepositoryRetrofit
import ru.pervukhin.messanger.repository.RepositoryRoom

class CreateChatViewModel : ViewModel() {
    val liveDate: MutableLiveData<List<Profile>> = MutableLiveData()
    private val repositoryRoom = RepositoryRoom()
    private val repositoryRetrofit = RepositoryRetrofit()

    fun getProfile(){
        viewModelScope.launch {
            liveDate.value = repositoryRoom.getAllProfile()
        }
    }

    fun createChat(groupChat: GroupChat){
        viewModelScope.launch {
            repositoryRetrofit.createGroupChat(groupChat)
        }
    }
}