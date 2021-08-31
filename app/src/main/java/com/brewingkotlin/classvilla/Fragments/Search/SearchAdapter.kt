package com.brewingkotlin.classvilla.Fragments.Search

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brewingkotlin.classvilla.R
import com.brewingkotlin.classvilla.User
import com.bumptech.glide.Glide
import java.io.PipedOutputStream

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var dataList = listOf<User?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_search,parent))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.name.text = dataList[position]!!.name
        holder.email.text = dataList[position]!!.email
        val url = dataList[position]?.imageUrl.let {
            Glide.with(holder.itemView.context).load(it).into(holder.imageView)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(list : List<User?>) {
        dataList = list
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.item_s_name)
        val email = itemView.findViewById<TextView>(R.id.item_s_email)
        val imageView = itemView.findViewById<ImageView>(R.id.item_s_imageView)

    }
}