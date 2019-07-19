package com.hskris.newsapp.domain

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.NewsRemoteRepository
import com.hskris.newsapp.domain.models.News
import io.reactivex.Single

class GetHeadlineUseCase(private val repo: NewsRepository, private val scheduler: BaseSchedulerProvider) {

    fun getByCategory(category: String): Single<List<News>> {

        val query = NewsRemoteRepository.NewsRemoteQuery()
        query.add("category", category)

        val result = repo.fetchHeadlines(query)

        return result.subscribeOn(scheduler.io())
            .map {newsResponse -> News.parseResponseToModel(newsResponse)}
            .observeOn(scheduler.ui())
    }

}