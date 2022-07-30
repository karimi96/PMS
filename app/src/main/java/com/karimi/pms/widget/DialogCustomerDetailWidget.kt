package com.karimi.pms.widget

import android.app.Dialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.karimi.pms.R
import com.karimi.pms.adapter.RequestAdapter
import com.karimi.pms.modal.Request
import kotlinx.android.synthetic.main.box_chat.*
import kotlinx.android.synthetic.main.dialog_customer_detail.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.overlay.Marker

class DialogCustomerDetailWidget(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) ,RequestAdapter.Listener{

    init{
//        RequestAdapter.ff.initTest(this)
        inflate(context , R.layout.dialog_customer_detail , this) }

    override fun showDialogCustomerDetail() {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_customer_detail)
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
//            initDialogReasonOfCancel()
        }

        dialog.tv_done.setOnClickListener {
            dialog.dismiss()
//            initDialogSaveActivity()
        }    }





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
        marker.icon = context.getDrawable(R.drawable.ic_location)
        d.map.overlays.add(marker)
        d.map.invalidate()
    }


}