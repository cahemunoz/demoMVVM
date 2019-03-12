package com.cahemunoz.demomvvm.presentation.user_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahemunoz.demomvvm.R
import com.cahemunoz.demomvvm.presentation.user_list.viewmodels.UserListViewModel
import kotlinx.android.synthetic.main.activity_user_crud.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserCrudActivity : AppCompatActivity() {
    private val userViewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_crud)

        this.listView.layoutManager = LinearLayoutManager(this)
        this.listView.adapter = UserAdapter()

        userViewModel.userList.observe(this, Observer { list ->
            listView.adapter?.let {
                (it as UserAdapter).swapList(list)
            }
        })
        createButton.setOnClickListener { userViewModel.create() }
    }
}