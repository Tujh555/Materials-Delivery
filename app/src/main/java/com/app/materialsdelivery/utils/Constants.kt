package com.app.materialsdelivery.utils

import android.os.Environment
import com.app.materialsdelivery.data.mappers.toDomain
import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.domain.entity.Company
import java.io.File

object Constants {
    const val COMPANIES_CHILD_PATH = "companies"
    const val DELIVERY_ITEMS_CHILD_PAHT = "delivery_items"
    const val DELIVERY_CHILD_PATH = "deliveries"
    const val CONFIRM_NOTIFICATION_DATA_PATH = "confirmation"

    var currentCompany: Company? = null
    var directory: File? = null

    fun createDirectory() {
        directory = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "MyFolder"
        )

        directory?.let { it ->
            if (!it.exists()) {
                it.mkdirs()
            }
        }
    }
}