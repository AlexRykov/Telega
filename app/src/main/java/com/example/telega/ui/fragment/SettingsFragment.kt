package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.telega.R


class SettingsFragment : BaseFragment(R.layout.fragment_settings){


    override fun onResume() {
        super.onResume()

    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}