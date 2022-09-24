package com.app.materialsdelivery.presentation.suppliesFromCompany

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.materialsdelivery.domain.entity.Delivery
import com.app.materialsdelivery.domain.usecase.ConfirmDeleteDeliveryUseCase
import com.app.materialsdelivery.domain.usecase.GetDeliveryUseCase
import com.app.materialsdelivery.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class SuppliesFromCompanyViewModel @Inject constructor(
    private val confirmDeleteDeliveryUseCase: ConfirmDeleteDeliveryUseCase,
    private val getDeliveriesUseCase: GetDeliveryUseCase
) : ViewModel() {
    private val _deliveryList = MutableLiveData<List<Delivery>>()
    val deliveryList: LiveData<List<Delivery>>
        get() = _deliveryList

    init {
        getDeliveriesUseCase.addCallback { deliveries ->
            _deliveryList.postValue(
                deliveries.filter {
                    it.dispatchCompany?.name == Constants.currentCompany?.name
                }
            )
        }
    }

    fun deleteDelivery(delivery: Delivery, confirmed: Boolean){
        viewModelScope.launch {
            confirmDeleteDeliveryUseCase.invoke(delivery, confirmed)
        }
    }
}