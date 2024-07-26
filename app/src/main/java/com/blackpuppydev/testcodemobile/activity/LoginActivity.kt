package com.blackpuppydev.testcodemobile.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.blackpuppydev.testcodemobile.AppPreference
import com.blackpuppydev.testcodemobile.databinding.ActivityLoginBinding
import com.blackpuppydev.testcodemobile.viewmodel.LoginViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {

    private var binding:ActivityLoginBinding? = null
    private var viewModel:LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        viewModel = ViewModelProvider(this@LoginActivity)[LoginViewModel::class.java]

        if (viewModel!!.checkLogin()){
            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
        }

        binding?.apply {
            btnLogin.setOnClickListener {

                if (!editMail.text.isNullOrEmpty() && !editPassword.text.isNullOrEmpty()
                    && editMail.text.isNotBlank() && editPassword.text.isNotBlank()){

                    if (viewModel!!.validateMail(editMail.text.toString()) && viewModel!!.checkPassword(editPassword.text.toString())){
                        viewModel!!.setPreference(applicationContext,editMail.text.toString(),editPassword.text.toString())

                        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))

                    } else viewModel!!.showDialog(this@LoginActivity,"Email or password incorrect")

                } else viewModel!!.showDialog(this@LoginActivity,"Email or password incorrect")
            }
        }
    }

}