package com.cahemunoz.demomvvm.presentation.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cahemunoz.demomvvm.R
import com.cahemunoz.demomvvm.business.entities.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var userList: MutableList<User> = mutableListOf()


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

}