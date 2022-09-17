package com.example.newsupdate.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsupdate.R
import com.example.newsupdate.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var newsSettingBinding: ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsSettingBinding = ActivityProfileBinding.inflate(layoutInflater)
        val view = newsSettingBinding.root

        newsSettingBinding.idBookmarkedNews.setOnClickListener {
            startActivity(Intent(this, BookmarksPage::class.java))
        }

        setContentView(view)
    }
}