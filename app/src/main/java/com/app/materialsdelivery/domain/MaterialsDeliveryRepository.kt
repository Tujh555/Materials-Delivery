package com.app.materialsdelivery.domain

import androidx.lifecycle.LiveData
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.entity.Delivery
import com.google.firebase.database.DataSnapshot

interface MaterialsDeliveryRepository{

    suspend fun addCompany(company: Company)

    suspend fun deleteDelivery(delivery: Delivery)

    fun getDelivery(callback: (List<Delivery>) -> Unit)
}