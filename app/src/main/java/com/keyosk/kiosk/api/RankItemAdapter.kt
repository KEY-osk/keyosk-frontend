package com.keyosk.kiosk.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keyosk.kiosk.R

class RankItemAdapter(private val rankItems: List<Rank>) : RecyclerView.Adapter<RankItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_rank, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = rankItems[position]
        holder.rankTitleTextView.text = item.title
        holder.rankCategoryTextView.text = item.category
        // 이미지 로딩 라이브러리(Glide)를 사용하여 이미지를 표시합니다.
        Glide.with(holder.itemView.context).load(item.imageUrl).into(holder.rankIconImageView)
    }

    override fun getItemCount(): Int {
        return rankItems.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rankIconImageView: ImageView = itemView.findViewById(R.id.rank1_img)
        val rankTitleTextView: TextView = itemView.findViewById(R.id.rank_title)
        val rankCategoryTextView: TextView = itemView.findViewById(R.id.rank_category)
    }
}
