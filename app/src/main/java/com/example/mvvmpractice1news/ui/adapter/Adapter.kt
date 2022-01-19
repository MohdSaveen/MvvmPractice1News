package com.example.mvvmpractice1news.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice1news.R
import com.example.mvvmpractice1news.model.local.NewEntity

class Adapter(var list: MutableList<NewEntity>,
val onClick: OnClick,
val context: Context): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data =list[position]
        holder.setData(data)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}