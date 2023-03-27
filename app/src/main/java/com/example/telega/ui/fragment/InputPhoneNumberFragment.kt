package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.telega.MainActivity
import com.example.telega.R
import com.example.telega.activities.RegisterActivity
import com.example.telega.databinding.FragmentInputPhoneNumberBinding
import com.example.telega.utilits.AUTH
import com.example.telega.utilits.intentActivity
import com.example.telega.utilits.intentFragment
import com.example.telega.utilits.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class InputPhoneNumberFragment : Fragment(R.layout.fragment_input_phone_number) {

   private lateinit var mBinding:FragmentInputPhoneNumberBinding
   private lateinit var mPhoneNum: String
   private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentInputPhoneNumberBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        showToast("Welcome")
                        (activity as RegisterActivity).intentActivity(MainActivity())
                    } else {
                        showToast(task.exception?.message.toString())
                    }
                }
            }
            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                intentFragment(R.id.register_data_container,
                    InputVerificationCodeFragment(mPhoneNum, id)
                )
            }
            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
            }
        }
        mBinding.registerInputPhoneNumber.text.clear()
        mBinding.registerBtnNext.setOnClickListener {
            sendCode()
        }
    }

    private fun sendCode() {
        if(mBinding.registerInputPhoneNumber.text.toString().isEmpty()){
            showToast("Input your phone num")
        } else {
//            intentFragment(R.id.register_data_container, InputVerificationCodeFragment())
            authUser()
        }
    }

    private fun authUser() {
        mPhoneNum = mBinding.registerInputPhoneNumber.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNum,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallback
        )
    }

//    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            InputPhoneNumberFragment().apply {
//                arguments = Bundle().apply {
//                }
//            }
//    }
}