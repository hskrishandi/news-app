package com.hskris.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
                    rvHeadlines.visibility = View.INVISIBLE
                    loadingNews.visibility = View.VISIBLE
                }
                is NewsViewModel.NewsState.Data -> {
                    rvHeadlines.visibility = View.VISIBLE
                    loadingNews.visibility = View.INVISIBLE
                    newsCardAdapter.updateNews(state.news)
                }
                is NewsViewModel.NewsState.Error -> {
                    Toast.makeText(this, "Fail to get news", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.newsState.observe(this, newsObserver)
        viewModel.getNews("general")

        val carousellObserver = Observer<NewsViewModel.CarousellState>{ state ->
            when(state){
                is NewsViewModel.CarousellState.Loading -> {
                    loadingCarousell.visibility = View.VISIBLE
                }
                is NewsViewModel.CarousellState.Data -> {
                    loadingCarousell.visibility = View.INVISIBLE
                    carousellAdapter.updateNews(state.news)
                }
                is NewsViewModel.CarousellState.Error -> {
                    Toast.makeText(this, "Fail to get carousell news", Toast.LENGTH_LONG).show()
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