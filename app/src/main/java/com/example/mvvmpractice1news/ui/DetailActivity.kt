package com.example.mvvmpractice1news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.mvvmpractice1news.R
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.android.synthetic.main.item_layout.ivImageView
import kotlinx.android.synthetic.main.item_layout.tvTitle
import kotlinx.android.synthetic.main.news_detail.*

class DetailActivity : AppCompatActivity() {
    var title:String=""
    var desc:String=""
    var date:String=""
    var sorce:String=""
    var url:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_detail)
        val intent=intent
        title= intent.getStringExtra("name").toString()
        desc= intent.getStringExtra("desc").toString()
        date= intent.getStringExtra("date").toString()
        sorce= intent.getStringExtra("sorce").toString()
        url=intent.getStringExtra("url").toString()
        tvTitle.setText(title)
        tvSorce.setText(sorce)
        tvDate.setText(date)
        tvdesc.setText(desc)

        Glide.with(ivImageView).load(url)
            .into(ivImageView)

    }
}