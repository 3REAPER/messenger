package ru.pervukhin.messanger.fragments.contactList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.repository.Repository

class ContactListViewModel : ViewModel() {
    val liveData: MutableLiveData<List<Profile>> = MutableLiveData()
    val repository = Repository()

    fun getProfileByNumber(json: List<Map<String,String>>){
        viewModelScope.launch {
            liveData.value = repository.getProfileByNumber(json).body()
        }
    }

}