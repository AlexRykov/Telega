package com.example.telega.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.telega.R
import com.example.telega.databinding.ActivityRegisterBinding
import com.example.telega.ui.fragment.InputPhoneNumberFragment

class RegisterActivity : AppCompatActivity() {
//    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }



    override fun onStart() {
        super.onStart()
        mToolbar = findViewById(R.id.registerToolbar)
        setSupportActionBar(mToolbar)
        title = getString(R.string.your_phone_num_is)
        supportFragmentManager.beginTransaction()
            .add(R.id.register_data_container, InputPhoneNumberFragment())
            .commit()

    }
}