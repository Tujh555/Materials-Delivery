package com.app.materialsdelivery.domain.usecase

import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Company

class AddCompanyUseCase(private val repository: MaterialsDeliveryRepository) {

    suspend operator fun invoke(company: Company) {
        repository.addCompany(company)
    }

}