package com.hskris.newsapp.domain.models

import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class News (
    val title: String,
    val author: String?,
    val date: Date,
    val description: String?,
    val content: String?,
    val imageUrl: String?
) {
    companion object {
        fun parseResponseToModel(newsResponse: NewsResponse): List<News> {

            val articles = newsResponse.articles
            val newsArray = arrayListOf<News>()

            for(a in articles){
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                val date = formatter.parse(a.publishedAt)
                val news = News(a.title, a.author, date!!, a.description, a.content, a.urlToImage)
                newsArray.add(news)
            }

            return newsArray
        }
    }
}