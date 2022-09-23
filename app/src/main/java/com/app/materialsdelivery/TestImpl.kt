package com.app.materialsdelivery

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.entity.Delivery
import com.app.materialsdelivery.domain.entity.DeliveryItem

class TestImpl(val application: Application): MaterialsDeliveryRepository {

    private var list = mutableListOf<Delivery>()
    private val ld = MutableLiveData<List<Delivery>>()

    init {
        for (i in 0..10){
            val item = Delivery(i, DeliveryItem(i, "", i.toLong()), i.toLong(), i.toLong(), i,
                null, null)
            list.add(item)
        }
    }

    override suspend fun addCompany(company: Company) {
        Log.d("testUseCase", "${company.name} ${company.cityName}")
    }

    override suspend fun deleteDelivery(delivery: Delivery) {
        list.remove(delivery)
        update()
    }

    override fun getDelivery(): LiveData<List<Delivery>> = ld

    private fun update(){
        ld.value = list
    }


}