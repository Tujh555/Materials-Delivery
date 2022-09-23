package com.app.materialsdelivery.domain.usecase

import androidx.lifecycle.LiveData
import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Delivery

class GetDeliveryUseCase(private val repository: MaterialsDeliveryRepository){

    operator fun invoke(): LiveData<List<Delivery>>{
        return repository.getDelivery()
    }

}