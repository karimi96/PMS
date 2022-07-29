package com.karimi.pms.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.karimi.pms.R
import com.karimi.pms.adapter.FilterAdapter
import com.karimi.pms.adapter.RequestAdapter
import com.karimi.pms.modal.Request
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_sheet_filter.*
import kotlinx.android.synthetic.main.bottom_sheet_setting.*
import kotlinx.android.synthetic.main.box_chat.*
import kotlinx.android.synthetic.main.box_reason_c.*
import kotlinx.android.synthetic.main.box_record_activity.*
import kotlinx.android.synthetic.main.box_spinner.*
import kotlinx.android.synthetic.main.dialog_filter_detail.*
import kotlinx.android.synthetic.main.dialog_reason_cansel.*
import kotlinx.android.synthetic.main.dialog_save_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.overlay.Marker

class HomeActivity : AppCompatActivity(), RequestAdapter.Listener, FilterAdapter.Listener {
    var listRequest: ArrayList<Request> = ArrayList()
    private lateinit var adapter: RequestAdapter
    private var bottomSheetFiltering: BottomSheetDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance()
            .load(this, android.preference.PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_home)

        checkTheme()
        initRecyclerHome()
        initBottomSheetSitting()
        initBottomSheetFiltering()

    }


    private fun initRecyclerHome() {
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
    private fun initBottomSheetSitting() {
        setting_slider.setOnClickListener {
            val bsh = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
//            val view = layoutI
//            nflater.inflate(R.layout.bottom_sheet_setting, null)
            bsh.setContentView(R.layout.bottom_sheet_setting)

            bsh.switch_nightMode.setOnCheckedChangeListener { compoundButton, b -> initSwitch(bsh) }
            if (MyPreferences(this).darkMode == 1) bsh.switch_nightMode.isChecked = true

            bsh.exitAccount.setOnClickListener { finishAffinity() }
            bsh.show()
        }
    }


    private fun initSwitch(bsh: BottomSheetDialog) {
        if (bsh.switch_nightMode.isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            MyPreferences(this).darkMode = 1
            delegate.applyDayNight()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            MyPreferences(this).darkMode = 2
            delegate.applyDayNight()
        }
    }


    private fun checkTheme() {
        when (MyPreferences(this).darkMode) {
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
        }
    }


    class MyPreferences(context: Context?) {
        companion object {
            private const val DARK_STATUS = "io.github.manuelernesto.DARK_STATUS"
        }

        private val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        var darkMode = preferences.getInt(DARK_STATUS, 0)
            set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()

    }


//    private fun initRecyclerFilter(): FilterAdapter {
//        var list = ArrayList<String>()
//        list.add("نصب")
//        list.add("جمع اوری")
//        list.add("بازدید دوره")
//        list.add("رفع خرابی")
//        return FilterAdapter(list, applicationContext , this)
//    }


    private fun initBottomSheetFiltering() {
        filtering.setOnClickListener {
            bottomSheetFiltering = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            bottomSheetFiltering!!.setContentView(R.layout.bottom_sheet_filter)
            bottomSheetFiltering!!.show()
            val list = resources.getStringArray(R.array.listOfFilter)
            bottomSheetFiltering!!.recycler_filter.adapter =
                FilterAdapter(list.toList(), applicationContext, this)
        }
    }


    override fun showDialog() {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_filter_detail)
        dialog.setCancelable(false)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setBackgroundDrawableResource(R.drawable.item_border_dialog)
        dialog.show()

        dialog.window?.setLayout(((6.3 * width) / 7).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)


        lunchMap(dialog)
        dialog.close_detail.setOnClickListener { dialog.dismiss() }

        var k = 0
        dialog.tv_showMore.setOnClickListener {
            k++
            if (k % 2 != 0) {
                arrayOf(dialog.passage_short, dialog.img_showMore_down).forEach {
                    it.visibility = View.GONE
                }

                arrayOf(dialog.passage_long, dialog.img_showMore_up).forEach {
                    it.visibility = View.VISIBLE
                }

                dialog.scroll.post { dialog.scroll.fullScroll(View.FOCUS_DOWN) }

            } else {
                arrayOf(dialog.passage_long, dialog.img_showMore_up).forEach {
                    it.visibility = View.GONE
                }

                arrayOf(dialog.passage_short, dialog.img_showMore_down).forEach {
                    it.visibility = View.VISIBLE
                }
            }
        }



        dialog.tv_doing.setOnClickListener {
            dialog.linear_done_cancel.visibility = View.VISIBLE
            dialog.tv_doing.visibility = View.GONE
            dialog.close_detail.visibility = View.GONE
            dialog.posModel_t.visibility = View.VISIBLE
            dialog.posModel.visibility = View.GONE
            dialog.posIcon.visibility = View.GONE
            dialog.setCancelable(true)
        }

        dialog.tv_cancel.setOnClickListener {
            dialog.dismiss()
            initDialogReasonOfCancel()
        }

        dialog.tv_done.setOnClickListener {
            dialog.dismiss()
            initDialogSaveActivity()
        }
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


    private fun lunchMap(d: Dialog) {
        d.map.setTileSource(TileSourceFactory.MAPNIK)
        d.map.isTilesScaledToDpi = true
        d.map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
        d.map.setBuiltInZoomControls(false)
        d.map.setMultiTouchControls(true)
        val mapController = d.map.controller
        mapController.setZoom(16.0)
        val startPoint = GeoPoint(34.629634, 50.913787) //55.751442, 37.615569
        mapController.setCenter(startPoint)

        var marker = Marker(d.map)
        marker.position = GeoPoint(34.629634, 50.913787)
        marker.icon = getDrawable(R.drawable.ic_location)
        d.map.overlays.add(marker)
        d.map.invalidate()
    }


    private fun initDialogReasonOfCancel() {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        val d = Dialog(this)
        d.setContentView(R.layout.dialog_reason_cansel)
        d.setCancelable(false)
        d.window?.attributes?.windowAnimations = R.style.DialogAnimation
        d.window?.setLayout(((6.3 * width) / 7).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        d.window?.setBackgroundDrawableResource(R.drawable.item_border_dialog)
        d.show()

        d.close_reason_cancel.setOnClickListener { d.dismiss() }
        d.send.setOnClickListener {
            toast("با موفقیت ارسال شد")
            d.dismiss()
        }

        initBoxSpinner(d)
    }


    private fun initBoxSpinner(d: Dialog) {
        val reason = resources.getStringArray(R.array.ReasonOfCancel)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, reason)
        d.auto_complete_txt.setAdapter(adapter)
        d.auto_complete_txt.threshold = 0
        d.auto_complete_txt.text
    }


    private fun initDialogSaveActivity() {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        val d = Dialog(this)
        d.setContentView(R.layout.dialog_save_activity)
        d.setCancelable(false)
        d.window?.attributes?.windowAnimations = R.style.DialogAnimation
        d.window?.setLayout(((6.3 * width) / 7).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        d.window?.setBackgroundDrawableResource(R.drawable.item_border_dialog)
        d.show()

        d.close_save_activity.setOnClickListener { d.dismiss() }
        d.send_saveActivity.setOnClickListener {
            toast("با موفقیت ارسال شد")
            d.dismiss()
        }

    }


    override fun dismissDialog(s: String) {
        toast(s)
//        bottomSheetFiltering?.dismiss()
    }
}



