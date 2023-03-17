package ru.pervukhin.messanger.fragments.chatList

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_chat_list.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MessageNotificationService
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.adapter.ChatListAdapter
import ru.pervukhin.messanger.domain.Chat

class ChatListFragment : Fragment(), ChatListAdapter.ChatOnClickListener {

    private lateinit var viewModel: ChatListViewModel
    private lateinit var mainActivity: MainActivity
    private lateinit var app: App

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.startService(Intent(context,MessageNotificationService::class.java))
        viewModel = ViewModelProvider(this).get(ChatListViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_chat_list, container, false)
        val nameUser = view.name_user
        val recyclerView = view.chat_list
        val adapter = ChatListAdapter(this)
        recyclerView.adapter = adapter

        mainActivity = activity as MainActivity
        app = mainActivity.application as App
        val user = app.user
        nameUser.title = user.name
        viewModel.getAllChatByUserId(user.id)
        viewModel.liveData.observe(viewLifecycleOwner){list ->
            list.body()?.let { adapter.setList(it) }
        }


        return view
    }

    override fun onClick(chat: Chat) {
        app.chat = chat
        activity?.stopService(Intent(context,MessageNotificationService::class.java))
        mainActivity.navigateToDialog()
    }
}