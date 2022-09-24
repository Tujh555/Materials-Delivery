package com.app.materialsdelivery.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.app.materialsdelivery.R
import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.DeliveryEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.DeliveryItemEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.NotificationData
import com.app.materialsdelivery.utils.Constants
import com.app.materialsdelivery.utils.appComponent
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            factory
        ).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)

        lifecycleScope.launchWhenCreated {
            delay(12_000)

            FirebaseDatabase
                .getInstance()
                .reference
                .child(Constants.CONFIRM_NOTIFICATION_DATA_PATH)
                .child("10")
                .setValue(
                    NotificationData(
                        124,
                        "Доставляющая компания",
                        "Notified"
                    )
                )
        }
    }

    override fun onStart() {
        super.onStart()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        viewModel.observeNotifications { notificationData ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )

                notificationManager.createNotificationChannel(channel)
            }

            val builder = NotificationCompat.Builder(this, NOTIFICATION_BUILDER_ID)
                .setContentTitle("Уведомление о доставке")
                .setContentText(notificationData.toString())
                .setSmallIcon(R.drawable.ic_baseline_delivery_dining_24)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setChannelId(NOTIFICATION_CHANNEL_ID)

            NotificationManagerCompat.from(this)
                .notify(NOTIFICATION_ID, builder.build())
        }
    }

    private companion object {
        const val NOTIFICATION_BUILDER_ID = "notification_builder"
        const val NOTIFICATION_ID = 1
        const val NOTIFICATION_CHANNEL_ID = "notification_channel_1"
        const val NOTIFICATION_CHANNEL_NAME = "notification_name"
    }
}