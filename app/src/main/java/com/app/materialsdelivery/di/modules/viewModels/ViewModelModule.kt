package com.app.materialsdelivery.di.modules.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.materialsdelivery.presentation.MainActivityViewModel
import com.app.materialsdelivery.presentation.addingShipping.AddingShippingViewModel
import com.app.materialsdelivery.presentation.authorization.AuthorizationFragment
import com.app.materialsdelivery.presentation.authorization.AuthorizationViewModel
import com.app.materialsdelivery.presentation.companyInfo.CompanyInfoEditingViewModel
import com.app.materialsdelivery.presentation.suppliesForTheCompany.SuppliesForTheCompanyViewModel
import com.app.materialsdelivery.presentation.suppliesFromCompany.SuppliesFromCompanyViewModel
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

    @IntoMap
    @Binds
    @ViewModelKey(CompanyInfoEditingViewModel::class)
    fun bindInfoEditing(model: CompanyInfoEditingViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SuppliesFromCompanyViewModel::class)
    fun bindSuppliesFromViewModel(model: SuppliesFromCompanyViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(AddingShippingViewModel::class)
    fun bindAddingViewModel(model: AddingShippingViewModel): ViewModel
}