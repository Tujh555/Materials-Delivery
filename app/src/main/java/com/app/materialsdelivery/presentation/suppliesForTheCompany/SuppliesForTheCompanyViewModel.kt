package com.app.materialsdelivery.presentation.suppliesForTheCompany

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.materialsdelivery.domain.entity.Delivery
import com.app.materialsdelivery.domain.usecase.DeleteDeliveryUseCase
import com.app.materialsdelivery.domain.usecase.GetDeliveryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SuppliesForTheCompanyViewModel @Inject constructor(
    private val deleteDeliveryUseCase: DeleteDeliveryUseCase,
    private val getDeliveriesUseCase: GetDeliveryUseCase
) : ViewModel() {
    private val _deliveryList = MutableLiveData<List<Delivery>>()
    val deliveryList: LiveData<List<Delivery>>
        get() = _deliveryList

    init {
        getDeliveriesUseCase.addCallback {
            Log.d("MyLogs", "ViewModel" + it.joinToString { " " })
            _deliveryList.postValue(it)
        }
    }

    fun deleteDelivery(delivery: Delivery){
        viewModelScope.launch {
            deleteDeliveryUseCase.invoke(delivery)
        }
    }
}