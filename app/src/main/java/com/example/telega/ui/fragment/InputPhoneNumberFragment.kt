package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.telega.R
import com.example.telega.databinding.FragmentInputPhoneNumberBinding

class InputPhoneNumberFragment : Fragment(R.layout.fragment_input_phone_number) {

   private lateinit var mBinding:FragmentInputPhoneNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentInputPhoneNumberBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.registerInputPhoneNumber.text.clear()
        mBinding.registerBtnNext.setOnClickListener {
            sendCode()

        }
    }

    private fun sendCode() {
        if(mBinding.registerInputPhoneNumber.text.toString().isEmpty()){
            Toast.makeText(activity, "Input your phone num", Toast.LENGTH_SHORT).show()
        } else {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.register_data_container, InputVerificationCodeFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputPhoneNumberFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}