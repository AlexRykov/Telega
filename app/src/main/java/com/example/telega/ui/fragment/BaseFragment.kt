package com.example.telega.ui.fragment


import androidx.fragment.app.Fragment
import com.example.telega.MainActivity

open class BaseFragment(layout:Int):Fragment(layout) {



    override fun onStart() {
        super.onStart()
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).mAppDrawer.enableDrawer()

    }
}
