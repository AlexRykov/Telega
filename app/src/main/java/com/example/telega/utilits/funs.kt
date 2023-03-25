package com.example.telega.utilits

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.showToast(msg:String){
    Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
}
fun AppCompatActivity.intentActivity(activity: AppCompatActivity){
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun AppCompatActivity.intentFragment(container:Int, fragment: Fragment , addStack : Boolean = true){
    if(addStack){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(container, fragment)
            .commit()
    } else {
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
    }

}

fun Fragment.intentFragment(container:Int, fragment: Fragment){
    fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(container, fragment)
        ?.commit()
}