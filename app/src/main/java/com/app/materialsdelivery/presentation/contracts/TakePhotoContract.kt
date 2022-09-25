package com.app.materialsdelivery.presentation.contracts

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.app.materialsdelivery.utils.Constants
import java.io.File
import javax.inject.Inject

class TakePhotoContract @Inject constructor() : ActivityResultContract<PackageManager, Uri?>() {
    override fun createIntent(context: Context, input: PackageManager): Intent {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri())
        intent.resolveActivity(input)

        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        Log.d("MyLogs", intent?.dataString ?: "null")
        return intent?.data
    }

    private fun generateFileUri(): Uri? {
        return Constants.directory?.let { directory ->
            val file = File(
                directory.path + "/" + "photo_" + System.currentTimeMillis() + ".jpg"
            )

            Uri.fromFile(file)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }
}