package com.app.materialsdelivery.domain

import com.app.materialsdelivery.domain.entity.Company

interface MaterialsDeliveryRepository{

    suspend fun addCompany(company: Company)

}