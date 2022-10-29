package ru.pervukhin.messanger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_chat.view.*
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.domain.Chat
import kotlin.coroutines.coroutineContext

class ChatListAdapter(val listener: ChatOnClickListener): RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>() {
    var chatList: List<Chat> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_chat, parent, false)
        return ChatListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        val nameChatTextView = holder.itemView.name_chat
        val authorTextView = holder.itemView.author
        val messageTextView = holder.itemView.message
        val timeTextView = holder.itemView.time
        val chat = chatList[position]

        holder.itemView.setOnClickListener(View.OnClickListener {
            listener.onClick(chat)
        })

        chat.let {
            nameChatTextView.text = it.name
            if (it.messages.isNotEmpty()) {
                val message = it.messages.get(it.messages.size - 1)
                authorTextView.text = message.profile.name +":"
                messageTextView.text = message.message
                timeTextView.text = message.time.split(".").get(3) +":" +message.time.split(".").get(4)
            }


        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    fun setList(list: List<Chat>){
        chatList = list
        notifyDataSetChanged()
    }

    class ChatListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    interface ChatOnClickListener{
        fun onClick(chat: Chat)
    }
}