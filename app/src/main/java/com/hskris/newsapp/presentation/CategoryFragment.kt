package com.hskris.newsapp.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.hskris.newsapp.R
import com.hskris.newsapp.domain.models.News
import kotlinx.android.synthetic.main.fragment_category.*
import org.koin.android.ext.android.inject

class CategoryFragment(val category: String) : Fragment() {

    private val viewModel: NewsViewModel by inject()
    private val newsCardAdapter = NewsCardAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvHeadlines.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsCardAdapter
        }

        val newsObserver = Observer<List<News>>{
            newsCardAdapter.updateNews(it)
        }

        viewModel.news.observe(this, newsObserver)
        viewModel.getNews(category)
    }
}
