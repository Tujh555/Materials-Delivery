package com.app.materialsdelivery.domain.usecase

import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import javax.inject.Inject

class DeleteAllConfirmationsUseCase @Inject constructor(
    private val repository: MaterialsDeliveryRepository
) {
    suspend operator fun invoke() {
        repository.deleteAllConfirmations()
    }
}