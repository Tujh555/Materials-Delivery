package com.app.materialsdelivery

import android.app.Application
import android.util.Log
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.MaterialsDeliveryRepository

class TestImpl(val application: Application): MaterialsDeliveryRepository {


    override suspend fun addCompany(company: Company) {
        Log.d("testUseCase", "${company.name} ${company.cityName}")
    }
}