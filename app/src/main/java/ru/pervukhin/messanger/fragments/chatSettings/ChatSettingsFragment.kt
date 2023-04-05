package ru.pervukhin.messanger.fragments.chatSettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_chat_settings.view.*
import kotlinx.android.synthetic.main.fragment_dialog.view.appBarLayout
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.adapter.ProfileAdapter
import ru.pervukhin.messanger.domain.GroupChat
import ru.pervukhin.messanger.domain.Profile

class ChatSettingsFragment : Fragment(), ProfileAdapter.OnClickListener{
    private lateinit var viewModel: ChatSettingsViewModel
    private lateinit var adapter: ProfileAdapter
    private lateinit var app: App

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat_settings, container, false)
        viewModel = ViewModelProvider(this).get(ChatSettingsViewModel::class.java)
        val mainActivity = activity as MainActivity
        app = mainActivity.application as App
        val appBar = view.appBarLayout.appBar
        val nameChat = view.name
        val description = view.description
        val profileRecyclerView = view.profile_recycler_view
        adapter = ProfileAdapter(app.user!!,this)

        app.chat.let {
            if (it is GroupChat){
                adapter.setList(app.chat!!.usersId)
                profileRecyclerView.adapter = adapter

                nameChat.setText(it.name)
                description.setText(it.description)
            }
        }

        appBar.setOnClickListener {
            mainActivity.navigateToDialogFromChatSettings()
        }
        return view
    }

    override fun onRecyclerClick(profile: Profile) {
        app.chat!!.usersId = app.chat!!.usersId.minus(profile)
        viewModel.deleteUserFromChat(app.chat!!.id, profile.id)
        adapter.remove(profile)
    }
}