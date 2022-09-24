package com.app.materialsdelivery.di

import android.content.Context
import com.app.materialsdelivery.di.modules.AdapterModule
import com.app.materialsdelivery.di.modules.FirebaseModule
import com.app.materialsdelivery.di.modules.RepositoryModule
import com.app.materialsdelivery.di.modules.viewModels.ViewModelModule
import com.app.materialsdelivery.presentation.authorization.AuthorizationFragment
import com.app.materialsdelivery.presentation.suppliesForTheCompany.SuppliesForTheCompanyFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FirebaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        AdapterModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: AuthorizationFragment)
    fun inject(fragment: SuppliesForTheCompanyFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}