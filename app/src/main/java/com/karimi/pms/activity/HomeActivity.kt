package com.karimi.pms.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.karimi.pms.R
import com.karimi.pms.adapter.RequestAdapter
import com.karimi.pms.modal.Request
import com.karimi.pms.widget.BottomSheetSettingWidget
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_sheet_setting.*
import kotlinx.android.synthetic.main.box_chat.*
import kotlinx.android.synthetic.main.box_reason_c.*
import kotlinx.android.synthetic.main.dialog_filter_detail.*
import kotlinx.android.synthetic.main.dialog_reason_cansel.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.jar.Manifest

class HomeActivity : AppCompatActivity(), RequestAdapter.Listener{
    var listRequest: ArrayList<Request> = ArrayList()
    private lateinit var adapter: RequestAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        initRecycler()
        initSitting()
        initFiltering()
    }


    private fun initRecycler() {
        initListAdapter()
        adapter = RequestAdapter(listRequest, applicationContext, this)
        recycler.adapter = adapter
    }


    private fun initListAdapter() {
        listRequest.add(
            Request(
                "رفع خرابی",
                "15 خرداد کوچه 39 15 خرداد کوچه 39 15 خرداد کوچه 9",
                "1401/03/28",
                "فوری"
            )
        )
        listRequest.add(Request("رفع خرابی", "15 خرداد کوچه 39", "1401/03/28", "فوری"))
        listRequest.add(Request("نصب", "15 خرداد کوچه 39", "1401/03/28", "امروز"))
        listRequest.add(Request("نصب", "15 خرداد کوچه 39", "1401/03/28", "امروز"))
        listRequest.add(Request("نصب", "15 خرداد کوچه 39", "1401/03/28", "امروز"))
        listRequest.add(Request("نصب", "15 خرداد کوچه 39", "1401/03/28", "امروز"))
        listRequest.add(Request("نصب", "15 خرداد کوچه 39", "1401/03/28", "امروز"))
        listRequest.add(Request("نصب", "15 خرداد کوچه 39", "1401/03/28", "امروز"))
        listRequest.add(Request("بازدید دوره", "15 خرداد کوچه 39", "1401/03/28", "فردا"))
        listRequest.add(Request("بازدید دوره", "15 خرداد کوچه 39", "1401/03/28", "فردا"))
        listRequest.add(Request("بازدید دوره", "15 خرداد کوچه 39", "1401/03/28", "فردا"))
        listRequest.add(Request("بازدید دوره", "15 خرداد کوچه 39", "1401/03/28", "فردا"))
    }



    @SuppressLint("ResourceAsColor")
    private fun initSitting() {
        setting_slider.setOnClickListener {
            val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
//            val view = layoutInflater.inflate(R.layout.bottom_sheet_setting, null)
            dialog.setContentView(R.layout.bottom_sheet_setting)

            dialog.window?.setBackgroundDrawable(null)
            dialog.switch_nightMode.setOnCheckedChangeListener { compoundButton, b ->
                if (dialog.switch_nightMode.isChecked){
                     toast("on")
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else {
                    toast("off")
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
//            saveNightModeToPreferences(newNightMode);
//            recreate();
            dialog.show()
        }
    }


    private fun initFiltering() {
        filtering.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.bottom_sheet_filter)
//            dialog.window?.setBackgroundDrawableResource(R.drawable.item_border_bottom_sheet)
            dialog.show()
        }
    }


    override fun showDialog() {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_filter_detail)
        dialog.setCancelable(false)
        dialog.window?.setLayout(
            ((6.3 * width) / 7).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.close_detail.setOnClickListener { dialog.dismiss() }

        var k = 0
        dialog.showMore.setOnClickListener {
            k++
            if (k % 2 != 0) {
                dialog.passage_short.visibility = View.GONE
                dialog.passage_long.visibility = View.VISIBLE
            } else {
                dialog.passage_short.visibility = View.VISIBLE
                dialog.passage_long.visibility = View.GONE
            }
        }


        dialog.tv_doing.setOnClickListener {
            dialog.linear_done_cancel.visibility = View.VISIBLE
            dialog.tv_doing.visibility = View.GONE
            dialog.close_detail.visibility = View.GONE
            dialog.posModel_t.visibility = View.VISIBLE
            dialog.posModel.visibility = View.GONE
            dialog.posIcon.visibility = View.GONE
        }

//        dialog.tv_doing.setOnClickListener {  }
        dialog.cancel.setOnClickListener { reasonOfCancel() }

        dialog.window?.setBackgroundDrawableResource(R.drawable.item_border_dialog)
        dialog.show()
    }


    private fun reasonOfCancel() {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        val d = Dialog(this)
        d.setContentView(R.layout.dialog_reason_cansel)
        d.setCancelable(false)
        d.window?.setLayout(
            ((6.3 * width) / 7).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        d.window?.setBackgroundDrawableResource(R.drawable.item_border_dialog)

        d.close_reason_cancel.setOnClickListener { d.dismiss() }
        d.send.setOnClickListener {
            toast("با موفقیت ارسال شد")
            d.dismiss()
        }
        d.show()
    }

    private fun toast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }


    fun layoutAnimationRecycler() {
        val context = recycler.context
        val layoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down)
        recycler.layoutAnimation = layoutAnimationController
        recycler.adapter!!.notifyDataSetChanged()
        recycler.scheduleLayoutAnimation()
    }

}



