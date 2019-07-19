package com.hskris.newsapp.domain

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.NewsRemoteRepository
import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import com.hskris.newsapp.domain.models.News
import io.reactivex.Single
import java.util.*

class GetHeadlineUseCase(private val repo: NewsRepository, private val scheduler: BaseSchedulerProvider) {

    fun getByCategory(category: String): Single<List<News>> {

        val query = NewsRemoteRepository.NewsRemoteQuery()
        query.add("category", category)

        val result = repo.fetchHeadlines(query)

        return result.subscribeOn(scheduler.io())
            .map {newsResponse -> parseResponseToModel(newsResponse)}
            .observeOn(scheduler.ui())
    }

    private fun parseResponseToModel(newsResponse: NewsResponse): List<News> {

        val articles = newsResponse.articles
        val newsArray = arrayListOf<News>()

        for(a in articles){
            val news = News(a.title, a.author, Date(a.publishedAt), a.description, a.content, a.urlToImage)
            newsArray.add(news)
        }

        return newsArray
    }

}