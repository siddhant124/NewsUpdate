package com.example.newsupdate.newsfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.NewsListAdapter
import com.example.newsupdate.databinding.FragmentScienceNewsBinding


class ScienceNewsFragment : GeneralNewsFragment() {

    private lateinit var scienceNewsBinding: FragmentScienceNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        scienceNewsBinding = FragmentScienceNewsBinding.inflate(inflater, container, false)
        scienceNewsBinding.scienceNewsRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = NewsListAdapter(this)
        scienceNewsBinding.scienceNewsRecyclerView.adapter = mAdapter

        fetchData("science")

        scienceNewsBinding.scienceNewsRefreshLayout.setOnRefreshListener {
            fetchData("science")
            scienceNewsBinding.scienceNewsRefreshLayout.isRefreshing = false
        }
        return scienceNewsBinding.root
    }

}