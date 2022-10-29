package ru.pervukhin.messanger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_profile.view.*
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.domain.Profile

class ProfileAdapter(val me: Profile, val  listener: OnClickListener) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    private var list : List<Profile> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_profile,parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val name : TextView = holder.itemView.name
        val delete = holder.itemView.delete

        if (list[position].login == me.login){
            name.text = "Я"
            delete.visibility = View.INVISIBLE
        }else {
            name.text = list[position].name
            if (delete.visibility != View.VISIBLE){
                delete.visibility = View.VISIBLE
            }
        }

        delete.setOnClickListener(){
            listener.onRecyclerClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list : List<Profile>){
        this.list = list
        notifyDataSetChanged()
    }

    fun addProfile(profile: Profile){
        list = list.plusElement(profile)
        notifyDataSetChanged()
    }

    fun remove(profile: Profile){
        list = list.minus(profile)
        notifyDataSetChanged()
    }

    class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }

    interface OnClickListener{

        fun onRecyclerClick(profile: Profile)
    }
}