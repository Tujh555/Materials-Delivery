package com.app.materialsdelivery.utils

import android.content.Context
import com.app.materialsdelivery.di.AppComponent
import com.app.materialsdelivery.presentation.MainApplication

val Context.appComponent: AppComponent
    get() = if (this is MainApplication) {
        this.appComponent
    } else {
        this.applicationContext.appComponent
    }