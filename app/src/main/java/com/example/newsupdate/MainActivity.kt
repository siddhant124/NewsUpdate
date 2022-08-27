package com.example.newsupdate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.newsupdate.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout
    private lateinit var bar: Toolbar    // creating object of ToolBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        pager = binding.viewPager
        tab = binding.tabs
        bar = binding.toolbar

        // To make our toolbar show the application we need to give it to the ActionBar
        setSupportActionBar(bar)

        // Initializing the ViewPagerAdapter
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(MainNewsFragment(), "sports")
        adapter.addFragment(SportsNews(), "Crime")
        adapter.addFragment(TechnologyNews(), "Technology")

        // Adding the Adapter to the ViewPager
        pager.adapter = adapter

        // bind the viewPager with the TabLayout.
        tab.setupWithViewPager(pager)
    }
}