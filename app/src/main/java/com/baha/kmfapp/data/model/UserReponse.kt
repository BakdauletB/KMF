package com.baha.kmfapp

import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("username")
    var username: String? = null

    @SerializedName("firstName")
    var firstName: String? = null

    @SerializedName("lastName")
    var lastName: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("userStatus")
    var userStatus: Int = 0
}