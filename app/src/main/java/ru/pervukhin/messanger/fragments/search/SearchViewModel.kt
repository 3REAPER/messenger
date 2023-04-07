package ru.pervukhin.messanger.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.repository.RepositoryRetrofit

class SearchViewModel : ViewModel() {
    val liveData: MutableLiveData<List<Chat>> = MutableLiveData()
    private val repository = RepositoryRetrofit()

    fun search(name: String){
        viewModelScope.launch {
            liveData.value = repository.search(name)
        }
    }
}