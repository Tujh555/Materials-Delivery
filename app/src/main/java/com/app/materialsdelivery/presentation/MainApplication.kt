package com.app.materialsdelivery.presentation

import android.app.Application
import com.app.materialsdelivery.di.AppComponent

class MainApplication : Application() {
    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent
        get() = requireNotNull(_appComponent) {
            "AppComponent must be not null"
        }

    override fun onCreate() {
        super.onCreate()

        _appComponent = Dagger
            .builder()
            .context(this)
            .build()
    }
}