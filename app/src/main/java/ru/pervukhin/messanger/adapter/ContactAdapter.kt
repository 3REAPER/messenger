package ru.pervukhin.messanger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_chat.view.*
import kotlinx.android.synthetic.main.item_rv_contact.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.domain.Profile
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var list: List<Profile> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val name = holder.itemView.name
        val number = holder.itemView.number
        val openChatButton = holder.itemView.open_chat

        val profile = list[position]

        name.text = profile.name
        number.text = profile.number

        openChatButton.setOnClickListener {
            TODO("create listener")
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Profile>){
        this.list = list
        notifyDataSetChanged()
    }

    class ContactViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

}