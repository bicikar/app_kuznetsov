package com.kirillkuznetsov.firstapplication

import android.app.Application
import com.kirillkuznetsov.firstapplication.ui.BuildConfig
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger(){
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}