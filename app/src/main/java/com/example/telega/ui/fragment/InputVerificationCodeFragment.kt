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
import com.example.telega.utilits.*
import com.google.firebase.auth.PhoneAuthProvider


class InputVerificationCodeFragment(val phoneNum: String, val id: String) : Fragment(R.layout.fragment_input_verification_code) {

    private lateinit var mBinding: FragmentInputVerificationCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
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

                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = phoneNum
                dateMap[CHILD_USERNAME] = uid

//                call to Root element of Database
                REF_DATABASE_ROOT
//                        call or /create to Node 'users'
                    .child(NODE_USERS)
//                        call or /create to Node 'id'
                    .child(uid)
//                        push Data to Database
                    .updateChildren(dateMap)
//
                    .addOnCompleteListener { task2 ->
                        if (task2.isSuccessful){
                            showToast("Welcome")
                            (activity as RegisterActivity).intentActivity(MainActivity())
                        } else showToast(task2.exception?.message.toString())
                    }
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