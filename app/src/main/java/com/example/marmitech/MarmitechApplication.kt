package com.example.marmitech

import android.app.Application
import com.example.marmitech.di.databaseModule
import com.example.marmitech.di.networkModule
import com.example.marmitech.marmitechUI.loginModule
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
            modules(networkModule, loginModule, databaseModule)
        }
    }
}