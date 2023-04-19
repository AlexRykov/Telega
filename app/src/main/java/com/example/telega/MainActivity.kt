package com.example.telega

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.telega.activities.RegisterActivity
import com.example.telega.databinding.ActivityMainBinding
import com.example.telega.ui.fragment.ChatFragment
import com.example.telega.ui.objects.AppDrawer
import com.example.telega.utilits.*

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFirebase()
        initUser {
            initContacts()
            initFields()
            initFunc()
        }
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            intentFragment(R.id.data_container, ChatFragment(), false)
        } else {
            intentActivity(RegisterActivity())
        }
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer()

    }

    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }
//      lesson 28 7:00 Window of permission AGAIN !
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(
                APP_ACTIVITY,
                READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            initContacts()
        }
    }
}