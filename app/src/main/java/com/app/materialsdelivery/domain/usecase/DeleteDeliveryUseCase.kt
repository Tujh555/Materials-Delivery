package com.app.materialsdelivery.domain.usecase

import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Delivery

class DeleteDeliveryUseCase(private val repository: MaterialsDeliveryRepository) {

    suspend operator fun invoke(delivery: Delivery){
        repository.deleteDelivery(delivery)
    }

}