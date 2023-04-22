package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.telega.R
import com.example.telega.databinding.FragmentSingleChatBinding
import com.example.telega.models.CommonModel
import com.example.telega.models.UserModel
import com.example.telega.utilits.*
import com.google.firebase.database.DatabaseReference


class SingleChatFragment(private val contact: CommonModel) : BaseFragment(R.layout.fragment_single_chat) {

    private lateinit var mListenerInfoToolbar:AppValueEventListener
    private lateinit var mReceivingUser: UserModel
    private lateinit var mToolbarInfo:View
    private lateinit var mRefUser:DatabaseReference

    private lateinit var mBinding: FragmentSingleChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSingleChatBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        mToolbarInfo =APP_ACTIVITY.mToolbar.findViewById<ConstraintLayout>(R.id.toolbar_info)
        mToolbarInfo.visibility = View.VISIBLE
        mListenerInfoToolbar = AppValueEventListener {
            mReceivingUser = it.getUserModel()
            initInfoToolbar()
        }
//          lesson 37       9:11
        mRefUser = REF_DATABASE_ROOT.child(NODE_USERS).child(contact.id)
        mRefUser.addValueEventListener(mListenerInfoToolbar)
    }

    private fun initInfoToolbar() {
        val t = mToolbarInfo.findViewById<ImageView>(R.id.toolbar_image)
//        val image = view?.findViewById<ImageView>(R.id.toolbar_image)
        val fname = mToolbarInfo.findViewById<TextView>(R.id.toolbar_fullname)
        val status = mToolbarInfo.findViewById<TextView>(R.id.toolbar_status)
        t.downloadAndSetImage(mReceivingUser.photoUrl)
        fname.text = mReceivingUser.fullname
        status.text = mReceivingUser.state
    }

    override fun onPause() {
        super.onPause()
        mToolbarInfo.visibility = View.GONE
//          lesson 37       9:11
        mRefUser.removeEventListener(mListenerInfoToolbar)
    }
}