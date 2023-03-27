package com.example.telega.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.telega.R
import com.example.telega.ui.fragment.InputPhoneNumberFragment
import com.example.telega.utilits.initFirebase
import com.example.telega.utilits.intentFragment

class RegisterActivity : AppCompatActivity() {
//    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initFirebase()
    }



    override fun onStart() {
        super.onStart()
        mToolbar = findViewById(R.id.registerToolbar)
        setSupportActionBar(mToolbar)
        title = getString(R.string.your_phone_num_is)
        intentFragment(R.id.register_data_container, InputPhoneNumberFragment(), false)

    }
}