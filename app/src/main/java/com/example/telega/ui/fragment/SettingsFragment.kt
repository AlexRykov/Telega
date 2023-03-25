package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.*
import com.example.telega.MainActivity
import com.example.telega.R
import com.example.telega.activities.RegisterActivity
import com.example.telega.utilits.AUTH
import com.example.telega.utilits.intentActivity


class SettingsFragment : BaseFragment(R.layout.fragment_settings){


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.settings_action_manu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_menu_change_exit -> {
                AUTH.signOut()
                (activity as MainActivity).intentActivity(RegisterActivity())
            }
        }
        return true
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