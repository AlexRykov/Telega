package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.*
import com.example.telega.MainActivity
import com.example.telega.R
import com.example.telega.activities.RegisterActivity
import com.example.telega.databinding.FragmentSettingsBinding
import com.example.telega.utilits.AUTH
import com.example.telega.utilits.USER
import com.example.telega.utilits.intentActivity
import com.example.telega.utilits.intentFragment


class SettingsFragment : BaseFragment(R.layout.fragment_settings){

    private lateinit var mBinding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()

    }

    private fun initFields() {
        with(mBinding){
            tvFullName.text = USER.fullname
            settingsPhoneNumber.text = USER.phone
            tvOnline.text = USER.status
            settingsUsername.text = USER.username
            settingsAbout.text = USER.about
            settingsBtnChangeUsername.setOnClickListener { intentFragment(R.id.data_container, ChangeUserNameFragment()) }
            settingsBtnAboutUser.setOnClickListener { intentFragment(R.id.data_container, ChangeAboutFragment()) }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.settings_action_manu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_menu_change_exit -> {
                AUTH.signOut()
                (activity as MainActivity).intentActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> {
                intentFragment(R.id.data_container, ChangeNameFragment())
            }
        }
        return true
    }

}