package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.*
import com.example.telega.R
import com.example.telega.databinding.FragmentChangeNameBinding
import com.example.telega.utilits.*


class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {


    private lateinit var mBinding: FragmentChangeNameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentChangeNameBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        initFullnameList()
    }

    private fun initFullnameList() {
        //        this method divide full name "Adam Tomas" by delimiters ' space ' on "Adam" and "Tomas"
        val fullNameList: List<String> = USER.fullname.split(" ")

        //    lesson 15     You HAVE TO write --fullname-- like in Database nodes (appDatabaseHelper.kt) not fullName ! ! !

        if (fullNameList.size > 1) {
            mBinding.settingsInputName.setText(fullNameList[0])
            mBinding.settingsInputSecondName.setText(fullNameList[1])
        } else {
            mBinding.settingsInputName.setText(fullNameList[0])
        }
    }

    override fun change() {
        val name = mBinding.settingsInputName.text.toString()
        val secondName = mBinding.settingsInputSecondName.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.name_can_not_be_empty))
        } else {
            val fullName = "$name $secondName"
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_FULLNAME)
                .setValue(fullName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(getString(R.string.toast_data_updated))
                        hideKeyboard()
                        USER.fullname = fullName
                        fragmentManager?.popBackStack()
                    } else {
                        showToast("sdgdg")
                    }
                }
        }
    }
}