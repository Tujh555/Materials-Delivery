package com.app.materialsdelivery.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.app.materialsdelivery.data.realtimeDatabaseEntities.NotificationData
import com.app.materialsdelivery.utils.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val databaseReference: DatabaseReference,
) : ViewModel() {

    fun observeNotifications(callback: (NotificationData) -> Unit) {
        databaseReference.child(Constants.CONFIRM_NOTIFICATION_DATA_PATH)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Constants.currentCompany?.let { currentCompany ->
                        snapshot.children.forEach { dataSnapshot ->
                            val notificationData = dataSnapshot.getValue(NotificationData::class.java)

                            if (currentCompany.name == notificationData?.notifiedCompanyName) {
                                callback(notificationData)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {  }

            })
    }
}