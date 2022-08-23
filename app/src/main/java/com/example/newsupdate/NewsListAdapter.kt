package com.example.newsupdate

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked) :
    RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }



        return viewHolder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.newsImage)
        holder.newsHeadline.text = currentItem.title
        holder.newsDescription.text = currentItem.description
        holder.newsDate.text = currentItem.publishedAt
        if (currentItem.author.isEmpty() or (currentItem.author == "null")) {
            holder.newsAuthor.text = ""
        } else {
            holder.newsAuthor.text = "Author \"${currentItem.author}\""
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatedNews(updatedItem: ArrayList<News>) {
        items.clear()
        items.addAll(updatedItem)
        notifyDataSetChanged()
    }
}


class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val newsHeadline: TextView = itemView.findViewById(R.id.newsHeadline)
    val newsDescription: TextView = itemView.findViewById(R.id.newsHeadline)
    val newsAuthor: TextView = itemView.findViewById(R.id.newsAuthor)
    val newsDate: TextView = itemView.findViewById(R.id.newsDate)
    val newsImage: ImageView = itemView.findViewById(R.id.news_img)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}
