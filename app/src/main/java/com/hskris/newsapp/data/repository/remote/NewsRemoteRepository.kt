package com.hskris.newsapp.data.repository.remote

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.api.ApiService
import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import io.reactivex.Single

class NewsRemoteRepository(private val api: ApiService) : NewsRepository {
    override fun fetchHeadlines(query: NewsRepository.NewsQuery): Single<NewsResponse> {
        return api.getHeadline((query as NewsRemoteQuery).options)
    }

    override fun fetchEverything(query: NewsRepository.NewsQuery): Single<NewsResponse> {
        return api.getEverything((query as NewsRemoteQuery).options)
    }

    class NewsRemoteQuery : NewsRepository.NewsQuery {
        val options = hashMapOf<String, String>()

        fun add(key: String, value: String){
            options[key] = value
        }

        fun add(map: HashMap<String, String>){
            options.putAll(map)
        }
    }
}
