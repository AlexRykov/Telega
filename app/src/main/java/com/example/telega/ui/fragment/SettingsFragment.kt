package com.example.telega.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.telega.MainActivity
import com.example.telega.R
import com.example.telega.activities.RegisterActivity
import com.example.telega.utilits.AUTH
import com.example.telega.utilits.intentActivity
import com.example.telega.utilits.intentFragment


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
            R.id.settings_menu_change_name -> {
                intentFragment(R.id.data_container, ChangeNameFragment())
            }
        }
        return true
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ChatFragment().apply {
//                arguments = Bundle().apply {
//                }
//            }
//    }
}