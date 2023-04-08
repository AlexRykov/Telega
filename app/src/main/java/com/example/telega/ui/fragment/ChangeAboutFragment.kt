package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.telega.R
import com.example.telega.databinding.FragmentChangeAboutBinding
import com.example.telega.utilits.*


class ChangeAboutFragment : BaseChangeFragment(R.layout.fragment_change_about) {

    private lateinit var mBinding: FragmentChangeAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentChangeAboutBinding.inflate(inflater, container, false)
        return mBinding.root
    }
    override fun onResume() {
        super.onResume()
        mBinding.settingsInputAbout.setText(USER.about)
    }

    override fun change() {
        super.change()
        val mAbout = mBinding.settingsInputAbout.text.toString()
        if (mAbout.isEmpty()) {
            showToast(getString(R.string.name_can_not_be_empty))
        } else {
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_ABOUT).setValue(mAbout)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(getString(R.string.toast_data_updated))
                        USER.about = mAbout
                        fragmentManager?.popBackStack()
                    } else {
                        showToast("sdgdg")
                    }
                }
        }
    }
}