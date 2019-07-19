package com.hskris.newsapp.domain

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.NewsRemoteRepository
import com.hskris.newsapp.domain.models.News
import io.reactivex.Single

class GetEverythingUseCase(
    private val repo: NewsRepository,
    private val scheduler: BaseSchedulerProvider,
    val language: String = "en"
) {

    fun getByKeywords(keywords: String): Single<List<News>> {
        val query = NewsRemoteRepository.NewsRemoteQuery()
        query.add("language", language)
        query.add("q", keywords)

        val result = repo.fetchEverything(query)

        return result.subscribeOn(scheduler.io())
            .map {newsResponse -> News.parseResponseToModel(newsResponse)}
            .observeOn(scheduler.ui())
    }
}