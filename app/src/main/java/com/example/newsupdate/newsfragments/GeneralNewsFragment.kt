package com.example.newsupdate.newsfragments

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
import com.example.newsupdate.MySingleton
import com.example.newsupdate.News
import com.example.newsupdate.NewsItemClicked
import com.example.newsupdate.NewsListAdapter
import com.example.newsupdate.databinding.FragmentGeneralNewsBinding


open class GeneralNewsFragment : Fragment(), NewsItemClicked {

    private lateinit var generalNewsBinding: FragmentGeneralNewsBinding
    lateinit var mAdapter: NewsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        generalNewsBinding = FragmentGeneralNewsBinding.inflate(inflater, container, false)
        generalNewsBinding.recyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = NewsListAdapter(this)
        generalNewsBinding.recyclerView.adapter = mAdapter
        fetchData("general")
        generalNewsBinding.refreshLayout.setOnRefreshListener {
            fetchData("general")
            Toast.makeText(context, "Refreshing Done", Toast.LENGTH_SHORT).show()
            generalNewsBinding.refreshLayout.isRefreshing = false
        }

        return generalNewsBinding.root
    }

    fun fetchData(newsType: String) {
        val newsUrl =
            "https://newsapi.org/v2/top-headlines?country=in&category=$newsType&apiKey=ba37e8b185a24834837c12e258b4dfff"
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
