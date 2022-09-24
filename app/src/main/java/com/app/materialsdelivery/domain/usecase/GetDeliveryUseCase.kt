package com.app.materialsdelivery.domain.usecase

import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Delivery
import javax.inject.Inject

class GetDeliveryUseCase @Inject constructor(
    private val repository: MaterialsDeliveryRepository
){

    fun addCallback(callback: (List<Delivery>) -> Unit) {
        repository.getDelivery(callback)
    }
}