package com.cahemunoz.demomvvm.infra.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahemunoz.demomvvm.R
import com.cahemunoz.demomvvm.infra.realm_viewmodels.UserListViewModel
import kotlinx.android.synthetic.main.activity_user_crud.*

class UserCrudActivity: AppCompatActivity () {
    private val userViewModel: UserListViewModel by lazy { ViewModelProviders.of(this).get(UserListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_crud)

        this.listView.layoutManager = LinearLayoutManager(this)
        this.listView.adapter = UserAdapter()

        userViewModel.userList.observe(this, Observer { list ->
            listView.adapter?.let {
                (it as UserAdapter).userList = list
                it.notifyDataSetChanged()
            }
        })
        createButton.setOnClickListener { userViewModel.create() }
    }
}