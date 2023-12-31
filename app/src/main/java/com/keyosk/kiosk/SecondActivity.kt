package com.keyosk.kiosk

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe)

        val btnfastfood: Button = findViewById(R.id.fastfood_category)
        btnfastfood.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        val btnetc: Button = findViewById(R.id.etc_category)
        btnetc.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }

        val btnnext: Button = findViewById(R.id.btn_next)
        btnnext.setOnClickListener {
            val intent = Intent(this, MmthHomeActivity::class.java)
            startActivity(intent)
        }
    }
}