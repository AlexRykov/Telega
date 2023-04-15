package com.example.telega.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import com.example.telega.R
import com.example.telega.activities.RegisterActivity
import com.example.telega.databinding.FragmentSettingsBinding
import com.example.telega.utilits.*
import com.google.firebase.storage.StorageReference
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

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
        with(mBinding) {
            tvFullName.text = USER.fullname
            settingsPhoneNumber.text = USER.phone
            tvOnline.text = USER.status
            settingsUsername.text = USER.username
            settingsAbout.text = USER.about
            settingsBtnChangeUsername.setOnClickListener {
                intentFragment(
                    R.id.data_container,
                    ChangeUserNameFragment()
                )
            }
            settingsBtnAboutUser.setOnClickListener {
                intentFragment(
                    R.id.data_container,
                    ChangeAboutFragment()
                )
            }
            settingsChangePhoto.setOnClickListener { changePhotoUser() }
            settingsUserPhoto.downloadAndSetImage(USER.photoUrl)
        }

    }

    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(800, 800)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE).child(CURRENT_UID)
            putImageToStorage(uri, path) {
                getURLFromStorage(path) {
                    putUrlToDB(it) {
                        mBinding.settingsUserPhoto.downloadAndSetImage(it)
                        showToast(getString(R.string.toast_data_updated))
                        USER.photoUrl = it
                    }
                }
            }
//            Previous variant down below

//            path.putFile(uri).addOnSuccessListener {
//                    path.downloadUrl.addOnCompleteListener { it2 ->
//                        if(it2.isSuccessful){
//                            val photoUrl = it2.result.toString()
//                            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(
//                                CHILD_PHOTO_URL).setValue(photoUrl)
//                                .addOnCompleteListener { it3 ->
//                                    if (it3.isSuccessful){
//                                        mBinding.settingsUserPhoto.downloadAndSetImage(photoUrl)
//                                        showToast(getString(R.string.toast_data_updated))
//                                        USER.photoUrl = photoUrl
//                                    }
//                                }
//                        }
//                    }
//                }
//            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.settings_action_manu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_change_exit -> {
                AUTH.signOut()
                APP_ACTIVITY.intentActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> {
                intentFragment(R.id.data_container, ChangeNameFragment())
            }
        }
        return true
    }

}