package com.example.telega.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.telega.R
import com.example.telega.databinding.FragmentInputPhoneNumberBinding
import com.example.telega.databinding.FragmentInputVerificationCodeBinding


class InputVerificationCodeFragment : Fragment(R.layout.fragment_input_verification_code) {

    private lateinit var mBinding:FragmentInputVerificationCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentInputVerificationCodeBinding.inflate(inflater, container, false)
        return mBinding.root
    }
    override fun onStart() {
        super.onStart()
        mBinding.registerInputPhoneNumber.addTextChangedListener (
            object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    val stringA = mBinding.registerInputPhoneNumber.text.toString()
                    if (stringA.length == 6) {
                        verificateCode()
                    }
                }
            }
                )
    }
    private fun verificateCode(){
        Toast.makeText(activity, "OKAY", Toast.LENGTH_SHORT).show()
    }

    companion object {

        @JvmStatic
        fun newInstanceVerifyFragment() =
            InputVerificationCodeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}