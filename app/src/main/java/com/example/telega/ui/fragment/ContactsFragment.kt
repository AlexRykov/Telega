package com.example.telega.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.telega.R
import com.example.telega.utilits.APP_ACTIVITY


class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Contacts"

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}