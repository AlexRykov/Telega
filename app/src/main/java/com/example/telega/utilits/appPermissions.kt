package com.example.telega.utilits

import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


const val READ_CONTACTS = android.Manifest.permission.READ_CONTACTS
const val PERMISSION_REQUEST = 200

fun checkPermissions(permission: String): Boolean {
    return if (ContextCompat.checkSelfPermission(
            APP_ACTIVITY,
            permission
        ) != PackageManager.PERMISSION_GRANTED
    ) {
//        lesson 28 4:50 its the Window which opens when you give agree to something permission
        ActivityCompat.requestPermissions(APP_ACTIVITY, arrayOf(permission), PERMISSION_REQUEST)
        false
    } else true
}