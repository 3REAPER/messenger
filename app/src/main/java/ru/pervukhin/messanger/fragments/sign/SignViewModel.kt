package ru.pervukhin.messanger.fragments.sign

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.repository.Repository
import ru.pervukhin.messanger.retrofit.model.ResultPasswordEmail

class SignViewModel: ViewModel() {
    var repository = Repository()
    private val profileLiveData: MutableLiveData<Response<Profile>> = MutableLiveData()
    val resultLiveData: MutableLiveData<Response<ResultPasswordEmail>> = MutableLiveData()

    fun sign(login:String,password:String){
        viewModelScope.launch {
            resultLiveData.value = repository.isRightPasswordAndLogin(login, password)
        }
    }

    fun getUser(id:Int){
        viewModelScope.launch {
            profileLiveData.value = repository.getUser(id)
        }
    }

}