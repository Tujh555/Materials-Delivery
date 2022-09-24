package com.app.materialsdelivery.presentation.fragments.suppliesForTheCompany

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.materialsdelivery.domain.entity.Delivery
import com.app.materialsdelivery.domain.usecase.ConfirmDeleteDeliveryUseCase
import com.app.materialsdelivery.domain.usecase.GetDeliveryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SuppliesForTheCompanyViewModel @Inject constructor(
    private val confirmDeleteDeliveryUseCase: ConfirmDeleteDeliveryUseCase,
    private val getDeliveriesUseCase: GetDeliveryUseCase
) : ViewModel() {
    private val _deliveryList = MutableLiveData<List<Delivery>>()
    val deliveryList: LiveData<List<Delivery>>
        get() = _deliveryList

    init {
        getDeliveriesUseCase.addCallback { deliveries ->
            Log.d("MyLogs", "ViewModel" + deliveries.joinToString {
                it.toString()
            })
            _deliveryList.postValue(deliveries)
        }
    }

    fun deleteDelivery(delivery: Delivery){
        viewModelScope.launch {
            confirmDeleteDeliveryUseCase.invoke(delivery)
        }
    }
}