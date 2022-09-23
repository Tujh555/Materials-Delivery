package com.app.materialsdelivery.di.modules

import com.app.materialsdelivery.data.FirebaseRepository
import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindRepository(repository: FirebaseRepository): MaterialsDeliveryRepository
}