package com.app.materialsdelivery.di

import android.content.Context
import com.app.materialsdelivery.di.modules.FirebaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FirebaseModule::class])
interface AppComponent {
    fun inject(fragment: TestFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}