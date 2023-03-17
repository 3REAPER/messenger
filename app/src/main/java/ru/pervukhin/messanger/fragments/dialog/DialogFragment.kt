package ru.pervukhin.messanger.fragments.dialog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.adapter.DialogAdapter
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.fragments.chatList.ChatListViewModel

class DialogFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var app: App

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
        mainActivity = activity as MainActivity
        app = mainActivity.application as App
        val adapter = DialogAdapter()
        val rvMessage = view.message_list
        val messageLayout = view.message_layout
        val appBar = view.appBarLayout.name_chat

        rvMessage.adapter = adapter
        appBar.title = app.chat.name

        viewModel.getAllMessageChatId(app.chat.id)
        viewModel.liveData.observe(viewLifecycleOwner){list ->
            list.let {
                if (list != null) {
                    adapter.init(list,app.user)
                }
            }
        }

        viewModel.getNewMessage(app.user.id)

        appBar.setNavigationOnClickListener {
            mainActivity.navigateToChatListFromDialog()
            viewModel.stopTimerMessage()
        }

        appBar.setOnClickListener {
            mainActivity.navigateToChatSettingFromDialog()
        }

        messageLayout.setEndIconOnClickListener(View.OnClickListener {
            val sendMessage = Message(messageLayout.message_edit_text.text.toString(),"25.10.2022.13.26",false,app.user, Message.CREATE, app.chat.id)
            messageLayout.message_edit_text.setText("")
            adapter.addList(sendMessage)
            viewModel.sendMessage(sendMessage)
        })
        return view
    }

}