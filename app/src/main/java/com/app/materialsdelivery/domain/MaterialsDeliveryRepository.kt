package com.app.materialsdelivery.domain

import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.entity.Delivery

interface MaterialsDeliveryRepository{

    suspend fun addCompany(company: Company)

    suspend fun deleteDelivery(delivery: Delivery, isConfirm: Boolean)

    suspend fun addDelivery(delivery: Delivery)

    suspend fun deleteAllConfirmations()

    fun getDelivery(callback: (List<Delivery>) -> Unit)

    fun getCompany(callback: (Company) -> Unit)
}