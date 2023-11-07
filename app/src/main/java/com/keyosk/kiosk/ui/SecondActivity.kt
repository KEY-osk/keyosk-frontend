package com.keyosk.kiosk.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyosk.kiosk.R
import com.keyosk.kiosk.api.ApiClient
import com.keyosk.kiosk.api.Rank
import com.keyosk.kiosk.api.RankItemAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rank_RecyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val rankService = ApiClient.rankService
        val call: Call<List<Rank>> = rankService.getRankData()

        call.enqueue(object : Callback<List<Rank>> {
            override fun onResponse(call: Call<List<Rank>>, response: Response<List<Rank>>) {
                if (response.isSuccessful) {
                    val rankItems: List<Rank> = response.body() ?: emptyList()
                    val adapter = RankItemAdapter(rankItems)
                    recyclerView.adapter = adapter
                } else {
                    // API 호출이 실패한 경우에 대한 처리를 여기에 작성
                }
            }

            override fun onFailure(call: Call<List<Rank>>, t: Throwable) {
                // 네트워크 오류 또는 다른 예외가 발생한 경우에 대한 처리를 여기에 작성합니다.
            }
        })

        fun onNextButtonClick(view: View) {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

    }
}

