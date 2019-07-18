package com.hskris.newsapp.data.repository

import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import io.reactivex.Single

interface NewsRepository {

    fun fetchHeadlines(query: NewsQuery): Single<NewsResponse>

    fun fetchLatestByQuery(query: String, from: String = "", to: String): Single<NewsResponse>

    interface NewsQuery
}