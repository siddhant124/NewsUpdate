package com.example.newsupdate.newsfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsupdate.NewsListAdapter
import com.example.newsupdate.databinding.FragmentTechnologyNewsBinding


class TechnologyNewsFragment : GeneralNewsFragment() {

    private lateinit var technologyNewsBinding: FragmentTechnologyNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        technologyNewsBinding = FragmentTechnologyNewsBinding.inflate(inflater, container, false)
        technologyNewsBinding.techNewsRecyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = NewsListAdapter(this)
        technologyNewsBinding.techNewsRecyclerView.adapter = mAdapter

        fetchData("technology")

        technologyNewsBinding.techNewsRefreshLayout.setOnRefreshListener {
            fetchData("technology")
            technologyNewsBinding.techNewsRefreshLayout.isRefreshing = false
        }

        return technologyNewsBinding.root
    }
}