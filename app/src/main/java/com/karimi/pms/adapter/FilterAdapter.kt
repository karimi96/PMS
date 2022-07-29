package com.karimi.pms.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.karimi.pms.R
import com.karimi.pms.helper.Session
import kotlinx.android.synthetic.main.list_item_filtring.view.*

class FilterAdapter(list: List<String>, context: Context, listener: Listener) :
    RecyclerView.Adapter<FilterAdapter.MyViewHolder>() {

    private var context: Context = context
    private var list: List<String> = list
    private var raw = -1
    var listener: Listener = listener


    interface Listener {
        fun dismissDialog(title: String)
    }


    @SuppressLint("ResourceType")
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = view.name!!
        var title2 = view.name_selected!!
        var rv_selected = view.relative_selected!!
        var rv_normal = view.relative_normal!!

        init {
            itemView.setOnClickListener {
                Session.getInstance().clearExtras()
                Session.getInstance().putExtra("pos", position)
                raw = position
                title2.text = list[position]
                rv_selected.visibility = View.VISIBLE
                rv_normal.visibility = View.GONE
                notifyDataSetChanged()
                listener.dismissDialog(list[position])
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

        if (raw == position || Session.getInstance().getInt("pos") == position) {
            holder.title2.text = list[position]
            holder.rv_selected.visibility = View.VISIBLE
            holder.rv_normal.visibility = View.GONE
        } else {
            holder.rv_selected.visibility = View.GONE
            holder.rv_normal.visibility = View.VISIBLE
        }

    }


    override fun getItemCount(): Int {
        return 4
    }


}