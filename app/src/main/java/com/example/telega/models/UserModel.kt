package com.example.telega.models

data class UserModel (
    val id: String = "",
    var username:String = "",
    var about: String = "",
    var fullname: String = "",
    var state: String = "",
    var phone: String = "",
    var photoUrl: String = "empty"
        )