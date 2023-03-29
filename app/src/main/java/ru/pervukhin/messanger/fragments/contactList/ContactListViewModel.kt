package ru.pervukhin.messanger.fragments.contactList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.repository.Repository

class ContactListViewModel : ViewModel() {
    val liveDataProfile: MutableLiveData<List<Profile>> = MutableLiveData()
    val liveDataChat: MutableLiveData<Chat> = MutableLiveData()
    val repository = Repository()

    fun getProfileByNumber(json: List<Map<String,String>>){
        viewModelScope.launch {
            liveDataProfile.value = repository.getProfileByNumber(json).body()
        }
    }

    fun getChat(myId: Int, userId: Int) {
        viewModelScope.launch(){
            liveDataChat.value = repository.getChat(myId,userId).body()
        }
    }

}