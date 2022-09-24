package com.app.materialsdelivery.di.modules

import com.app.materialsdelivery.presentation.contracts.TakePhotoContract
import dagger.Module
import dagger.Provides

@Module
class ContractsModule {
    @Provides
    fun providePickPhotoContract() = TakePhotoContract()
}