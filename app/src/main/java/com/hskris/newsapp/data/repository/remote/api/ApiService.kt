package com.hskris.newsapp.data.repository.remote.api

import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("top-headlines?apiKey=" + Api.API_KEY)
    fun getHeadline(
        @QueryMap options: Map<String, String>
    ): Single<NewsResponse>

    @GET("everything?language=en&apiKey=" + Api.API_KEY)
    fun getEverything(
        @Query("q") keyword: String,
        @Query("from") from: String = "",
        @Query("to") to: String = ""
    ): Single<NewsResponse>
}