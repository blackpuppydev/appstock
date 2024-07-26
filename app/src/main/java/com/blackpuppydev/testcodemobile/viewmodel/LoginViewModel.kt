package com.blackpuppydev.testcodemobile.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.blackpuppydev.testcodemobile.AppPreference
import com.blackpuppydev.testcodemobile.dialog.StandardDialog
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {

    private var appPreference = AppPreference.getInstance()

    fun showDialog(context: Context,text:String){
        object : StandardDialog(context){
            override fun onCancelClick() {
                dismiss()
            }
        }.show(text)
    }


    fun setPreference(context: Context, username:String, password:String){
        appPreference.setSharedPreference(context)
        appPreference.setUsername(username)
        appPreference.setPassword(password)
    }


    fun validateMail(email: String?): Boolean {
        val regexMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = regexMail.matcher(email!!)
        return matcher.matches()
    }

    fun checkPassword(password:String):Boolean{
        return password.length >= 8
    }

    fun checkLogin():Boolean{
        return !appPreference?.getUsername().isNullOrEmpty() && !appPreference?.getPassword().isNullOrEmpty()
    }

}