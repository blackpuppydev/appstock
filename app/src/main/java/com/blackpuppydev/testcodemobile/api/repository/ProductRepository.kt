package com.blackpuppydev.testcodemobile.api.repository

import android.util.Log
import com.blackpuppydev.testcodemobile.api.ApiManager
import com.blackpuppydev.testcodemobile.api.ItemApi
import com.blackpuppydev.testcodemobile.api.response.Item
import com.blackpuppydev.testcodemobile.api.response.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    companion object {

        private var instance: ProductRepository? = null
        fun newInstance(): ProductRepository {
            if (instance == null) instance = ProductRepository()
            return instance!!
        }

    }

    fun getProductItem(callback:(res:ArrayList<Product>?) -> Unit){

        ApiManager.getRetrofit().create(ItemApi::class.java).getProductItem().enqueue(object : Callback<Item>{
            override fun onResponse(call: Call<Item>?, response: Response<Item>?) {
                val p  = response!!.body().products
                for (pro in p){
                    pro.totalPrice = pro.price - (pro.price * (pro.discountPercentage/100))
                }
                callback.invoke(p)
            }

            override fun onFailure(call: Call<Item>?, t: Throwable?) {
                callback.invoke(null)
            }

        })
    }


    fun getProduct(id:String,callback: (res: Product?) -> Unit){

        ApiManager.getRetrofit().create(ItemApi::class.java).getProduct(id).enqueue(object : Callback<Product>{
            override fun onResponse(call: Call<Product>?, response: Response<Product>?) {
                callback.invoke(response!!.body())
            }

            override fun onFailure(call: Call<Product>?, t: Throwable?) {
                callback.invoke(null)
            }
        })
    }

}