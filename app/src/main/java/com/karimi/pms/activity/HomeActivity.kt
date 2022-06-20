package com.karimi.pms.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karimi.pms.R
import com.karimi.pms.adapter.RequestAdapter
import com.karimi.pms.modal.Request
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {
    var listRequest : ArrayList<Request> = ArrayList()
    private lateinit var adapter :RequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initRecycler()

    }

    private fun initRecycler(){
        initListAdapter()
        adapter = RequestAdapter(listRequest,applicationContext)
        recycler.adapter = adapter
    }

    private fun initListAdapter(){
        listRequest.add(Request("رفع خرابی","15 خرداد کوچه 39 15 خرداد کوچه 39 15 خرداد کوچه 9","1401/03/28","فوری"))
        listRequest.add(Request("رفع خرابی","15 خرداد کوچه 39","1401/03/28","فوری"))
        listRequest.add(Request("نصب","15 خرداد کوچه 39","1401/03/28","امروز"))
        listRequest.add(Request("نصب","15 خرداد کوچه 39","1401/03/28","امروز"))
        listRequest.add(Request("نصب","15 خرداد کوچه 39","1401/03/28","امروز"))
        listRequest.add(Request("نصب","15 خرداد کوچه 39","1401/03/28","امروز"))
        listRequest.add(Request("نصب","15 خرداد کوچه 39","1401/03/28","امروز"))
        listRequest.add(Request("نصب","15 خرداد کوچه 39","1401/03/28","امروز"))
        listRequest.add(Request("بازدید دوره","15 خرداد کوچه 39","1401/03/28","فردا"))
        listRequest.add(Request("بازدید دوره","15 خرداد کوچه 39","1401/03/28","فردا"))
        listRequest.add(Request("بازدید دوره","15 خرداد کوچه 39","1401/03/28","فردا"))
        listRequest.add(Request("بازدید دوره","15 خرداد کوچه 39","1401/03/28","فردا"))
    }

    fun initSitting(){
        setting_slider.setOnClickListener {  }
    }

}
