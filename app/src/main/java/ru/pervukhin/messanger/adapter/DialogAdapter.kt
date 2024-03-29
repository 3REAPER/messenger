package ru.pervukhin.messanger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_message.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.domain.Profile
import javax.inject.Inject

class DialogAdapter: RecyclerView.Adapter<DialogAdapter.DialogViewHolder>() {
    var list: List<Message> = listOf()
    @Inject
     lateinit var myProfile: Profile

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_message,parent,false)
        return DialogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        App.appComponent.inject(this)
        val date  = holder.itemView.date
        val companion = holder.itemView.companion
        val author = holder.itemView.author
        val companionMessage = holder.itemView.companion_message
        val companionTime = holder.itemView.companion_time
        val my = holder.itemView.my
        val myMessage = holder.itemView.my_message
        val myTime = holder.itemView.my_time

        val message = list[position]
        if(position != 0) {
            val lastMessage = list[position - 1]
            val dateLastMessage = lastMessage.getDateString()
            val dateNowMessage = message.getDateString()
            if (dateLastMessage == dateNowMessage) {
                if (date.visibility != View.GONE) {
                    date.visibility = View.GONE
                }
                date.text = dateNowMessage
            } else if (dateLastMessage != dateNowMessage){
                if (date.visibility != View.VISIBLE) {
                    date.visibility = View.VISIBLE
                }
                date.text = dateNowMessage
            }
        }else{
            if (date.visibility != View.VISIBLE) {
                date.visibility = View.VISIBLE
            }
            date.text = message.getDateString()
        }
        if (message.authorId.id == myProfile.id){
            if (my.visibility != View.VISIBLE){
                my.visibility = View.VISIBLE
            }
            myMessage.text = message.message
            myTime.text = message.getTimeString()
            companion.visibility= View.INVISIBLE
        }else{
            if (companion.visibility != View.VISIBLE){
                companion.visibility = View.VISIBLE
            }
            companionMessage.text = message.message
            companionTime.text = message.getTimeString()
            author.text = message.authorId.name +":"
            my.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun init(list: List<Message>){
        this.list = list
        notifyDataSetChanged()
    }

    fun addList(message: Message){
        list = list.plusElement(message)
        notifyDataSetChanged()
    }

    class DialogViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}

