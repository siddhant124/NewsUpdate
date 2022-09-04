package com.example.newsupdate.newsfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.NewsListAdapter
import com.example.newsupdate.databinding.FragmentSportsNewsBinding


class SportsNewsFragment : GeneralNewsFragment() {
    private lateinit var sportsNewsBinding: FragmentSportsNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        sportsNewsBinding = FragmentSportsNewsBinding.inflate(inflater, container, false)
        sportsNewsBinding.sportsNewsRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = NewsListAdapter(this)
        sportsNewsBinding.sportsNewsRecyclerView.adapter = mAdapter

        fetchData("sports")

        sportsNewsBinding.sportsNewsRefreshLayout.setOnRefreshListener {
            fetchData("sports")
            sportsNewsBinding.sportsNewsRefreshLayout.isRefreshing = false
        }

        return sportsNewsBinding.root
    }
}