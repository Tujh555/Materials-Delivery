package com.app.materialsdelivery.di.modules

import com.app.materialsdelivery.presentation.suppliesForTheCompany.SuppliesForTheCompanyAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {
    @Provides
    fun provideSuppliesAdapter() = SuppliesForTheCompanyAdapter()
}