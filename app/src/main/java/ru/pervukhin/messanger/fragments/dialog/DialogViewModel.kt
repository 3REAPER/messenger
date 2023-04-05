package ru.pervukhin.messanger.fragments.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.messanger.domain.ConditionSend
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.repository.Repository
import java.util.*

class DialogViewModel : ViewModel() {
    val liveData: MutableLiveData<List<Message>?> = MutableLiveData()
    private val repository = Repository()
    private var timer = Timer()

    fun getAllMessageChatId(id: Int) {
        viewModelScope.launch {
            liveData.value = repository.getAllMessageChatId(id).body()
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            repository.sendMessage(message)
        }
    }

    fun messageRead(list: List<Message>){
        viewModelScope.launch {
            list.forEach{
                messageRead(it)
            }
        }
    }

    private fun messageRead(message: Message) {
        viewModelScope.launch {
            if (message.conditionSend[0].condition != ConditionSend.CONDITION_READ) {
                message.conditionSend[0].condition = ConditionSend.CONDITION_READ
                repository.updateMessage(message)
            }
        }
    }

    fun startTimerMessages(profileId: Int,chatId: Int) {
            timer.schedule(object : TimerTask() {
                override fun run() {
                    viewModelScope.launch {
                        val messages = repository.getUnread(profileId).body()
                        if (messages != null) {
                            if (messages.isNotEmpty()) {
                                if (messages[0].chatId == chatId) {
                                    messages[0].conditionSend[0].condition = ConditionSend.CONDITION_SEND
                                    repository.updateMessage(messages[0])
                                }
                                liveData.value = liveData.value?.plus(messages)


                            }
                        }
                    }

                }
            }, 1, 1L * 1000)
    }

    fun stopTimerMessage(){
        timer.cancel()
        timer = Timer()
    }
}