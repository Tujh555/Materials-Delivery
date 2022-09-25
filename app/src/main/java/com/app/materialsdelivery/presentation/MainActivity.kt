package com.app.materialsdelivery.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.materialsdelivery.R
import com.app.materialsdelivery.data.realtimeDatabaseEntities.NotificationData
import com.app.materialsdelivery.databinding.ActivityMainLayoutBinding
import com.app.materialsdelivery.presentation.contracts.TakePhotoContract
import com.app.materialsdelivery.utils.Constants
import com.app.materialsdelivery.utils.appComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity(), TakePhotoCallback, MenuSwitcher {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var takePhotoContract: TakePhotoContract

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            factory
        ).get(MainActivityViewModel::class.java)
    }

    private val binding by lazy {
        ActivityMainLayoutBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        appComponent.inject(this)
        Constants.createDirectory()
    }

    override fun onStart() {
        super.onStart()

        viewModel.observeNotifications { notificationData ->
            showNotification(notificationData)
        }

        binding.bottomMenu.setOnItemSelectedListener { menuItem ->
            val controller = binding
                .fragmentContainer
                .findNavController()

            when (menuItem.itemId) {
                R.id.delivery_adding -> {
                    controller.navigate(R.id.action_global_adding_shipping_fragment)
                }

                R.id.received_deliveries -> {
                    controller.navigate(R.id.action_global_suppliesForTheCompanyFragment)
                }

                R.id.sent_deliveries -> {
                    controller.navigate(R.id.action_global_supplies_form_company)
                }

                R.id.company_info -> {
                    controller.navigate(R.id.action_global_company_info_fragment)
                }
            }

            true
        }
    }

    private fun showNotification(notificationData: NotificationData) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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

    override fun getPhoto(takePhotoCallback: ((Uri?) -> Unit)?) {
        registerForActivityResult(takePhotoContract) {
            takePhotoCallback?.invoke(it)
        }.launch(null)
    }

    private companion object {
        const val NOTIFICATION_BUILDER_ID = "notification_builder"
        const val NOTIFICATION_ID = 1
        const val NOTIFICATION_CHANNEL_ID = "notification_channel_1"
        const val NOTIFICATION_CHANNEL_NAME = "notification_name"
    }

    override fun switch(isVisible: Boolean) {
        Log.d("MyLogs", "$isVisible")

        binding.bottomMenu.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}