package com.app.materialsdelivery.data

import android.util.Log
import com.app.materialsdelivery.data.mappers.toDomain
import com.app.materialsdelivery.data.mappers.toEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.DeliveryEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.NotificationData
import com.app.materialsdelivery.domain.MaterialsDeliveryRepository
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.entity.Delivery
import com.app.materialsdelivery.utils.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
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

    override suspend fun deleteAllConfirmations() {
        withContext(Dispatchers.IO) {
            databaseReference.child(Constants.CONFIRM_NOTIFICATION_DATA_PATH)
                .removeValue()
        }
    }

    override suspend fun deleteDelivery(delivery: Delivery, isConfirm: Boolean) {
        withContext(Dispatchers.IO) {
            val entity = delivery.toEntity()
            databaseReference.child(Constants.DELIVERY_CHILD_PATH)
                .child(entity.id.toString())
                .removeValue()

            if (isConfirm) {
                val notificationData = NotificationData(
                    deliveryId = delivery.id,
                    companyName = delivery.dispatchCompany?.name ?: "empty",
                    notifiedCompanyName = delivery.destinationCompany?.name ?: "empty"
                )

                databaseReference.child(Constants.CONFIRM_NOTIFICATION_DATA_PATH)
                    .child(delivery.id.toString())
                    .setValue(notificationData)
            }
        }
    }

    override suspend fun addDelivery(delivery: Delivery) {
        val entity = delivery.toEntity()

        databaseReference.child(Constants.DELIVERY_CHILD_PATH)
            .child(entity.id.toString())
            .setValue(entity)
    }

    override fun getDelivery(callback: (List<Delivery>) -> Unit) {
        databaseReference.child(Constants.DELIVERY_CHILD_PATH)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val deliveries = try {
                        snapshot.children.map {
                            Log.d("MyLogs", it.toString())
                            it.getValue(DeliveryEntity::class.java)
                                ?.toDomain()
                                ?: DeliveryEntity().toDomain()
                        }
                    } catch (e: Exception) {
                        Log.e("MyLogs", e.toString())
                        emptyList()
                    }

                    Log.d("MyLogs", "Repository" + deliveries.joinToString { " " })
                    callback(deliveries)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("MyLogs", error.toString())
                }
            })
    }

    override fun getCompany(callback: (Company) -> Unit) {
        databaseReference.child(Constants.COMPANIES_CHILD_PATH)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val company = snapshot.children.firstOrNull {
                        it.getValue(CompanyEntity::class.java)?.id == Constants.currentCompany?.id
                    }?.getValue(CompanyEntity::class.java)
                        ?.toDomain()
                        ?: Constants.currentCompany

                    callback(company!!)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("MyLogs", error.toString())
                }

            })
    }
}