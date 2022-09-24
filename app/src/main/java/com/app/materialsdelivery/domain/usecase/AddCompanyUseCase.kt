package com.app.materialsdelivery.domain.usecase

import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Company
import javax.inject.Inject

class AddCompanyUseCase @Inject constructor(
    private val repository: MaterialsDeliveryRepository
) {

    suspend operator fun invoke(company: Company) {
        repository.addCompany(company)
    }

}