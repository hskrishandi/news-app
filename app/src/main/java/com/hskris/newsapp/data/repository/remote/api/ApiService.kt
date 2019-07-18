package com.hskris.newsapp.data.repository.remote.api

import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?apiKey=" + Api.API_KEY)
    fun getHeadline(
        @Query("q") query: String = "",
        @Query("category") category: String = "",
        @Query("country") country: String = "id"
        ): Single<NewsResponse>
}