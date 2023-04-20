package com.example.telega.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.telega.R
import com.example.telega.databinding.FragmentContactsBinding
import com.example.telega.models.CommonModel
import com.example.telega.utilits.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import de.hdodenhof.circleimageview.CircleImageView


class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: FirebaseRecyclerAdapter<CommonModel,ContactHolder>
    private lateinit var mRefContacts:DatabaseReference
    private lateinit var mRefUsers:DatabaseReference

    private lateinit var mBinding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentContactsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
        APP_ACTIVITY.title = getString(R.string.fragment_contacts_title)

    }

    private fun initRecyclerView() {
        //              lesson 33


        mRecyclerView = mBinding.contactsRecycleView
        mRefContacts = REF_DATABASE_ROOT.child(NODE_PHONES_CONTACTS).child(CURRENT_UID)

        val options = FirebaseRecyclerOptions.Builder<CommonModel>()
            .setQuery(mRefContacts, CommonModel::class.java)
            .build()

        mAdapter = object : FirebaseRecyclerAdapter<CommonModel, ContactHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
                return ContactHolder(view)
            }

            override fun onBindViewHolder(
                holder: ContactHolder,
                position: Int,
                model: CommonModel
            ) {


                mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS).child(model.id)
                mRefUsers.addValueEventListener(AppValueEventListener{

                    val contact = it.getCommonModel()
                    holder.a.text = contact.fullname
                    holder.s.text = contact.state
                    holder.f.downloadAndSetImage(contact.photoUrl)
                })

            }

        }

        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    class ContactHolder(viewContact: View): RecyclerView.ViewHolder(viewContact){

        val a: TextView = viewContact.findViewById(R.id.contact_fullname)
        val s: TextView = viewContact.findViewById(R.id.contact_f)
        val f: CircleImageView = viewContact.findViewById(R.id.contact_photo)

    }


    override fun onStop() {
        super.onStop()
        APP_ACTIVITY.title = getString(R.string.app_name)
        mAdapter.stopListening()
    }
}

