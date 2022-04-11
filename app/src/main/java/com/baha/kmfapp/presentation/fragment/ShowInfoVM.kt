package com.baha.kmfapp.presentation.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.baha.kmfapp.Resource
import com.baha.kmfapp.api_clients.KMFApi
import com.baha.kmfapp.data.repository.RegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.edu.kmf.data.local.UserDatabase
import kz.edu.kmf.data.model.User

class ShowInfoVM(application: Application) : AndroidViewModel(application) {

    fun getUsers(user: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = KMFApi.retrofitService.getUser(user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun addUser(event: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(event)
        }
    }
    val readAllData: LiveData<List<User>>
    private val repository: RegistrationRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = RegistrationRepository(userDao)
        println("Counter ")
        readAllData = repository.readAllData
    }

}