package com.app.materialsdelivery.presentation.companyInfo

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.materialsdelivery.data.mappers.toDomain
import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.usecase.AddCompanyUseCase
import com.app.materialsdelivery.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompanyInfoEditingViewModel @Inject constructor(
    private val addCompanyUseCase: AddCompanyUseCase,
) : ViewModel() {

    fun updateCompanyPhoto(uri: Uri?) {
        updateCompany(
            Constants.currentCompany
                ?.copy(photoUri = uri?.toString())
                ?: CompanyEntity()
                    .copy(photoUri = uri?.toString())
                    .toDomain()
        )
    }

    fun updateCompany(company: Company) {
        Constants.currentCompany = company
        viewModelScope.launch {
            addCompanyUseCase(company)
        }
    }
}