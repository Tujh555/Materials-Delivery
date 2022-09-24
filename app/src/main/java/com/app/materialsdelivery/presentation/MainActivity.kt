package com.app.materialsdelivery.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.app.materialsdelivery.R
import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.DeliveryEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.DeliveryItemEntity
import com.app.materialsdelivery.utils.Constants
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseDatabase
            .getInstance()
            .reference
            .child(Constants.DELIVERY_CHILD_PATH)
            .child(13.toString())
            .setValue(
                DeliveryEntity(
                    13,
                    DeliveryItemEntity(),
                    432949,
                    124,
                    2,
                    CompanyEntity(),
                    CompanyEntity(),
                )
            )
    }
}