package com.app.materialsdelivery.di.modules.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.materialsdelivery.presentation.MainActivityViewModel
import com.app.materialsdelivery.presentation.authorization.AuthorizationFragment
import com.app.materialsdelivery.presentation.authorization.AuthorizationViewModel
import com.app.materialsdelivery.presentation.suppliesForTheCompany.SuppliesForTheCompanyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(AuthorizationViewModel::class)
    fun bindAuthorizationViewModel(model: AuthorizationViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SuppliesForTheCompanyViewModel::class)
    fun bindSuppliesViewModel(model: SuppliesForTheCompanyViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityViewModel(model: MainActivityViewModel): ViewModel
}