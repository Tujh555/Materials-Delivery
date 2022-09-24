package com.app.materialsdelivery.di

import android.content.Context
import com.app.materialsdelivery.di.modules.AdapterModule
import com.app.materialsdelivery.di.modules.ContractsModule
import com.app.materialsdelivery.di.modules.FirebaseModule
import com.app.materialsdelivery.di.modules.RepositoryModule
import com.app.materialsdelivery.di.modules.viewModels.ViewModelModule
import com.app.materialsdelivery.presentation.MainActivity
import com.app.materialsdelivery.presentation.authorization.AuthorizationFragment
import com.app.materialsdelivery.presentation.companyInfo.CompanyInfoEditingFragment
import com.app.materialsdelivery.presentation.suppliesForTheCompany.SuppliesForTheCompanyFragment
import com.app.materialsdelivery.presentation.suppliesFromCompany.SuppliesFromCompanyFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FirebaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        AdapterModule::class,
        ContractsModule::class,
    ]
)
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: AuthorizationFragment)
    fun inject(fragment: SuppliesForTheCompanyFragment)
    fun inject(fragment: CompanyInfoEditingFragment)
    fun inject(fragment: SuppliesFromCompanyFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}