package com.keyosk.kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 페이지 이동
        fun moveToAnotherPage(){
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}