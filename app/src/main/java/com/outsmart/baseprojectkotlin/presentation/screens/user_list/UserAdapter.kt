package com.outsmart.baseprojectkotlin.presentation.screens.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outsmart.baseprojectkotlin.R
import com.outsmart.baseprojectkotlin.services.entities.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userList: MutableList<User> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return object: RecyclerView.ViewHolder (view){}
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = userList[position]
        holder.itemView.emailView.text = user.email
        holder.itemView.usernameView.text = user.username
    }

    fun swapList(userList: MutableList<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}