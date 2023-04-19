package com.example.telega.ui.objects

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.telega.R
import com.example.telega.ui.fragment.ContactsFragment
import com.example.telega.ui.fragment.SettingsFragment
import com.example.telega.utilits.APP_ACTIVITY
import com.example.telega.utilits.USER
import com.example.telega.utilits.downloadAndSetImage
import com.example.telega.utilits.intentFragment
import com.mikepenz.iconics.Iconics.applicationContext
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import java.text.FieldPosition

class AppDrawer {
//    32 lesson delete it
//class AppDrawer(val mainActivity: AppCompatActivity, val toolbar: Toolbar) {
    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mCurrentProfile: ProfileDrawerItem


    fun create() {
        initLoader()
        crateHeader()
        createDrawer()
        mDrawerLayout = mDrawer.drawerLayout
    }

    fun disableDrawer() {
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        Lock Drawer in Close (Invisible) Mode
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
            APP_ACTIVITY.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer() {
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
            APP_ACTIVITY.mAppDrawer.updateHeader()
            mDrawer.openDrawer()
        }
    }

    //      Create left side thing with options
    private fun createDrawer() {
        mDrawer = DrawerBuilder()
            .withActivity(APP_ACTIVITY)
            .withToolbar(APP_ACTIVITY.mToolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Create Group")
                    .withSelectable(false)
                    .withIcon(R.drawable.people_outline),
                PrimaryDrawerItem().withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("Start SecretChat")
                    .withSelectable(false)
                    .withIcon(R.drawable.lock),
                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Create Channel")
                    .withSelectable(false)
                    .withIcon(R.drawable.flight_takeoff),
                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Contacts")
                    .withSelectable(false)
                    .withIcon(R.drawable.person_outline),
                PrimaryDrawerItem().withIdentifier(104)
                    .withIconTintingEnabled(true)
                    .withName("Calls")
                    .withSelectable(false)
                    .withIcon(R.drawable.settings_phone),
                PrimaryDrawerItem().withIdentifier(105)
                    .withIconTintingEnabled(true)
                    .withName("Favourites")
                    .withSelectable(false)
                    .withIcon(R.drawable.bookmark),
                PrimaryDrawerItem().withIdentifier(106)
                    .withIconTintingEnabled(true)
                    .withName("Settings")
                    .withSelectable(false)
                    .withIcon(R.drawable.settings),
                DividerDrawerItem(),
                PrimaryDrawerItem().withIdentifier(107)
                    .withIconTintingEnabled(true)
                    .withName("Invite Friends")
                    .withSelectable(false)
                    .withIcon(R.drawable.person_add_alt),
                PrimaryDrawerItem().withIdentifier(108)
                    .withIconTintingEnabled(true)
                    .withName("Questions About")
                    .withSelectable(false)
                    .withIcon(R.drawable.contact_support)
            )
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    clickToItem(position)
                    Toast.makeText(applicationContext, "Click on $position", Toast.LENGTH_SHORT)
                        .show()
                    return false
                }
            })
            .build()
    }

    private fun clickToItem(position: Int){
        when (position) {
            7 -> APP_ACTIVITY.intentFragment(R.id.data_container, SettingsFragment())
            4 -> APP_ACTIVITY.intentFragment(R.id.data_container, ContactsFragment())
        }
    }

    //      Create top side thing with app name
    private fun crateHeader() {
        mCurrentProfile = ProfileDrawerItem()
            .withName(USER.fullname)
            .withEmail(USER.phone)
            .withIcon(USER.photoUrl)
            .withIdentifier(200)
        mHeader = AccountHeaderBuilder()
            .withActivity(APP_ACTIVITY)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                mCurrentProfile
            )
            .build()
    }

    fun updateHeader() {
        mCurrentProfile = ProfileDrawerItem()
            .withName(USER.fullname)
            .withEmail(USER.phone)
            .withIcon(USER.photoUrl)
//                lesson 24 this string down below does not exist there, but I added it
            .withIdentifier(200)

        mHeader.updateProfile(mCurrentProfile)
    }

    private fun initLoader() {
        DrawerImageLoader.init(object : AbstractDrawerImageLoader()
        {
            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable) {
                imageView.downloadAndSetImage(uri.toString())
            }
        }
        )
    }
}