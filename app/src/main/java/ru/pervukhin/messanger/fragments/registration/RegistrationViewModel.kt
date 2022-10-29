package ru.pervukhin.messanger.fragments.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.repository.Repository

class RegistrationViewModel : ViewModel() {
    val liveData : MutableLiveData<Response<String>> = MutableLiveData()
    val repository = Repository()

    fun registration(profile: Profile){
        viewModelScope.launch {
            liveData.value = repository.createProfile(profile)
        }
    }
}