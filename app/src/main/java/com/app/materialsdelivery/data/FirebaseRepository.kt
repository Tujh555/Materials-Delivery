package com.app.materialsdelivery.data

import com.app.materialsdelivery.data.mappers.toEntity
import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.utils.Constants
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    private val databaseReference: DatabaseReference
) : MaterialsDeliveryRepository {
    override suspend fun addCompany(company: Company) {
        withContext(Dispatchers.IO) {
            val entity = company.toEntity()
            databaseReference.child(Constants.COMPANIES_CHILD_PATH)
                .child(entity.id.toString())
                .setValue(entity)
        }
    }
}