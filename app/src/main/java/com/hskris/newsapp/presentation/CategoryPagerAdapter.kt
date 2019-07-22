package com.hskris.newsapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CategoryPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    companion object {
        private const val CATEGORY_GENERAL = "general"
        private const val CATEGORY_ENTERTAINMENT = "entertainment"
        private const val CATEGORY_TECHNOLOGY = "technology"
    }

    private val pages = listOf(
        CategoryFragment(CATEGORY_GENERAL),
        CategoryFragment(CATEGORY_ENTERTAINMENT),
        CategoryFragment(CATEGORY_TECHNOLOGY)
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }
}