package com.blackpuppydev.testcodemobile.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.blackpuppydev.testcodemobile.databinding.DialogBaseBinding

abstract class StandardDialog(context: Context) : Dialog(context) {

    private var binding: DialogBaseBinding = DialogBaseBinding.inflate(layoutInflater)

    init{
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
        setCancelable(true)

        binding.btnCancel.setOnClickListener {
            onCancelClick()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    private fun setMessage(title: String?) {
        binding.title.text = title
    }

    fun show(title: String?){
        setMessage(title)
        setCancelable(false)
        show()
    }

    abstract fun onCancelClick()



}