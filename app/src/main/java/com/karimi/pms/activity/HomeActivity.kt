package com.karimi.pms.activity

import android.app.Dialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.karimi.pms.R
import com.karimi.pms.adapter.FilterAdapter
import com.karimi.pms.adapter.RequestAdapter
import com.karimi.pms.helper.Session
import com.karimi.pms.helper.Tools
import com.karimi.pms.modal.request.Request
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_sheet_filter.*
import kotlinx.android.synthetic.main.bottom_sheet_setting.*
import kotlinx.android.synthetic.main.box_chat.*
import kotlinx.android.synthetic.main.box_reason_c.*
import kotlinx.android.synthetic.main.box_record_activity.*
import kotlinx.android.synthetic.main.box_spinner.*
import kotlinx.android.synthetic.main.dialog_customer_detail.*
import kotlinx.android.synthetic.main.dialog_reason_cansel.*
import kotlinx.android.synthetic.main.dialog_save_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.overlay.Marker


class HomeActivity : AppCompatActivity(), FilterAdapter.Listener, RequestAdapter.Listener {
    var listRequest: ArrayList<Request> = ArrayList()
    private lateinit var adapter: RequestAdapter
    private var bottomSheetFiltering: BottomSheetDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, android.preference.PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_home)

        Tools.checkTheme()
        initRecyclerHome()
//        testSever()
        initBottomSheetSitting()
        initBottomSheetFiltering()

    }


    private fun initRecyclerHome() {
        initListAdapter()
        adapter = RequestAdapter(listRequest, applicationContext, this)
        recycler.adapter = adapter
        Tools.layoutAnimationRecycler(recycler)
    }


    /* private fun testSever(){
         var apiService = ApiClient.getClient()?.create(ApiService::class.java)
         val getApiPost = apiService?.getPost()
         getApiPost?.enqueue(object : Callback<Data> {
             override fun onResponse(call: Call<Data>, response: Response<Data>) {
 //                Log.e("qqq", "onResponse: "+response.body().ok);
                 adapter = RequestAdapter(response.body()?.result!!, applicationContext, this@HomeActivity)
                 recycler.adapter = adapter
 //                recyclerView.setAdapter(AdapterSearch(getContext(), response.body().result))
             }

             override fun onFailure(call: Call<Data>, t: Throwable) {
                 Log.e("qqqq", "onFailure: ", t)
             }
         })
     }*/


    private fun initListAdapter() {
        listRequest.add(Request("رفع خرابی", "15 خرداد کوچه 39 15 خرداد کوچه 39 15 خرداد کوچه 9", "1401/03/28", "فوری" ))
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


    private fun initBottomSheetSitting() {
        setting_slider.setOnClickListener {
            val bsh = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            bsh.setContentView(R.layout.bottom_sheet_setting)

            bsh.switch_nightMode.setOnCheckedChangeListener { compoundButton, b -> initSwitch(bsh) }
            if (Session.getInstance().getInt("darkMode") == 1) bsh.switch_nightMode.isChecked = true

            bsh.exitAccount.setOnClickListener { finishAffinity() }
            bsh.show()
        }
    }


    private fun initSwitch(bsh: BottomSheetDialog) {
        if (bsh.switch_nightMode.isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Session.getInstance().putExtra("darkMode", 1)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Session.getInstance().putExtra("darkMode", 2)
        }
    }


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


    override fun showDialogCustomerDetail() {
            val metrics = resources.displayMetrics
            val width = metrics.widthPixels
            val height = metrics.heightPixels

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_customer_detail)
            dialog.setCancelable(false)
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            dialog.window?.setBackgroundDrawableResource(R.drawable.item_border_dialog)
            dialog.show()

            dialog.window?.setLayout(
                ((6.3 * width) / 7).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )


            lunchMap(dialog)
            dialog.close_detail.setOnClickListener {
                dialog.dismiss()
            }

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
                arrayOf(dialog.linear_done_cancel, dialog.posModel_t).forEach {
                    it.visibility = View.VISIBLE
                }
                arrayOf(
                    dialog.tv_doing,
                    dialog.close_detail,
                    dialog.posModel,
                    dialog.posIcon
                ).forEach { it.visibility = View.GONE }
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
            Tools.toast(baseContext,"با موفقیت ارسال شد")
            d.dismiss()
        }
        initBoxSpinner(d)
    }


    private fun initBoxSpinner(d: Dialog) {
        val reason = resources.getStringArray(R.array.ReasonOfCancel)
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_spinner, reason)
        d.auto_complete_txt.setAdapter(adapter)
        d.auto_complete_txt.threshold = 0
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
            Tools.toast(baseContext,"با موفقیت ارسال شد")
            d.dismiss()
        }

    }


    override fun dismissDialog(s: String) {
        Tools.toast(baseContext,s)
//        bottomSheetFiltering?.dismiss()
    }
}



