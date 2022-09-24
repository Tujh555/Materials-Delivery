package com.app.materialsdelivery.domain.usecase

import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Company
import javax.inject.Inject

class GetCompanyUseCase @Inject constructor(
    private val repository: MaterialsDeliveryRepository
) {
    fun addCallback(callback: (Company) -> Unit) {
        repository.getCompany(callback)
    }
}