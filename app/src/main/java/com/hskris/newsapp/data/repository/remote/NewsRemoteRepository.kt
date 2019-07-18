package com.hskris.newsapp.data.repository.remote

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.api.ApiService
import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import io.reactivex.Single

class NewsRemoteRepository(private val api: ApiService) : NewsRepository {
    override fun fetchHeadlines(query: NewsRepository.NewsQuery): Single<NewsResponse> {
        query as NewsRemoteQuery
        return api.getHeadline(query.keyword, query.category, query.country)
    }

    override fun fetchLatestByQuery(query: String, from: String, to: String): Single<NewsResponse> {
        return api.getEverything(query, from, to)
    }

    class NewsRemoteQuery : NewsRepository.NewsQuery {
        var keyword = ""
        var category = ""
        var country = "id"
        var from = ""
        var to = ""
        var language ="en"
    }
}
