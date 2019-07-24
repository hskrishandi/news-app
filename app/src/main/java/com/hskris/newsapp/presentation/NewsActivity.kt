package com.hskris.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hskris.newsapp.R
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_news.rvHeadlines
import org.koin.android.ext.android.inject

class NewsActivity : AppCompatActivity() {

    companion object {
        private val categories = arrayOf(
            "general",
            "business",
            "health"
        )
    }

    private val viewModel: NewsViewModel by inject()
    private val carousellAdapter = NewsCarousellAdapter(emptyList())
    private val newsCardAdapter = NewsCardAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        rvLatestNews.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = carousellAdapter
        }
        rvHeadlines.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsCardAdapter
        }

        val newsObserver = Observer<NewsViewModel.NewsState>{ state ->
            when(state){
                is NewsViewModel.NewsState.Loading -> {
                    Log.d("NewsActivity", "Loading data")
                }
                is NewsViewModel.NewsState.Data -> {
                    newsCardAdapter.updateNews(state.news)
                }
                is NewsViewModel.NewsState.Error -> {
                    Log.e("NewsActivity", "Error")
                }
            }
        }

        viewModel.newsState.observe(this, newsObserver)
        viewModel.getNews("general")

        val carousellObserver = Observer<NewsViewModel.CarousellState>{ state ->
            when(state){
                is NewsViewModel.CarousellState.Loading -> {
                    Log.d("NewsActivity", "Loading data")
                }
                is NewsViewModel.CarousellState.Data -> {
                    carousellAdapter.updateNews(state.news)
                }
                is NewsViewModel.CarousellState.Error -> {
                    Log.e("NewsActivity", "Error")
                }
            }
        }


        viewModel.carousellState.observe(this, carousellObserver)
        viewModel.getCarousell()

        setupTabs()

        tabsNews.tabMode = TabLayout.MODE_SCROLLABLE
        tabsNews.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewModel.getNews(categories[p0!!.position])
            }

        })
    }

    private fun setupTabs() {
        for (category in categories) {
            tabsNews.addTab(tabsNews.newTab().setText(category))
        }
    }
}