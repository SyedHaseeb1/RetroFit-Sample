package com.hsb.syedhaseebtaks_janbark.koin

import android.app.Application
import com.hsb.syedhaseebtaks_janbark.viewModel.MyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val module = module {

    single { MyViewModel(androidContext() as Application) }
}