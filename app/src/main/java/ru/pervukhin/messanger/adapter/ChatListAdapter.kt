package ru.pervukhin.messanger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_chat.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.domain.Chat
import ru.pervukhin.messanger.domain.ConditionSend
import ru.pervukhin.messanger.domain.GroupChat
import ru.pervukhin.messanger.domain.Profile
import javax.inject.Inject


class ChatListAdapter(private val listener: ChatOnClickListener): RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>() {
    var chatList: List<Chat> = emptyList()

    @Inject
    lateinit var user: Profile

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_chat, parent, false)
        return ChatListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        App.appComponent.inject(this)
        val nameChatTextView = holder.itemView.name_chat
        val authorTextView = holder.itemView.author
        val messageTextView = holder.itemView.message
        val timeTextView = holder.itemView.time
        val newMessageCount = holder.itemView.new_messages_count
        val textChat = holder.itemView.text_chat
        val chat = chatList[position]

        holder.itemView.setOnClickListener{
            listener.onClick(chat)
        }

        chat.let {

            if (it is GroupChat){
                nameChatTextView.text = it.name
                textChat.text = it.name.getOrNull(0).toString()
            }else {
                it.usersId.forEach {profile ->
                    if (profile.id != user.id){
                        nameChatTextView.text = profile.name
                        textChat.text = profile.name.getOrNull(0).toString()
                    }
                }
            }
            if (it.messages.isNotEmpty()) {
                var count = 0
                val message = it.messages.get(it.messages.size - 1)
                authorTextView.text = message.authorId.name +":"
                messageTextView.text = message.message
                timeTextView.text = " - " +message.getTimeString()
                chat.messages.forEach { mes ->
                        if (mes.conditionSend[0].condition != ConditionSend.CONDITION_READ && mes.authorId != user) {
                            count++
                        }
                }


                if (count > 99){
                    if (newMessageCount.visibility == View.INVISIBLE){
                        newMessageCount.visibility = View.VISIBLE
                    }
                    newMessageCount.text = "99+"
                }else if (count == 0){
                    newMessageCount.visibility = View.INVISIBLE
                }else{
                    if (newMessageCount.visibility == View.INVISIBLE){
                        newMessageCount.visibility = View.VISIBLE
                    }
                    newMessageCount.text = count.toString()
                }
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

    class ChatListViewHolder(view: View): RecyclerView.ViewHolder(view)

    interface ChatOnClickListener{
        fun onClick(chat: Chat)
    }
}