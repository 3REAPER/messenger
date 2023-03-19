package ru.pervukhin.messanger.fragments.chatList

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
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
import ru.pervukhin.messanger.domain.Message
import java.util.*
import kotlin.collections.ArrayList

class ChatListFragment : Fragment(), ChatListAdapter.ChatOnClickListener {

    private lateinit var viewModel: ChatListViewModel
    private lateinit var mainActivity: MainActivity
    private lateinit var app: App

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        viewModel.startTimerChat(user.id)
        viewModel.getAllChatByUserId(user.id)
        viewModel.liveData.observe(viewLifecycleOwner){list ->
            list?.let {
                adapter.setList(sort(it))
            }
        }


        return view
    }

    private fun sort(list: List<Chat>): List<Chat>{
        val  chatList: ArrayList<Chat> = arrayListOf()
        list.forEach {
            chatList.add(it)
        }

        var sorted = false
        while (!sorted) {
            sorted = true
            for (i in 1 until chatList.size) {
                var previous: Date = Date(0)
                var current: Date = Date(0)
                if (chatList[i - 1].messages.isNotEmpty()) {
                    previous = chatList[i - 1].messages.get(chatList[i - 1].messages.size - 1).time
                }
                if (chatList[i].messages.isNotEmpty()) {
                    current = chatList[i].messages.get(chatList[i].messages.size - 1).time
                }
                if (previous.before(current)) {
                    swap(chatList, i - 1, i)
                    sorted = false
                }
            }
        }
        var result: List<Chat> = listOf()
        for (i in chatList){
            result = result.plus(i)
        }
        return result
    }


    private fun swap(list: ArrayList<Chat>,index1: Int, index2: Int) {
        val buffer = list[index1]
        list[index1] = list[index2]
        list[index2] = buffer
    }

    override fun onClick(chat: Chat) {
        app.chat = chat
        mainActivity.navigateToDialog()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopTimer()
    }
}