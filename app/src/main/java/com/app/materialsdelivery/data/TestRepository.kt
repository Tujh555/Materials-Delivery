package com.app.materialsdelivery.data

import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class TestRepository @Inject constructor(
    private val databaseReference: DatabaseReference
) {
    fun addCompany(companyEntity: CompanyEntity) {
        TODO()
    }
}