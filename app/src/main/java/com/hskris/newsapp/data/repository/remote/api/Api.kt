package com.hskris.newsapp.data.repository.remote.api

import com.hskris.newsapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    companion object {
        private const val API_URL = BuildConfig.API_URL
        const val API_KEY = BuildConfig.API_KEY

        private var retrofit: Retrofit? = null

        fun getInstance(): ApiService {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit
                    .Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiService::class.java)
        }
    }

}