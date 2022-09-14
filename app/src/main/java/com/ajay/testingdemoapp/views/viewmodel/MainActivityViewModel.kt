package com.ajay.testingdemoapp.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ajay.testingdemoapp.repository.UserDatabaseRepo
import com.ajay.testingdemoapp.roomDB.User

class MainActivityViewModel(private val repository: UserDatabaseRepo, private val Email: String?) : ViewModel() {


    init {
        getUser()
    }

    fun getUser() : LiveData<User> {
        return repository.getUser(Email)
    }

}