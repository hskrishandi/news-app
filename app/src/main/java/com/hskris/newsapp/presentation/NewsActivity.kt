package com.hskris.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hskris.newsapp.R
import com.hskris.newsapp.domain.models.News
import kotlinx.android.synthetic.main.activity_news.*
import org.koin.android.ext.android.inject

class NewsActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by inject()
    private val newsCardAdapter = NewsCardAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        recViewFrontNews.layoutManager = LinearLayoutManager(this)
        recViewFrontNews.adapter = newsCardAdapter

        val frontNewsObserver = Observer<List<News>>{
            newsCardAdapter.updateNews(it)
        }

        viewModel.news.observe(this, frontNewsObserver)

        viewModel.getFrontNews()

    }
}