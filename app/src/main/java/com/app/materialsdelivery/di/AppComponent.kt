package com.app.materialsdelivery.di

import android.content.Context
import com.app.materialsdelivery.di.modules.FirebaseModule
import com.app.materialsdelivery.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FirebaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}