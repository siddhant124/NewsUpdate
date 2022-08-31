package com.example.newsupdate.newsfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.NewsListAdapter
import com.example.newsupdate.databinding.FragmentBusinessNewsBinding

class BusinessNewsFragment : GeneralNewsFragment() {

    private lateinit var businessNewsBinding: FragmentBusinessNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        businessNewsBinding = FragmentBusinessNewsBinding.inflate(inflater, container, false)
        businessNewsBinding.businessNewsRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = NewsListAdapter(this)
        businessNewsBinding.businessNewsRecyclerView.adapter = mAdapter

        fetchData("business")

        businessNewsBinding.businessNewsRefreshLayout.setOnRefreshListener {
            fetchData("business")
            Toast.makeText(context, "Refreshing Done", Toast.LENGTH_SHORT).show()
            businessNewsBinding.businessNewsRefreshLayout.isRefreshing = false
        }

        return businessNewsBinding.root
    }
}