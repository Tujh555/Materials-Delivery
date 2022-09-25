package com.app.materialsdelivery.domain.usecase

import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Delivery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddDeliveryUseCase @Inject constructor(
    private val repository: MaterialsDeliveryRepository
) {
    suspend operator fun invoke(delivery: Delivery) {
        withContext(Dispatchers.IO) {
            repository.addDelivery(delivery)
        }
    }
}