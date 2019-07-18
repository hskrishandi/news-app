package com.hskris.newsapp.data.repository.remote

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.api.ApiService
import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import io.reactivex.Single

class NewsRemoteRepository(private val api: ApiService) : NewsRepository {
    override fun fetchHeadline(query: String): Single<NewsResponse> {
        return api.getHeadline(query)
    }
}