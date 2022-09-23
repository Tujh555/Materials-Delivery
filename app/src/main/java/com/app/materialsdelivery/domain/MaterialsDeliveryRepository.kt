package com.app.materialsdelivery.domain

import androidx.lifecycle.LiveData
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.entity.Delivery

interface MaterialsDeliveryRepository{

    suspend fun addCompany(company: Company)

    suspend fun deleteDelivery(delivery: Delivery)

    fun getDelivery(): LiveData<List<Delivery>>


}