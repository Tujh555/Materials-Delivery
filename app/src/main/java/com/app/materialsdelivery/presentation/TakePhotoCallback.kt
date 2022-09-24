package com.app.materialsdelivery.presentation

import android.net.Uri

interface TakePhotoCallback {
    fun getPhoto(takePhotoCallback: ((Uri?) -> Unit)?)
}