package com.brewingkotlin.classvilla.Fragments.Home

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brewingkotlin.classvilla.R
import com.brewingkotlin.classvilla.UserDemo
import com.bumptech.glide.Glide

class UserAdapter(val gotList : List<UserDemo>) : RecyclerView.Adapter<UserAdapter.TTTT>() {

    var dataList = listOf<UserDemo>()

    init {
        dataList = gotList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TTTT {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_home,parent,false)
        return TTTT(view)
    }

    override fun onBindViewHolder(holder: TTTT, position: Int) {
        holder.title.text = dataList[position].name
        Glide.with(holder.itemView.context).load(dataList[position].imgUrl).into(holder.imgv)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(list  :List <UserDemo>) {
        dataList = list
        notifyDataSetChanged()
    }

    inner class TTTT(itemView : View) :RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.item_home_name)
        val imgv =  itemView.findViewById<ImageView>(R.id.item_home_imgv)
    }

}