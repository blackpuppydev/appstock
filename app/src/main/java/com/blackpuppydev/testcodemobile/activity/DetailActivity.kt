package com.blackpuppydev.testcodemobile.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blackpuppydev.testcodemobile.api.repository.ProductRepository
import com.blackpuppydev.testcodemobile.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private var id:String? = null
    private var binding:ActivityDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        id = intent.getStringExtra("id")

        ProductRepository.newInstance().getProduct(id!!){ product ->

            binding!!.apply {
                Glide.with(applicationContext).load(product!!.images[0]).into(imageProduct)
                title.text = product.title
                stock.text = product.stock.toString()
                price.text = product.price.toString()


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