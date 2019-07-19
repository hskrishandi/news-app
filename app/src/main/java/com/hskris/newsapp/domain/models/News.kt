package com.hskris.newsapp.domain.models

import java.util.Date

data class News (
    val title: String,
    val author: String,
    val date: Date,
    val description: String,
    val content: String,
    val imageUrl: String
)