package com.keyosk.kiosk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CafeMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mmth_order_2)
    }
    val button1 = findViewById<Button>(R.id.fragment_button1)
    button1.setOnClickListener {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameId, FirstFragment())
            .commit()
    }

    val button2 = findViewById<Button>(R.id.fragment_button2)
    button2.setOnClickListener {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameId, SecondFragment())
            .commit()
    }
}
