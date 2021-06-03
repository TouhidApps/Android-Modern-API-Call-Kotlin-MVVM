package com.touhidapps.modernApi

import android.app.Application
import com.getkeepsafe.relinker.ReLinker
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        ReLinker.loadLibrary(this, "native-lib")

    }

}

