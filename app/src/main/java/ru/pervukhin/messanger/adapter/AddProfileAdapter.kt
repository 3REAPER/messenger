package ru.pervukhin.messanger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_add_profile.view.*
import kotlinx.android.synthetic.main.item_rv_profile.view.name
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.domain.Profile

class AddProfileAdapter: RecyclerView.Adapter<AddProfileAdapter.AddProfileViewHolder>() {
    private var list: List<Profile> = listOf()
    var listChecked: List<Profile> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_add_profile, parent, false)
        return AddProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddProfileViewHolder, position: Int) {
        val name = holder.itemView.name
        val checkbox = holder.itemView.checkbox

        val profile = list[position]
        name.text = profile.name

        checkbox.setOnCheckedChangeListener { compoundButton, isChecked ->
            listChecked = if (isChecked){
                listChecked.plus(profile)
            }else{
                listChecked.minus(profile)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Profile>){
        this.list = list
        notifyDataSetChanged()
    }

    fun add(profile: Profile){
        list = list.plus(profile)
        notifyDataSetChanged()
    }

    class AddProfileViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }
}