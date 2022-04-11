package com.baha.kmfapp.presentation.activity

import androidx.lifecycle.liveData
import com.baha.kmfapp.Resource
import com.baha.kmfapp.UserResponse
import com.baha.kmfapp.api_clients.KMFApi
import com.baha.kmfapp.utils.BaseVM
import kotlinx.coroutines.Dispatchers
import kz.edu.kmf.data.model.User

class RegistrationViewModel : BaseVM() {

    fun registerUser(user: User) = liveData(Dispatchers.IO) {
        val userResponse = UserResponse()
        userResponse.email = user.email
        userResponse.password = user.password
        userResponse.firstName = user.firstName
        userResponse.lastName = user.lastName
        userResponse.userStatus = user.userStatus
        userResponse.username = user.username
        userResponse.phone = user.phone
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = KMFApi.retrofitService.registerUser(userResponse)))

        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}