package com.example.dsk_one_test

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}