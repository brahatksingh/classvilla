package com.brewingkotlin.classvilla.Fragments.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brewingkotlin.classvilla.R
import com.brewingkotlin.classvilla.Room
import com.brewingkotlin.classvilla.User
import com.brewingkotlin.classvilla.UserDemo
import com.bumptech.glide.Glide

class RoomAdapter(val gotlist : List<Room>) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    var dataList = listOf<Room>()

    init {
        dataList = gotlist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        return RoomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.class_item,parent,false))
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.title.text = dataList[position].roomName
        holder.teacher.text = dataList[position].name
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(list  :List <Room>) {
        dataList = list
        notifyDataSetChanged()
    }

    inner class RoomViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        val title = itemview.findViewById<TextView>(R.id.subject)
        val teacher = itemview.findViewById<TextView>(R.id.teacher)
    }

}