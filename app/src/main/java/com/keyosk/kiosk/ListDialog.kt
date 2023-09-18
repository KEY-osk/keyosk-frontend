package com.keyosk.kiosk

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 전체 총 주문 리스트 보여주는 다이얼로그
class listDialog(
    private val itemList: ArrayList<ItemData>,
    private val totalPrice: String,
    private val totalCount: String
) : DialogFragment() {
    private lateinit var mmth_listCancel: Button
    private lateinit var mmth_listNext: Button
    private lateinit var mmth_listTotalPrice: TextView
    private lateinit var mmth_listTotalCount: TextView
    private lateinit var mmth_recyclerViewItemResult: RecyclerView
    private lateinit var mmth_adapter: TotalListRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_list, container, false)
        mmth_listCancel = view.findViewById(R.id.mmth_listCancel)
        mmth_listNext = view.findViewById(R.id.mmth_listNext)
        mmth_listTotalPrice = view.findViewById(R.id.mmth_listTotalPrice)
        mmth_listTotalCount = view.findViewById(R.id.mmth_listTotalCount)

        // 다른 형태의 리사이클러뷰
        mmth_recyclerViewItemResult = view.findViewById(R.id.mmth_recyclerViewItemResult)
        mmth_adapter = TotalListRecyclerViewAdapter(itemList)

        // 리사이클러뷰 아이템 새로 배치
        val layoutManager = LinearLayoutManager(context)
        mmth_recyclerViewItemResult.layoutManager = layoutManager
        mmth_recyclerViewItemResult.adapter = mmth_adapter

        // 다이얼로그 속성 설정
        val params: WindowManager.LayoutParams =
            dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)

        mmth_listTotalPrice.text = totalPrice
        mmth_listTotalCount.text = totalCount

        mmth_listCancel.setOnClickListener {
            if (itemList.isNotEmpty()) {
                (activity as? MmthMainActivity)?.mmth_pay1?.isEnabled = true
                (activity as? MmthMainActivity)?.mmth_pay2?.isEnabled = true
                (activity as? MmthMainActivity)?.mmth_pay3?.isEnabled = true
                (activity as? MmthMainActivity)?.mmth_pay4?.isEnabled = true
            }
            dismiss()
        }
        mmth_listNext.setOnClickListener {
            val dialog = thxDialog(totalPrice)
            dialog.show(parentFragmentManager, "CustomDialog")
            dismiss()
        }
        return view
    }
}