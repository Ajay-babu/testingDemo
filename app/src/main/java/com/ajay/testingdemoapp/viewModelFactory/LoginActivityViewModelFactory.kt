package com.ajay.testingdemoapp.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajay.testingdemoapp.repository.UserDatabaseRepo
import com.ajay.testingdemoapp.views.viewmodel.LoginActivityViewModel

class LoginActivityViewModelFactory(private val repository : UserDatabaseRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginActivityViewModel(repository) as T
    }
}