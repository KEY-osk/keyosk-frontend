package com.keyosk.kiosk

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class payDialog(private val getTotalPrice: String) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_card, container, false)
        val mmth_cancelBtn = view.findViewById<Button>(R.id.mmth_cancelBtn)
        val mmth_approveBtn = view.findViewById<Button>(R.id.mmth_approveBtn)
        val mmth_totalPriceDialog = view.findViewById<TextView>(R.id.mmth_totalPriceDialog)

        val params: WindowManager.LayoutParams =
            dialog?.window?.attributes as WindowManager.LayoutParams
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)

        mmth_totalPriceDialog.text = getTotalPrice

        mmth_cancelBtn.setOnClickListener {
            dismiss()
        }

        mmth_approveBtn.setOnClickListener {
            // 결제 버튼 눌렀을때 "취소" 버튼 눌림 방지
            mmth_cancelBtn.isEnabled = false
            val mmthmainActivity = activity as MmthMainActivity
            mmthmainActivity.let {
                Toast.makeText(context, "결제중입니다. 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show()

                // 5초 후에 결제 완료 메시지 표시 후 화면 전환
                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(context, "결제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    it.clearItemListAll()
                    val dialog = thxDialog()
                    dialog.show(parentFragmentManager, "CustomDialog")
                    dismiss()
                }, 3000)
            }
        }
        return view
    }
}