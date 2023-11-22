package com.keyosk.kiosk.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe)

        val btnStart : Button = findViewById(R.id.mmth_click)
        btnStart.setOnClickListener {
            val intent = Intent(this, MmthHomeActivity::class.java)
            startActivity(intent)
        }

        val btnfast: Button = findViewById(R.id.fastfood_category)
        btnfast.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        val btnetc: Button = findViewById(R.id.etc_category)
        btnetc.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }

    }
}

