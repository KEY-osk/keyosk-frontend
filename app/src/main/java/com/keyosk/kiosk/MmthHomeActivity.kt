package com.androidexlyj.lyj_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.keyosk.kiosk.MmthMainActivity
import com.keyosk.kiosk.R


class MmthHomeActivity : AppCompatActivity() {

    lateinit var mmthforhere: Button
    lateinit var mmthtogo: Button
    var backKeyPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mmth_order_main)

        mmthforhere = findViewById<Button>(R.id.mmth_forhere)
        mmthtogo = findViewById<Button>(R.id.mmth_togo)

        // 메뉴 화면으로 이동
        mmthforhere.setOnClickListener {
            val intent = Intent(this, MmthMainActivity::class.java)
            startActivity(intent)
        }
        mmthtogo.setOnClickListener {
            val intent = Intent(this, MmthMainActivity::class.java)
            startActivity(intent)
        }
    }

    // 백버튼 막기
    override fun onBackPressed() {
        // super.onBackPressed()
        // 백 버튼 눌렸을 때 첫 번째 누른 시간 기록하고 2.5초 이내에 다시 안눌린다면 시간 갱신
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis()
            return
        }
        // 백 버튼 누른 이후 2.5초 이내에 다시 누르면 앱 종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
            // 종료
            finishAffinity()
        }
    }
}