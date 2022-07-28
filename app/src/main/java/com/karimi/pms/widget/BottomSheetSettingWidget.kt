package com.karimi.pms.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.Toast
import com.karimi.pms.R
import com.karimi.pms.activity.HomeActivity

class BottomSheetSettingWidget(context: Context?, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {
//    private var listener: HomeActivity.Listener? = null
lateinit var homeActivity : HomeActivity


//    private var listener: HomeActivity.Listener? = null

    init {
//        homeActivity.init(this)
            inflate(context, R.layout.bottom_sheet_setting,this)
        }

//    fun test(l: HomeActivity.Listener){
//        listener = l
//
//    }
//    override fun showDialog() {
//        Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()    }


}