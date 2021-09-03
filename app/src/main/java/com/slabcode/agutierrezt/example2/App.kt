package com.slabcode.agutierrezt.example2

import android.app.Application
import com.slabcode.agutierrezt.example2.di.dataSourceModule
import com.slabcode.agutierrezt.example2.di.databaseModule
import com.slabcode.agutierrezt.example2.di.repoModule
import com.slabcode.agutierrezt.example2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataSourceModule, repoModule, viewModelModule, databaseModule))
        }
    }

}