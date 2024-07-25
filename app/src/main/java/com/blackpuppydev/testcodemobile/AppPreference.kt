package com.blackpuppydev.testcodemobile

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class AppPreference {

    private var sharedPreference: SharedPreferences? = null

    companion object{

        private var instance:AppPreference? = null

        fun getInstance():AppPreference {
            if(instance == null) instance = AppPreference()
            return instance!!
        }
    }

    fun setSharedPreference(context: Context){
        sharedPreference = context.getSharedPreferences("app", MODE_PRIVATE)
    }

    fun setUsername(username:String){
        sharedPreference?.edit()?.putString("username",username)?.apply()
    }

    fun getUsername(): String? {
        return sharedPreference?.getString("username", "")
    }

    fun setPassword(password:String){
        sharedPreference?.edit()?.putString("password",password)?.apply()
    }

    fun getPassword(): String? {
        return sharedPreference?.getString("password", "")
    }


}