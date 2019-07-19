package com.hskris.newsapp.domain.models

import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import java.util.Date

class News (
    val title: String,
    val author: String,
    val date: Date,
    val description: String,
    val content: String,
    val imageUrl: String
) {
    companion object {
        fun parseResponseToModel(newsResponse: NewsResponse): List<News> {

            val articles = newsResponse.articles
            val newsArray = arrayListOf<News>()

            for(a in articles){
                val news = News(a.title, a.author, Date(a.publishedAt), a.description, a.content, a.urlToImage)
                newsArray.add(news)
            }

            return newsArray
        }
    }
}