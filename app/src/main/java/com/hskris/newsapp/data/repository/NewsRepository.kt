package com.hskris.newsapp.data.repository

import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import io.reactivex.Single

interface NewsRepository {
    fun fetchHeadline(query: String = ""): Single<NewsResponse>

    fun fetchByCategory(category: String): Single<NewsResponse>
}