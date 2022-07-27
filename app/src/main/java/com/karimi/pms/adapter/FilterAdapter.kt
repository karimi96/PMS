package com.karimi.pms.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.karimi.pms.R
import kotlinx.android.synthetic.main.list_item_filtring.view.*

class FilterAdapter(list: ArrayList<String>, context: Context) :
    RecyclerView.Adapter<FilterAdapter.MyViewHolder>() {
    var context: Context = context
    private var list: ArrayList<String> = list
//    var listener: Listener = listener


    interface Listener {
        fun showDialog()
    }


    @SuppressLint("ResourceType")
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = view.name!!
        var img = view.image_filter!!
        var relative = view.relative_filtering!!

        init {
            itemView.setOnClickListener {
                title.setTextColor(android.R.attr.textColor)
//                title.setTextColor(R.color.blue_night)
                img.visibility = View.VISIBLE
                relative.setBackgroundColor(Color.parseColor("#292929"))
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(layoutInflater.inflate(R.layout.list_item_filtring, parent, false))
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var filter = list[position]
        holder.title.text = filter
    }


    override fun getItemCount(): Int {
        return 4
    }


}