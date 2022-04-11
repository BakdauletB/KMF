package com.baha.kmfapp.data.repository

import androidx.lifecycle.LiveData
import kz.edu.kmf.data.local.UserDao
import kz.edu.kmf.data.model.User

class RegistrationRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }


}