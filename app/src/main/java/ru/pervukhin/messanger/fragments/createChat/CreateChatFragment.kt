package ru.pervukhin.messanger.fragments.createChat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.fragment_create_chat.view.*
import kotlinx.android.synthetic.main.item_rv_contact.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.adapter.AddProfileAdapter
import ru.pervukhin.messanger.domain.GroupChat

class CreateChatFragment : Fragment() {

    private lateinit var viewModel: CreateChatViewModel
    private lateinit var name: EditText
    private lateinit var description: EditText
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_chat, container, false)
        viewModel = ViewModelProvider(this).get(CreateChatViewModel::class.java)
        name = view.name_edit_text
        description = view.description_edit_text
        mainActivity = activity as MainActivity
        val app = mainActivity.application as App
        val createChat = view.create_chat
        val isPrivate = view.is_private
        val recyclerView = view.add_profile_recycler_view
        val adapter = AddProfileAdapter()

        recyclerView.adapter = adapter
        viewModel.getProfile()
        viewModel.liveDate.observe(viewLifecycleOwner){
            adapter.setList(it)
        }

        createChat.setOnClickListener {
            if (validate()){
                var users = adapter.listChecked
                app.user.let {
                    if (it != null){
                        users = users.plus(it)
                    }
                }
                val groupChat = GroupChat(users, listOf(),true,name.text.toString(),description.text.toString(),isPrivate.isChecked,app.user!!)
                viewModel.createChat(groupChat)
                mainActivity.navigateToChatListFromCreateChat()
            }

        }

        return view
    }

    private fun validate(): Boolean{
        name.addTextChangedListener {
            validateName()
        }
        return validateName()
    }

    private fun validateName(): Boolean{
        return if (name.text.toString() == ""){
            name.error = this.getString(R.string.error_name_miss)
            false
        }else{
            name.error = ""
            true
        }
    }
}