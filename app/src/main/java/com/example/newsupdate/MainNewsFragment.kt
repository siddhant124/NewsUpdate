package com.example.newsupdate

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsupdate.databinding.FragmentMainNewsBinding


class MainNewsFragment : Fragment(), NewsItemClicked {

    private lateinit var mainNewsBinding: FragmentMainNewsBinding
    private lateinit var mAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainNewsBinding = FragmentMainNewsBinding.inflate(inflater, container, false)
        mainNewsBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        fetchData()
        mAdapter = NewsListAdapter(this)
        mainNewsBinding.recyclerView.adapter = mAdapter

        mainNewsBinding.refreshLayout.setOnRefreshListener {
            fetchData()
            Toast.makeText(context, "Refreshing Done", Toast.LENGTH_SHORT).show()
            mainNewsBinding.refreshLayout.isRefreshing = false
        }

        return mainNewsBinding.root
    }

    private fun fetchData() {
        val newsUrl =
            "https://newsapi.org/v2/top-headlines?country=us&apiKey=ba37e8b185a24834837c12e258b4dfff"
        // Request a string response from the provided URL.
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, newsUrl, null,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length() - 1) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("publishedAt")
                    )
                    newsArray.add(news)
                }
                mAdapter.updatedNews(newsArray)
            },
            {
                Toast.makeText(activity, "Couldn't load news because $it", Toast.LENGTH_SHORT)
                    .show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        // Add the request to the RequestQueue.
        MySingleton.getInstance(requireContext()).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        val newsUrl = item.url
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(newsUrl))
    }

}
