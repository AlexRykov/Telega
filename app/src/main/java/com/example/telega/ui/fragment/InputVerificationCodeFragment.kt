package com.example.telega.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.telega.MainActivity
import com.example.telega.R
import com.example.telega.activities.RegisterActivity
import com.example.telega.databinding.FragmentInputVerificationCodeBinding
import com.example.telega.utilits.AUTH
import com.example.telega.utilits.AppTextWatcher
import com.example.telega.utilits.intentActivity
import com.example.telega.utilits.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider


class InputVerificationCodeFragment(val phoneNum: String, val id: String) : Fragment(R.layout.fragment_input_verification_code) {

    private lateinit var mBinding: FragmentInputVerificationCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentInputVerificationCodeBinding.inflate(inflater, container, false)
        return mBinding.root
    }
        //      +1 650-555-3434
    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNum
        mBinding.registerInputPhoneNumber.addTextChangedListener(
            AppTextWatcher {
                    val stringA = mBinding.registerInputPhoneNumber.text.toString()
                    if (stringA.length == 6) {enterCode()}
            }
        )
    }

    private fun enterCode() {
        val code = mBinding.registerInputPhoneNumber.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){
                showToast("Welcome")
                (activity as RegisterActivity).intentActivity(MainActivity())
            } else {
                showToast(task.exception?.message.toString())
            }
        }
    }

//    companion object {
//
//        @JvmStatic
//        fun newInstanceVerifyFragment() =
//            InputVerificationCodeFragment(mPhoneNum, id).apply {
//                arguments = Bundle().apply {
//                }
//            }
//    }
}