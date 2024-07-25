package com.blackpuppydev.testcodemobile.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blackpuppydev.testcodemobile.AppPreference
import com.blackpuppydev.testcodemobile.databinding.ActivityLoginBinding
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {

    private var binding:ActivityLoginBinding? = null
    private var appPreference:AppPreference? = AppPreference.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        appPreference?.setSharedPreference(this)

        if (checkLogin()){
            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
        }

        binding?.apply {
            btnLogin.setOnClickListener {
                if (!editMail.text.isNullOrEmpty() && !editPassword.text.isNullOrEmpty()){
                    if (validateMail(editMail.text.toString()) && checkPassword(editPassword.text.toString())){
                        appPreference?.setUsername(editMail.text.toString())
                        appPreference?.setPassword(editPassword.text.toString())
                        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                    } else {
                        Toast.makeText(this@LoginActivity,"Email or password incorrect",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }



    private fun validateMail(email: String?): Boolean {
        val regexMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = regexMail.matcher(email!!)
        return matcher.matches()
    }

    private fun checkPassword(password:String):Boolean{
        return password.length >= 8
    }

    private fun checkLogin():Boolean{
        return !appPreference?.getUsername().isNullOrEmpty() && !appPreference?.getPassword().isNullOrEmpty()
    }


}