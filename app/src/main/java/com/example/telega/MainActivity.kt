package com.example.telega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.telega.activities.RegisterActivity
import com.example.telega.databinding.ActivityMainBinding
import com.example.telega.models.User
import com.example.telega.ui.fragment.ChatFragment
import com.example.telega.ui.objects.AppDrawer
import com.example.telega.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

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
        initUser()

    }

    private fun initUser() {

        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
//                15 Lesson 4:00 . This listener connect to Database, download data of User, and close
            .addListenerForSingleValueEvent(AppValueEventListener{
             USER = it.getValue(User::class.java)?: User()
                Log.d("USER", USER.fullname)
            })
    }
}