package com.app.materialsdelivery.presentation.suppliesForTheCompany

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.materialsdelivery.TestImpl
import com.app.materialsdelivery.domain.entity.Delivery
import com.app.materialsdelivery.domain.usecase.DeleteDeliveryUseCase
import com.app.materialsdelivery.domain.usecase.GetDeliveryUseCase
import kotlinx.coroutines.launch

class SuppliesForTheCompanyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TestImpl(application)
    private val deleteDeliveryUseCase = DeleteDeliveryUseCase(repository)

    val deliveryList = GetDeliveryUseCase(repository).invoke()

    fun deleteDelivery(delivery: Delivery){
        viewModelScope.launch {
            deleteDeliveryUseCase.invoke(delivery)
        }
    }






}