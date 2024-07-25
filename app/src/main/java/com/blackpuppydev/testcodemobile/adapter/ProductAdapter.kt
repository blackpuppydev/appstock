package com.blackpuppydev.testcodemobile.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackpuppydev.testcodemobile.api.response.Product
import com.blackpuppydev.testcodemobile.databinding.AdapterProductBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.withContext

abstract class ProductAdapter(private var listProduct:ArrayList<Product>,var showPricePiece:Boolean,var showTotalPrice:Boolean): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private var binding: AdapterProductBinding):RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("CheckResult", "SetTextI18n")
        fun setProduct(product:Product){
            binding.apply {
                Glide.with(itemView.context).load(product.thumbnail).into(imageProduct)
                title.text = product.title
                price.text = "Price : " + product.price.toString()
                stock.text = "Stock : " + product.stock.toString()

                if (showPricePiece) {
                    totalPrice.visibility = View.VISIBLE
                    totalPrice.text = "Total price / piece : " + product.totalPrice.toString()
                } else totalPrice.visibility = View.GONE

                if (showTotalPrice){
                    allPrice.visibility = View.VISIBLE
                    allPrice.text = "Total : " + (product.totalPrice * product.stock)
                } else allPrice.visibility = View.GONE


                btnDetail.setOnClickListener {
                    onSelectProduct(product.id)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = AdapterProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setProduct(listProduct[position])
    }

    abstract fun onSelectProduct(id:String)


}