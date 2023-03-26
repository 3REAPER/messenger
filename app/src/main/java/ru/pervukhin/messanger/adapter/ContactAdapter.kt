package ru.pervukhin.messanger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_chat.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.domain.Profile
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    @Inject
    lateinit var user: Profile

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_chat, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

    class ContactViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

}