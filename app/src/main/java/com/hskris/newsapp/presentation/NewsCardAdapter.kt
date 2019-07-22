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

class NewsCardAdapter(var items: List<News>) : RecyclerView.Adapter<NewsCardAdapter.NewsCardItem>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCardItem {
        return NewsCardItem(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_card,
                parent,
                false
        ))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsCardItem, position: Int) {
        val item = items[position]
        Picasso.get().load(item.imageUrl).into(holder.image)

        holder.title.text = item.title
        holder.desc.text = item.description
    }

    fun updateNews(newItems: List<News>) {
        items = newItems
        notifyDataSetChanged()
    }

    class NewsCardItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageViewCard)
        val title: TextView = itemView.findViewById(R.id.textViewTitleCard)
        val desc: TextView = itemView.findViewById(R.id.textViewDescCard)
    }

}