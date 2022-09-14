package com.ajay.testingdemoapp.repository

import androidx.lifecycle.LiveData
import com.ajay.testingdemoapp.roomDB.User
import com.ajay.testingdemoapp.roomDB.UserDao

class UserDatabaseRepo(private val userDao: UserDao) {

    suspend fun getUser(email: String, password: String): User {
        return userDao.getUser(email, password)
    }

    fun getUser(email: String?): LiveData<User> {
        return userDao.getUser(email)
    }

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}