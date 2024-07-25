package com.blackpuppydev.testcodemobile.api

import com.blackpuppydev.testcodemobile.api.response.Item
import com.blackpuppydev.testcodemobile.api.response.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemApi {

    @GET("/products")
    fun getProductItem():Call<Item>

    @GET("/products/{id}")
    fun getProduct(@Path("id") id:String):Call<Product>


}