package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.NewsitemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    lateinit var context: Context
    var newslist = ArrayList<NewsModel>()

    class NewsHolder(itemView: NewsitemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        context = parent.context
        var binding = NewsitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(binding)
    }

    override fun getItemCount(): Int {
        return newslist.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {

        holder.binding.apply {
            newslist.get(position).apply {
                Glide.with(context).load(image).into(imgposter)
                txttitle.text = title
                txtdescriptin.text = description
                txtcategory.text = category
                txtdate.text = date
            }

            val isVisible : Boolean = newslist.get(position).visibility
            holder.binding.newsitem.visibility = if (isVisible){
              holder.binding.click.setText("Tap To Close ▲")
                View.VISIBLE
            } else View.GONE

            holder.binding.content.setOnClickListener {
                newslist.get(position).visibility = !newslist.get(position).visibility
                notifyItemChanged(position)
            }
        }
    }

    fun update(newslist: ArrayList<NewsModel>) {
        this.newslist = newslist
        notifyDataSetChanged()
    }
}