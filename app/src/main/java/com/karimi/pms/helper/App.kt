package com.karimi.pms.helper

import android.app.Application
import android.content.Context

class App : Application() {
    var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this
    }


}