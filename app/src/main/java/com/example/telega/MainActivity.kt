package com.example.telega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.telega.activities.RegisterActivity
import com.example.telega.databinding.ActivityMainBinding
import com.example.telega.ui.fragment.ChatFragment
import com.example.telega.ui.objects.AppDrawer
import com.example.telega.utilits.AUTH
import com.example.telega.utilits.initFirebase
import com.example.telega.utilits.intentActivity
import com.example.telega.utilits.intentFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if(AUTH.currentUser!=null){
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            intentFragment(R.id.data_container, ChatFragment(), false)
        } else {
            intentActivity(RegisterActivity())
        }


    }



    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
        initFirebase()

    }
}