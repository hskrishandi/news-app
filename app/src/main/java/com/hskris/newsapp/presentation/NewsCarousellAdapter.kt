package com.hskris.newsapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hskris.newsapp.R
import com.hskris.newsapp.domain.models.News
import com.squareup.picasso.Picasso

class NewsCarousellAdapter(var items: List<News>) : RecyclerView.Adapter<NewsCarousellAdapter.NewsCarousellItem>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCarousellItem {
        return NewsCarousellItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_carousell,
                    parent,
                    false
                ))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsCarousellItem, position: Int) {
        val item = items[position]
        Picasso.get().load(item.imageUrl).into(holder.image)

        holder.title.text = item.title
        holder.description.text = item.description
    }

    fun updateNews(newItems: List<News>) {
        items = newItems
        notifyDataSetChanged()
    }

    class NewsCarousellItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageViewCarousell)
        val title: TextView = itemView.findViewById(R.id.textViewTitleCarousell)
        val description: TextView = itemView.findViewById(R.id.textViewDescriptionCarousell)
    }

}