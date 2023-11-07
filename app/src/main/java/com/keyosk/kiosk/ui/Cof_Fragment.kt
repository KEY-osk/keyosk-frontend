package com.keyosk.kiosk.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.keyosk.kiosk.R
import com.keyosk.kiosk.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Cof_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cof, container, false)
        val linearLayouts = listOf<LinearLayout>(
            view.findViewById(R.id.mmth_ameri),
            view.findViewById(R.id.mmth_latte),
        )

        for (linearLayout in linearLayouts) {
            linearLayout.setOnClickListener {
                handleLinearClick(linearLayout)
            }
        }
        return view
    }

    private fun handleLinearClick(linearLayout: LinearLayout) {
        val linearLayoutId = linearLayout.id
        // 해당 리니어의 id_~ 값들 가져오기
        val textId = linearLayout.resources.getIdentifier(
            "${resources.getResourceEntryName(linearLayoutId)}_text",
            "id",
            requireActivity().packageName
        )
        val priceId = linearLayout.resources.getIdentifier(
            "${resources.getResourceEntryName(linearLayoutId)}_price",
            "id",
            requireActivity().packageName
        )
        val imageId = linearLayout.resources.getIdentifier(
            "${resources.getResourceEntryName(linearLayoutId)}_img",
            "id",
            requireActivity().packageName
        )

        var text: String? = null
        var price: Int? = null
        var imageDrawable: Drawable? = null

        // 해당 리니어의 정보 가져오기
        linearLayout.findViewById<TextView>(textId)?.let {
            text = it.text.toString()
        }
        linearLayout.findViewById<TextView>(priceId)?.let {
            price = it.text.toString().toInt()
        }
        linearLayout.findViewById<ImageView>(imageId)?.let { imageView ->
            imageDrawable = imageView.background
        }

        if (text != null && price != null && imageDrawable != null) {
            // 다이얼로그에 정보 전달
            val dialog = optionDialog(
                resources.getResourceEntryName(linearLayoutId), text!!, price!!, imageDrawable!!
            )
            dialog.show(activity?.supportFragmentManager!!, "CustomDialog")
        }

        val touchService = ApiClient.touchService

        fun sendTouchCoordinatesToServer(x: Float, y: Float) {
            val touchApiService = ApiClient.touchService

            val call: Call<Void> = touchApiService.sendTouchCoordinates(x, y)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // 터치 좌표 전송 성공
                    } else {
                        // 터치 좌표 전송 실패
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // 네트워크 오류 또는 예외 발생 시 처리
                }
            })
        }
    }
}