package com.blackpuppydev.testcodemobile.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {


    companion object {

        fun getRetrofit() : Retrofit {
            val builder: Retrofit.Builder = Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .addConverterFactory(getConvertFactory())
                .client(getOkHttpClient())
            return builder.build()
        }


        private fun getOkHttpClient(): OkHttpClient? {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val request: Request =
                    chain.request().newBuilder()
                        .addHeader("CMReq", "request")
                        .addHeader("CMERes","response").build()
                chain.proceed(request)
            }
            return httpClient.build()
        }

        private fun getConvertFactory(): GsonConverterFactory {
            return GsonConverterFactory.create(
                GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create())
        }



    }
}