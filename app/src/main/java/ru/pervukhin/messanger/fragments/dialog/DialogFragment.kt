package ru.pervukhin.messanger.fragments.dialog

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.adapter.DialogAdapter
import ru.pervukhin.messanger.domain.*
import java.util.*
import javax.inject.Inject

class DialogFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var player: MediaPlayer
    private lateinit var rvMessage: RecyclerView

    @Inject
    lateinit var chat: Chat
    @Inject
    lateinit var user: Profile

    companion object {
        fun newInstance() = DialogFragment()
    }

    private lateinit var viewModel: DialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)
        viewModel = ViewModelProvider(this).get(DialogViewModel::class.java)
        App.appComponent.inject(this)
        mainActivity = activity as MainActivity
        player = MediaPlayer.create(context,R.raw.ring_new_message_in_dialog_fragment)
        rvMessage = view.message_list
        val adapter = DialogAdapter()
        val messageLayout = view.message_layout
        val appBar = view.appBarLayout.name_chat

        rvMessage.adapter = adapter
        if (chat is GroupChat){
            appBar.title = (chat as GroupChat).name
        }else{
            chat.usersId.forEach{
                if (it.id != user.id){
                    appBar.title = it.name
                }
            }
        }


        viewModel.getAllMessageChatId(chat.id)
        viewModel.liveData.observe(viewLifecycleOwner){list ->
            list.let {
                if (list != null) {
                    if (adapter.itemCount != 0){
                        notifyUser()
                    }
                    adapter.init(list)
                    viewModel.messageRead(list)
                }
            }
        }


        viewModel.startTimerMessages(user.id,chat.id)

        appBar.setNavigationOnClickListener {
            mainActivity.navigateToChatListFromDialog()
        }

        appBar.setOnClickListener {
            mainActivity.navigateToChatSettingFromDialog()
        }

        messageLayout.setEndIconOnClickListener{
            val sendMessage = Message(messageLayout.message_edit_text.text.toString(), Calendar.getInstance().time,false,user, listOf(ConditionSend(user, ConditionSend.CONDITION_CREATE)), chat.id)
            messageLayout.message_edit_text.setText("")
            adapter.addList(sendMessage)
            viewModel.sendMessage(sendMessage)
        }
        return view
    }

    private fun notifyUser(){
        rvMessage.adapter?.itemCount?.let { rvMessage.smoothScrollToPosition(it) }
        player.start()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopTimerMessage()
    }
}