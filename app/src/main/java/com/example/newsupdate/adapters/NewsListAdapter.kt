package com.example.newsupdate

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
        holder.newsDate.text = currentItem.publishedAt
        if (currentItem.author.isEmpty() or (currentItem.author == "null")) {
            holder.newsAuthor.text = ""
        } else {
            holder.newsAuthor.text = "Author \"${currentItem.author}\""
        }
        if (currentItem.description.isEmpty() or (currentItem.description == "null")) {
            holder.newsDescription.text = "Click here to read news"
        } else {
            holder.newsDescription.text = currentItem.description
        }

        holder.newsShareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/html"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Checkout this news, I found it on Samachar ${currentItem.url}"
            )
            val chooser = Intent.createChooser(shareIntent, "Share this news using...")
            holder.itemView.context.startActivity(chooser)
        }

        holder.newsBookmarkButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Bookmarked clicked", Toast.LENGTH_SHORT).show()
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
    val newsDescription: TextView = itemView.findViewById(R.id.newsDescription)
    val newsAuthor: TextView = itemView.findViewById(R.id.newsAuthor)
    val newsDate: TextView = itemView.findViewById(R.id.newsDate)
    val newsImage: ImageView = itemView.findViewById(R.id.news_img)
    val newsShareButton: Button = itemView.findViewById(R.id.shareNews)
    val newsBookmarkButton: Button = itemView.findViewById(R.id.bookMarkNews)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}
