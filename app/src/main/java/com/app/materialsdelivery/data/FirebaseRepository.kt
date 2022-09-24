package com.app.materialsdelivery.data

import android.util.Log
import android.widget.NumberPicker.OnValueChangeListener
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.app.materialsdelivery.data.mappers.toDomain
import com.app.materialsdelivery.data.mappers.toEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.DeliveryEntity
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

    override suspend fun deleteDelivery(delivery: Delivery) {
        val entity = delivery.toEntity()
        databaseReference.child(Constants.DELIVERY_CHILD_PATH)
            .child(entity.id.toString())
            .removeValue()
    }

    override fun getDelivery(callback: (List<Delivery>) -> Unit) {
        databaseReference.child(Constants.DELIVERY_CHILD_PATH)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val deliveries = try {
                        snapshot.children.map {
                            it.getValue(DeliveryEntity::class.java)
                                ?.toDomain()
                                ?: DeliveryEntity().toDomain()
                        }
                    } catch (e: Exception) {
                        Log.e("MyLogs", e.toString())
                        emptyList()
                    }

                    callback(deliveries)
                }

                override fun onCancelled(error: DatabaseError) {  }

            })
    }
}