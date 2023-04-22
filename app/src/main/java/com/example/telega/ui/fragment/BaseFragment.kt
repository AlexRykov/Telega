package com.example.telega.ui.fragment


import androidx.fragment.app.Fragment
import com.example.telega.MainActivity
import com.example.telega.utilits.hideKeyboard

open class BaseFragment(layout:Int):Fragment(layout) {



    override fun onStart() {
        super.onStart()
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).mAppDrawer.enableDrawer()
        hideKeyboard()

    }
}
