package com.blackpuppydev.testcodemobile.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blackpuppydev.testcodemobile.api.repository.ProductRepository
import com.blackpuppydev.testcodemobile.databinding.ActivityDetailBinding
import com.blackpuppydev.testcodemobile.dialog.LoadingDialog
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private var id:String? = null
    private var binding:ActivityDetailBinding? = null
    private var dialog: LoadingDialog? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        id = intent.getStringExtra("id")

        dialog = LoadingDialog(this@DetailActivity)
        dialog!!.show()
        ProductRepository.newInstance().getProduct(id!!){ product ->
            dialog!!.dismiss()
            binding!!.apply {
                Glide.with(applicationContext).load(product!!.images[0]).into(imageProduct)
                title.text = product.title
                stock.text = "Stock : " + product.stock.toString()
                price.text = "Price : " + product.price.toString()


                btnHome.setOnClickListener {
                    onBackPressed()
                }
            }

        }

    }

    override fun onResume() {
        super.onResume()


    }
}