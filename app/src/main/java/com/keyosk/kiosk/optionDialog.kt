package com.keyosk.kiosk

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment

class optionDialog(
    private val id: String,
    private val text: String,
    private val price: Int,
    private val imageDrawable: Drawable
) : DialogFragment() {

    // 클래스의 멤버 변수로 선언하여 클래스 전체에서 참조 가능
    private lateinit var mmth_opIce: RadioButton
    private lateinit var mmth_opHot: RadioButton
    private lateinit var mmth_small: RadioButton
    private lateinit var mmth_middle: RadioButton
    private lateinit var mmth_large: RadioButton
    private lateinit var mmth_optionCart: Button

    private lateinit var mmth_tumbleron: RadioButton
    private lateinit var mmth_tumbleroff: RadioButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.mmth_option, container, false)
        val mmth_optionMenuName = view.findViewById<TextView>(R.id.mmth_optionMenuName)
        val mmth_optionMenuPrice = view.findViewById<TextView>(R.id.mmth_optionMenuPrice)
        val mmth_optHotOrIce = view.findViewById<ConstraintLayout>(R.id.mmth_optHotOrIce)
        val mmth_optSize = view.findViewById<ConstraintLayout>(R.id.mmth_optSize)
        val mmth_optTumbler = view.findViewById<ConstraintLayout>(R.id.mmth_optTumbler)
        val mmth_shotOptionMenu = view.findViewById<ConstraintLayout>(R.id.mmth_shotOptionMenu)
        val mmth_optionDel = view.findViewById<Button>(R.id.mmth_optionDel)
        val mmth_optionMenuImg = view.findViewById<ImageView>(R.id.mmth_optionMenuImg)

        mmth_optionCart = view.findViewById(R.id.mmth_optionCart)

        var mmth_plusOptShot = ""
        var mmth_plusOptShotPrice = 0

        mmth_opIce = view.findViewById(R.id.mmth_opIce)
        mmth_opHot = view.findViewById(R.id.mmth_opHot)
        mmth_small = view.findViewById(R.id.mmth_small)
        mmth_middle = view.findViewById(R.id.mmth_middle)
        mmth_large = view.findViewById(R.id.mmth_large)
        mmth_tumbleron = view.findViewById(R.id.mmth_tumbleron)
        mmth_tumbleroff = view.findViewById(R.id.mmth_tumbleroff)

        val params: WindowManager.LayoutParams =
            dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)

        // 받아온 상품명 넣기
        mmth_optionMenuName.text = text
        mmth_optionMenuPrice.text = price.toString()
        mmth_optionMenuImg.background = imageDrawable

        // id가 "hot"을 포함한 경우 선택 옵션은 "HOT'으로 미리 선택
        if (id.contains("hot")) {
            mmth_opHot.isEnabled = false
            mmth_opIce.isEnabled = false
            mmth_opHot.isChecked = true
            mmth_opHot.setTextColor(Color.RED)
        } else if (id.contains("ice")) {
            mmth_opHot.isEnabled = false
            mmth_opIce.isEnabled = false
            mmth_opIce.isChecked = true
            mmth_opIce.setTextColor(Color.RED)
        } else if (id.contains("dessert")) {
            // dessert 인 경우 옵션 메뉴 안보이게
            mmth_optHotOrIce.visibility = View.GONE
            mmth_optSize.visibility = View.GONE
            mmth_optTumbler.visibility = View.GONE
            mmth_shotOptionMenu.visibility = View.GONE
        }

        // hot/ice 버튼을 "농도" 다음으로 2번째로 선택한 경우에도 mmth_optionCart 버튼 활성화 되도록
        mmth_opHot.setOnClickListener {
            updateOptCartBtnState()
        }
        mmth_opIce.setOnClickListener {
            updateOptCartBtnState()
        }

        // 라디오버튼 하나만 선택가능하도록
        mmth_opHot.setOnClickListener {
            mmth_opHot.isChecked = true
            mmth_opIce.isChecked = false
            mmth_small.isChecked = false
            mmth_middle.isChecked = false
            mmth_large.isChecked = false
            mmth_tumbleron.isChecked = false
            mmth_tumbleroff.isChecked = false

            val mmth_optShotName = view.findViewById<TextView>(R.id.mmth_basic)
            val mmth_optShotPrice = view.findViewById<TextView>(R.id.mmth_basicWon)

            // 해당 text 정보 뽑아서 전달하기
            mmth_plusOptShot = mmth_optShotName.text.toString()
            mmth_plusOptShotPrice = mmth_optShotPrice.text.toString().toInt()
            updateOptCartBtnState()
        }
        mmth_opIce.setOnClickListener {
            mmth_opHot.isChecked = false
            mmth_opIce.isChecked = true
            mmth_small.isChecked = false
            mmth_middle.isChecked = false
            mmth_large.isChecked = false
            mmth_tumbleron.isChecked = false
            mmth_tumbleroff.isChecked = false

            val mmth_optShotName = view.findViewById<TextView>(R.id.mmth_light)
            val mmth_optShotPrice = view.findViewById<TextView>(R.id.mmth_lightWon)

            // 해당 text 정보 뽑아서 전달하기
            mmth_plusOptShot = mmth_optShotName.text.toString()
            mmth_plusOptShotPrice = mmth_optShotPrice.text.toString().toInt()
            updateOptCartBtnState()
        }
        mmth_small.setOnClickListener {
            mmth_opHot.isChecked = false
            mmth_opIce.isChecked = false
            mmth_small.isChecked = true
            mmth_middle.isChecked = false
            mmth_large.isChecked = false
            mmth_tumbleron.isChecked = false
            mmth_tumbleroff.isChecked = false

            val mmth_optShotName = view.findViewById<TextView>(R.id.mmth_addShot)
            val mmth_optShotPrice = view.findViewById<TextView>(R.id.mmth_addShotWon)

            // 해당 text 정보 뽑아서 전달하기
            mmth_plusOptShot = mmth_optShotName.text.toString()
            mmth_plusOptShotPrice = mmth_optShotPrice.text.toString().toInt()
            updateOptCartBtnState()
        }
        mmth_middle.setOnClickListener {
            mmth_opHot.isChecked = false
            mmth_opIce.isChecked = false
            mmth_small.isChecked = false
            mmth_middle.isChecked = true
            mmth_large.isChecked = false
            mmth_tumbleron.isChecked = false
            mmth_tumbleroff.isChecked = false

            val mmth_optShotName = view.findViewById<TextView>(R.id.mmth_addTwoShot)
            val mmth_optShotPrice = view.findViewById<TextView>(R.id.mmth_addTwoShotWon)

            // 해당 text 정보 뽑아서 전달하기
            mmth_plusOptShot = mmth_optShotName.text.toString()
            mmth_plusOptShotPrice = mmth_optShotPrice.text.toString().toInt()
            updateOptCartBtnState()
        }

        updateOptCartBtnState()

        mmth_optionDel.setOnClickListener {
            dismiss()
        }

        mmth_optionCart.setOnClickListener {
            val mmth_ItemName = mmth_optionMenuName.text.toString()
            val mmth_ItemPriceText = mmth_optionMenuPrice.text.toString()
            val mmth_ItemCnt = "1".toInt()
            val selectedHotIceOption: String

            // 리사이클러뷰로 옵션 선택한 문자열 넘기기
            selectedHotIceOption = if (mmth_opHot.isChecked) "뜨거운 (HOT)"
            else if (mmth_opIce.isChecked) "차가운 (ICE)" else ""

            // ItemData에 값 전달
            val mmth_itemData = ItemData(
                id,
                mmth_ItemName,
                mmth_ItemPriceText,
                mmth_ItemCnt,
                selectedHotIceOption,
                mmth_plusOptShot,
                mmth_plusOptShotPrice
            )
            val mainActivity = activity as MainActivity
            // 아이템 추가
            mainActivity.addNewItem(mmth_itemData)
            dismiss()
        }
        return view
    }


    private fun updateOptCartBtnState() {
        // dessert인 경우 주문담기 버튼 활성화
        if (id.contains("dessert")) {
            mmth_optionCart.isEnabled = true
            mmth_optionCart.setBackgroundColor(Color.parseColor("#323335"))
            mmth_optionCart.setTextColor(Color.parseColor("#FFFFFF"))
            return
        }
        // 라디오 버튼 선택 여부로 버튼 활성화
        val isAnyHotIceSelected = listOf(mmth_opHot, mmth_opHot).any { it.isChecked }
        val isAnySizeBtnSelected =
            listOf(mmth_small, mmth_middle, mmth_large).any { it.isChecked }
        val isAnyTumblerSelected = listOf(mmth_tumbleron, mmth_tumbleroff).any { it.isChecked }

        // 모두 만족하는지 확인
        val mmth_isBothSelected = (isAnySizeBtnSelected && isAnyHotIceSelected)
        val mmth_isAllSelected = (mmth_isBothSelected && isAnyTumblerSelected)

        mmth_optionCart.isEnabled = mmth_isAllSelected

        if (mmth_isAllSelected) {
            // 라디오 버튼 세 가지 다 선택된 경우 주문담기 버튼 활성화
            mmth_optionCart.setBackgroundColor(Color.parseColor("#323335"))
            mmth_optionCart.setTextColor(Color.parseColor("#FFFFFF"))

        } else {
            // 그게 아니라면 비활성화
            mmth_optionCart.setBackgroundResource(R.drawable.btn_style)
            mmth_optionCart.setTextColor(Color.parseColor("#323335"))
        }
    }
}