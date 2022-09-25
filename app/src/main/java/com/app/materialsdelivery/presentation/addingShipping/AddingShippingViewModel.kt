package com.app.materialsdelivery.presentation.addingShipping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.materialsdelivery.domain.entity.Delivery
import com.app.materialsdelivery.domain.usecase.AddDeliveryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddingShippingViewModel @Inject constructor(
    private val addDeliveryUseCase: AddDeliveryUseCase
) : ViewModel() {
    private val _isAddingEnded = MutableLiveData<Unit>()
    val isAddingEnded: LiveData<Unit>
        get() = _isAddingEnded

    fun addDelivery(delivery: Delivery) {
        viewModelScope.launch {
            addDeliveryUseCase(delivery)
            _isAddingEnded.postValue(Unit)
        }
    }
}