package com.app.materialsdelivery.di.modules

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Singleton
    @Provides
    fun provideDatabaseReference(context: Context): DatabaseReference {
        FirebaseApp.initializeApp(context)

        return FirebaseDatabase.getInstance().reference
    }
}