package com.example.telega.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.telega.R


class ChatFragment : Fragment(R.layout.fragment_chat) {

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