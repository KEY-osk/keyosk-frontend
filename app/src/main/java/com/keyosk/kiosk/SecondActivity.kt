package com.keyosk.kiosk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 페이지 이동
        fun moveToAnotherPage() {
            val intent = Intent(this, CafeMainActivity::class.java)
            startActivity(intent)
        }
    }
}