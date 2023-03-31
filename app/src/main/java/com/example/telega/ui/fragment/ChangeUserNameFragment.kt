package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.*
import com.example.telega.MainActivity
import com.example.telega.R
import com.example.telega.databinding.FragmentChangeUserNameBinding
import com.example.telega.utilits.*
import java.util.*

class ChangeUserNameFragment : BaseFragment(R.layout.fragment_change_user_name) {

    private lateinit var mBinding: FragmentChangeUserNameBinding
    private lateinit var mNewUserName: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentChangeUserNameBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        mBinding.settingsInputUserName.setText(USER.username)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_confirm_change -> changeUserName()
        }
        return true
    }

    private fun changeUserName() {
        mNewUserName = mBinding.settingsInputUserName.text.toString().toLowerCase(Locale.getDefault())
            .toLowerCase(Locale.getDefault())
        if (mNewUserName.isEmpty()){
            showToast(getString(R.string.name_can_not_be_empty))
        }  else  {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener{
                    if (it.hasChild(mNewUserName)){
                        showToast(getString(R.string.user_name_busy))
                    } else {
                        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUserName).setValue(UID)
                            .addOnCompleteListener { task ->
                                if(task.isSuccessful){
                                    updateCurrentUserName()
                                } else {
                                    showToast("sdgdg")
                                }
                            }
                    }
                })
        }
    }
    private fun updateCurrentUserName() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_USERNAME)
            .setValue(mNewUserName)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    showToast(getString(R.string.toast_data_updated))
                    deleteOldUserNameFromDataBase()
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }

    private fun deleteOldUserNameFromDataBase() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast(getString(R.string.toast_data_updated))
                    USER.username = mNewUserName
                    fragmentManager?.popBackStack()

                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }


    override fun onStop() {
        super.onStop()
    }
}
