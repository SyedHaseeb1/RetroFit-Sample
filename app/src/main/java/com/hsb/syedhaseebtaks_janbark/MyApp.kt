package com.hsb.syedhaseebtaks_janbark

import android.app.Application
import com.hsb.syedhaseebtaks_janbark.koin.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(module)
        }
    }
}