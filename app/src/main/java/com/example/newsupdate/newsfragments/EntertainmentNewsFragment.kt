package com.example.newsupdate.newsfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.NewsListAdapter
import com.example.newsupdate.R
import com.example.newsupdate.databinding.FragmentEntertainmentNewsBinding

class EntertainmentNewsFragment : GeneralNewsFragment() {

    private lateinit var entertainmentNewsBinding: FragmentEntertainmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        entertainmentNewsBinding = FragmentEntertainmentNewsBinding.inflate(inflater, container, false)
        entertainmentNewsBinding.entertainmentNewsRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = NewsListAdapter(this)
        entertainmentNewsBinding.entertainmentNewsRecyclerView.adapter = mAdapter

        fetchData("entertainment")

        entertainmentNewsBinding.entertainmentNewsRefreshLayout.setOnRefreshListener {
            fetchData("entertainment")
            Toast.makeText(context, "Refreshing Done", Toast.LENGTH_SHORT).show()
            entertainmentNewsBinding.entertainmentNewsRefreshLayout.isRefreshing = false
        }

        return entertainmentNewsBinding.root

    }
}