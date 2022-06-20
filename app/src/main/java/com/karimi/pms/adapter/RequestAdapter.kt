package com.karimi.pms.adapter

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.karimi.pms.R
import com.karimi.pms.modal.Request
import kotlinx.android.synthetic.main.list_item_home.view.*

class RequestAdapter(list: ArrayList<Request>, context: Context) : RecyclerView.Adapter<RequestAdapter.MyViewHolder>() {
    var context :Context = context
    private var list: ArrayList<Request> = list


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cardView = view.cardView
        var title = view.title
        var address = view.address
        var date = view.date
        var dayName = view.dayName
        var place = view.img_place
        var circle = view.circle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(layoutInflater.inflate(R.layout.list_item_home, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var request = list[position]
        if (request.dayName == "فوری"){
            arrayOf(holder.date , holder.dayName).forEach { it.setTextColor(Color.parseColor("#E4897B"))  }
            holder.circle.background = context.getDrawable(R.drawable.circle_red)
            holder.place.setImageDrawable(context.getDrawable(R.drawable.place_red_ic))
            holder.cardView.foreground = context.getDrawable(R.drawable.item_border_red)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) { holder.cardView.outlineSpotShadowColor =Color.parseColor("#E4897B") }
        }else if (request.dayName == "امروز"){
            holder.circle.background = context.getDrawable(R.drawable.circle_blue)
            arrayOf(holder.date , holder.dayName).forEach { it.setTextColor(context.getColor(R.color.blue))  }
            holder.place.setImageDrawable(context.getDrawable(R.drawable.place_blue_ic))
        }

        holder.title.text = request.title
        holder.date.text = request.date
        holder.address.text = request.address
        holder.dayName.text = request.dayName
    }

    override fun getItemCount(): Int {
        return list.size
    }


}