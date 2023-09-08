package com.keyosk.kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.androidexlyj.lyj_kiosk.MmthHomeActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MmthMainActivity : AppCompatActivity() {
    lateinit var mmth_gohome: ImageButton
    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var mmth_delAllBtn: Button
    lateinit var mmth_totalPrice: TextView
    lateinit var mmth_pay1: Button
    lateinit var mmth_pay2: Button
    lateinit var mmth_pay3: Button
    lateinit var mmth_pay4: Button
    lateinit var mmth_recyclerView: RecyclerView
    lateinit var mmth_adapter: RecyclerViewAdapter
    lateinit var mmth_itemList: ArrayList<ItemData>
    private var mmth_totalCount = 0  // 총 개수 표시할 변수


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mmth_order_1)
        title = "MMTH COFFEE"

        mmth_gohome = findViewById<ImageButton>(R.id.mmth_gohome)
        mmth_recyclerView = findViewById<RecyclerView>(R.id.mmth_recyclerView)
        mmth_delAllBtn = findViewById<Button>(R.id.mmth_delAllBtn)

        mmth_totalPrice = findViewById(R.id.mmth_totalPrice)
        mmth_pay1 = findViewById<Button>(R.id.mmth_pay1)
        mmth_pay2 = findViewById<Button>(R.id.mmth_pay2)
        mmth_pay3 = findViewById<Button>(R.id.mmth_pay3)
        mmth_pay4 = findViewById<Button>(R.id.mmth_pay4)

        mmth_gohome.setOnClickListener {
            val intent = Intent(this, MmthHomeActivity::class.java)
            startActivity(intent)
        }
        mmth_delAllBtn.setOnClickListener {
            clearItemListAll()
            Toast.makeText(applicationContext, "주문이 전체 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
        mmth_pay1.setOnClickListener {
            checkListAndHandleButton()
        }
        mmth_pay2.setOnClickListener {
            checkListAndHandleButton()
        }
        mmth_pay3.setOnClickListener {
            checkListAndHandleButton()
        }
        mmth_pay4.setOnClickListener {
            Toast.makeText(applicationContext, "추후 업데이트 예정!", Toast.LENGTH_SHORT).show()
        }

        // 초기화
        tabLayout = findViewById(R.id.mmth_tabLayout)
        viewPager2 = findViewById(R.id.mmth_viewPager2)
        val viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2.adapter = viewPager2Adapter
        // 탭 메뉴 추가
        val tabs = listOf(
            "신메뉴",
            "커피",
            "디카페인",
            "콜드브루",
            "논커피",
            "티&에이드",
            "프라페&블렌디드",
            "디저트"
        )

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            // 리스트 목록을 가져와 탭에 보여주기
            tab.text = tabs[position]
        }.attach()
        // attach로 TabLayout, ViewPager 연결

        // 초기화
        mmth_itemList = ArrayList()
        // 리사이클러뷰 데이터 표시
        mmth_adapter = RecyclerViewAdapter(
            mmth_itemList,
            ::updateTotalPrice
        )  // ::updateTotalPrice 함수 참조 - 총 가격 업데이트

        // 어댑터 설정
        mmth_recyclerView.adapter = mmth_adapter
        // 아이템 수직 배치
        mmth_recyclerView.layoutManager = LinearLayoutManager(this)
        updateTotalPrice()
    }

    private fun checkListAndHandleButton() {
        if (mmth_itemList.isEmpty()) {
            Toast.makeText(applicationContext, "메뉴를 선택해 주세요.", Toast.LENGTH_SHORT).show()
        } else {
            // 리스트가 존재한다면 아이템 정보 넘기기
            val dialog = listDialog(
                mmth_itemList,
                mmth_totalPrice.text.toString(),
                mmth_totalCount.toString()
            )
            dialog.show(this.supportFragmentManager, "CustomDialog")
        }
        // lyj_itemList 확인
        /*for (itemData in lyj_itemList) {
            // itemData에 대한 처리
            // 예: Log 출력
            Log.d("listcheck", "Item: ${itemData.name}, Price: ${itemData.price}")
        }*/
    }


    // 총 가격 업데이트
    fun updateTotalPrice() {
        val totalPrice = mmth_adapter.getTotalPrice()
        // Count 저장
        val totalCount = mmth_adapter.getTotalCount()
        mmth_totalPrice.text = totalPrice.toString()
        mmth_totalCount = totalCount
    }

    // 리스트 전체 삭제
    fun clearItemListAll() {
        mmth_itemList.clear()
        mmth_adapter.notifyDataSetChanged()
        updateTotalPrice()


    }

    // 아이템 추가
    fun addNewItem(itemData: ItemData) {
        // ItemData 객체를 전달받아서 리스트에 추가하기
        mmth_itemList.add(itemData)
        mmth_adapter.notifyItemInserted(mmth_itemList.size - 1)
        mmth_adapter.notifyDataSetChanged()
        updateTotalPrice()
    }

    class ViewPager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 8

        // Fragment 연결
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> NewMenu_Fragment()
                1 -> Cof_Fragment()
                2 -> DeCof_Fragment()
                3 -> ColdBrew_Fragment()
                4 -> NonCoffee_Fragment()
                5 -> Tea_Ade_Fragment()
                6 -> Blended_Fragment()
                else -> Dessert_Fragment()
            }
        }
    }

    // 백버튼 막기
    override fun onBackPressed() {
        //        super.onBackPressed()
    }
}