package com.app.materialsdelivery.presentation.contracts

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import javax.inject.Inject

class TakePhotoContract @Inject constructor() :ActivityResultContract<Uri, Unit>() {
    override fun createIntent(context: Context, input: Uri): Intent {
        val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, input)
        }

        val cameraActivities: List<ResolveInfo> = context.packageManager.queryIntentActivities(
            captureImage,
            PackageManager.MATCH_DEFAULT_ONLY
        )

        for (cameraActivity in cameraActivities) {
            context.grantUriPermission(
                cameraActivity.activityInfo.packageName,
                input,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        }

        return captureImage
    }

    override fun parseResult(resultCode: Int, intent: Intent?) {
        Log.d("MyLog", "Camera result was got")
    }
}