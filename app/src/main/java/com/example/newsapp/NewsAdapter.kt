package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapp.databinding.NewsitemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    lateinit var context: Context
    var Newslist = ArrayList<NewsModel>()
    class NewsHolder(itemView: NewsitemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        var binding = NewsitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsHolder(binding)
    }

    override fun getItemCount(): Int {
        return Newslist.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {

        holder.binding.apply {
            Newslist.get(position).apply {

                txttitle.text = title
                txtdescriptin.text = description

            }
        }
    }
}