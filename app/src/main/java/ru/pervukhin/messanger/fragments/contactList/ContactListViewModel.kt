package ru.pervukhin.messanger.fragments.contactList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.data.room.EntityMapper
import ru.pervukhin.messanger.data.room.ProfileDao
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.repository.RepositoryRetrofit
import ru.pervukhin.messanger.repository.RepositoryRoom
import javax.inject.Inject

class ContactListViewModel : ViewModel() {
    val liveDataProfile: MutableLiveData<List<Profile>> = MutableLiveData()
    val liveDataChat: MutableLiveData<Chat> = MutableLiveData()
    private val repositoryRetrofit = RepositoryRetrofit()
    private val repositoryRoom = RepositoryRoom()
    @Inject
    lateinit var profileDao: ProfileDao

    init {
        App.appComponent.inject(this)
    }



    fun getProfileByNumber(json: List<Map<String,String>>){
        viewModelScope.launch {
            repositoryRetrofit.getProfileByNumber(json).body().let {
                liveDataProfile.value = it
                it?.forEach { profile ->
                    repositoryRoom.insert(profile)
                }
            }
            liveDataProfile.value
            }
        }

    fun getChat(myId: Int, userId: Int) {
        viewModelScope.launch{
            liveDataChat.value = repositoryRetrofit.getChat(myId,userId)
        }
    }

}