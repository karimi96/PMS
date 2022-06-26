package com.karimi.pms.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.karimi.pms.R
import com.karimi.pms.modal.Request

class DialogWidget(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    var a : ArrayList<Request> = ArrayList()
//     var adapter :RequestAdapter

    init {
//        adapter = RequestAdapter(a,this.context )
//        adapter.init(this)
        inflate(context , R.layout.dialog_filter_detail , this)
    }

//    override fun showDialog() {
//        Toast.makeText(context , "hello" , Toast.LENGTH_SHORT).show()
//        val metrics = resources.displayMetrics
//            val width = metrics.widthPixels
//            val height = metrics.heightPixels
//            val dialog = Dialog(context)
//            dialog.setContentView(R.layout.dialog_filter_detail)
//            dialog.setCancelable(false)
//            dialog.window?.setLayout(
//                ((6.3 * width) / 7).toInt(),
//                ViewGroup.LayoutParams.WRAP_CONTENT)
////            dialog.window?.setBackgroundDrawableResource(R.drawable.border_dialog)
//            dialog.show()
//    }


}