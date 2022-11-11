package com.example.marmitech

import android.app.Application
import com.example.marmitech.marmitechUI.marmitechModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarmitechApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        initModules()
    }

    private fun initModules(){
        startKoin {
            androidContext(this@MarmitechApplication)
            modules(marmitechModule)
        }
    }
}