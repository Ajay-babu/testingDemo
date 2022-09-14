package com.ajay.testingdemoapp.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajay.testingdemoapp.repository.UserDatabaseRepo
import com.ajay.testingdemoapp.views.viewmodel.MainActivityViewModel


class MainActivityViewModelFactory(
    private val repository: UserDatabaseRepo,
    private val email: String?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository, email) as T
    }
}