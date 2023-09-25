package com.keyosk.kiosk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 전체 총 주문 목록 보여주는 코드
class TotalListRecyclerViewAdapter (private val itemList: ArrayList<ItemData>) :
    RecyclerView.Adapter<TotalListRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mmth_recyMenuNameList: TextView = itemView.findViewById(R.id.mmth_recyMenuNameList)
        val mmth_recyMenuPriceList: TextView = itemView.findViewById(R.id.mmth_recyMenuPriceList)
        val mmth_optionPlusShotList: TextView = itemView.findViewById(R.id.mmth_optionPlusShotList)
        val mmth_optionPlusShotPriceList: TextView = itemView.findViewById(R.id.mmth_optionPlusShotPriceList)
        val mmth_cntList: TextView = itemView.findViewById(R.id.mmth_cntList)
        val mmth_optionHotIceMenuText: TextView = itemView.findViewById(R.id.mmth_optionHotIceMenuText)
        val mmth_totalRecyShotLinear: LinearLayout = itemView.findViewById(R.id.mmth_totalRecyShotLinear)
        val mmth_totalRecyHotIceLinear: LinearLayout = itemView.findViewById(R.id.mmth_totalRecyHotIceLinear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 아이템 데이터 가져오기
        val item = itemList[position]

        // 뷰홀더에 데이터 설정
        holder.mmth_recyMenuNameList.text = item.name
        holder.mmth_recyMenuPriceList.text = (item.price.toInt() * item.count).toString()
        holder.mmth_cntList.text = item.count.toString()
        holder.mmth_optionPlusShotList.text = item.optSize
        holder.mmth_optionPlusShotPriceList.text = (item.optShotPrice * item.count).toString()
        holder.mmth_optionHotIceMenuText.text = item.selectedHotIceOption

        // 아이디가 디저트인경우 해당 옵션 리니어 보이지 않도록
        if (item.id.contains("dessert")) {
            holder.mmth_totalRecyShotLinear.visibility = View.GONE
            holder.mmth_totalRecyHotIceLinear.visibility = View.GONE

        } else {
            holder.mmth_totalRecyShotLinear.visibility = View.VISIBLE
            holder.mmth_totalRecyHotIceLinear.visibility = View.VISIBLE
        }
    }
}