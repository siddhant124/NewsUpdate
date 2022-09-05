package com.example.newsupdate.newsfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.NewsListAdapter
import com.example.newsupdate.R
import com.example.newsupdate.databinding.FragmentHealthNewsBinding

class HealthNewsFragment : GeneralNewsFragment() {

    private lateinit var healthNewsBinding: FragmentHealthNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        healthNewsBinding = FragmentHealthNewsBinding.inflate(inflater, container, false)
        healthNewsBinding.healthNewsRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = NewsListAdapter(this)
        healthNewsBinding.healthNewsRecyclerView.adapter = mAdapter

        fetchData("health")

        healthNewsBinding.healthNewsRefreshLayout.setOnRefreshListener {
            fetchData("health")
            healthNewsBinding.healthNewsRefreshLayout.isRefreshing = false
        }

        return healthNewsBinding.root
    }


}