package com.zerdasoftware.draganddropreorderinrecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private val newsList:ArrayList<News>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener:onItemClickListener
    interface onItemClickListener { fun onItemClick(position: Int) }

    fun setOnItemClickListener(listener:onItemClickListener) { mListener = listener }

    fun deleteItem(i:Int) {
        newsList.removeAt(i)
        notifyDataSetChanged()
    }

    fun addItem(i: Int,news:News) {
        newsList.add(i,news)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView,mListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = "${position+1}-) ${currentItem.heading}"
        holder.briefNews.text = currentItem.briefNews

        val isVisible :Boolean = currentItem.visibility
        holder.constraintLayout.visibility = if (isVisible) View.VISIBLE else View.GONE

        holder.tvHeading.setOnClickListener {
            currentItem.visibility = !isVisible
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int { return newsList.size }

    class MyViewHolder(itemView:View,listener:onItemClickListener):RecyclerView.ViewHolder(itemView) {
        val titleImage:ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading:TextView = itemView.findViewById(R.id.tvHeading)
        val briefNews:TextView = itemView.findViewById(R.id.briefNews)
        val constraintLayout:ConstraintLayout = itemView.findViewById(R.id.expandedLayout)

        init {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }
}