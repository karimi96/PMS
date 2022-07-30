package com.karimi.pms.helper

import androidx.appcompat.app.AppCompatDelegate

object Tools {

     fun checkTheme() {
        when (Session.getInstance().getInt("darkMode")) {
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }



}