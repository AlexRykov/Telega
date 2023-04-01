package com.example.telega.models

data class User (
    val id: String = "",
    var username:String = "",
    var about: String = "",
    var fullname: String = "",
    var status: String = "",
    var phone: String = "",
    var photoUrl: String = ""
        )