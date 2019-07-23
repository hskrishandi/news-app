package com.hskris.newsapp.domain

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.NewsRemoteRepository
import com.hskris.newsapp.domain.models.News
import io.reactivex.Single

class GetHeadlineUseCase(
    private val repo: NewsRepository,
    private val scheduler: BaseSchedulerProvider,
    val country: String = "us"
) {
    fun getByCategory(category: String): Single<List<News>> {

        val query = NewsRemoteRepository.NewsRemoteQuery()
        query.add("country", country)
        query.add("category", category)

        val result = repo.fetchHeadlines(query)

        return result
            .map { newsResponse -> News.parseResponseToModel(newsResponse) }
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }

}