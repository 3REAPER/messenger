package ru.pervukhin.messanger.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.repository.Repository

class SearchViewModel : ViewModel() {
    val liveData: MutableLiveData<List<Chat>> = MutableLiveData()
    private val repository = Repository()

    fun search(name: String){
        viewModelScope.launch {
            liveData.value = repository.search(name)
        }
    }
}