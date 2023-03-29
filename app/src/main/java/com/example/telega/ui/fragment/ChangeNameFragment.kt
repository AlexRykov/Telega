package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.telega.MainActivity
import com.example.telega.R
import com.example.telega.databinding.FragmentChangeNameBinding
import com.example.telega.databinding.FragmentInputPhoneNumberBinding
import com.example.telega.utilits.*


class ChangeNameFragment : Fragment(R.layout.fragment_change_name) {


    private lateinit var mBinding: FragmentChangeNameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentChangeNameBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_confirm_change -> changeName()
        }
        return true
    }

    private fun changeName() {
        val name = mBinding.settingsInputName.text.toString()
        val secondName = mBinding.settingsInputSecondName.text.toString()
        if (name.isEmpty()){
            showToast(getString(R.string.name_can_not_be_empty))
        } else {
            val fullName = "$name $secondName"
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_FULLNAME).setValue(fullName)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        showToast(getString(R.string.toast_data_updated))
                        USER.fullName = fullName
                        fragmentManager?.popBackStack()
                    } else {
                        showToast("sdgdg")
                    }
                }
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).mAppDrawer.enableDrawer()

    }
}