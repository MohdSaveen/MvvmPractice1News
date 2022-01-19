package com.example.mvvmpractice1news.ui.adapter

import android.view.View
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmpractice1news.model.local.NewEntity
import kotlinx.android.synthetic.main.item_layout.view.*

class ViewHolder(itemView: View,val onClick: OnClick): RecyclerView.ViewHolder(itemView) {

    fun setData(result: NewEntity) {

        itemView.apply {
            crCardView1.setOnClickListener {
                onClick.setOnClick(result)
            }
            sorce.setText(result.date)
            tvTitle.setText(result.Name)
            Glide.with(ivImageView).load(result.ImageUrl)
                .into(ivImageView)
        }

    }
}