package com.app.materialsdelivery.presentation.authorization

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.app.materialsdelivery.TestImpl
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.usecase.AddCompanyUseCase
import kotlinx.coroutines.launch

class AuthorizationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TestImpl(application)
    private val addCompanyUseCase = AddCompanyUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> get() = _errorInputName

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit> get() = _shouldCloseScreen

    private val _errorInputCity = MutableLiveData<Boolean>()
    val errorInputCity: LiveData<Boolean> get() = _errorInputCity

    private fun parseInputData(name: String?): String {
        return name?.trim() ?: ""
    }

    private fun validateInput(name: String, city: String): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (city.isBlank()) {
            _errorInputCity.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCity() {
        _errorInputCity.value = false
    }

    fun addCompany(name: String?, city: String?) {
        viewModelScope.launch {
            val inputName = parseInputData(name)
            val inputCity = parseInputData(city)
            if (validateInput(inputName, inputCity)) {
                val company = Company(
                    0,
                    inputName,
                    inputCity,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
                addCompanyUseCase.invoke(company)
                Log.d("testUseCase", "good work")
            }
        }
    }

    fun checkInputDataFromDb(name: String?, city: String){
        TODO("реализация проверки аккаунта в базе данных")
    }
}