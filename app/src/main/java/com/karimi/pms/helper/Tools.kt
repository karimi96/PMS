package com.karimi.pms.helper

import android.content.Context
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.karimi.pms.R
import kotlinx.android.synthetic.main.activity_home.*

object Tools {

     fun checkTheme() {
        when (Session.getInstance().getInt("darkMode")) {
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


    fun layoutAnimationRecycler(recycler: RecyclerView) {
        val context = recycler.context
        val layoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down)
        recycler.layoutAnimation = layoutAnimationController
        recycler.adapter!!.notifyDataSetChanged()
        recycler.scheduleLayoutAnimation()
    }


     fun toast(c : Context ,text: String) {
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show()
    }



}