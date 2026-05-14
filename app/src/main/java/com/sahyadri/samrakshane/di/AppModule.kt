package com.sahyadri.samrakshane.di

import android.content.Context
import androidx.room.Room
import com.google.ai.client.generativeai.GenerativeModel
import com.sahyadri.samrakshane.data.local.SahyadriDatabase
import com.sahyadri.samrakshane.data.local.dao.AlertDao
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sahyadri.samrakshane.BuildConfig
import com.sahyadri.samrakshane.data.repository.DefaultAlertRepository
import com.sahyadri.samrakshane.data.repository.DefaultGenAiRepository
import com.sahyadri.samrakshane.data.repository.DefaultLocationTracker
import com.sahyadri.samrakshane.domain.repository.AlertRepository
import com.sahyadri.samrakshane.domain.repository.GenAiRepository
import com.sahyadri.samrakshane.domain.repository.LocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SahyadriDatabase {
        return Room.databaseBuilder(
            context,
            SahyadriDatabase::class.java,
            "sahyadri_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAlertDao(database: SahyadriDatabase): AlertDao {
        return database.alertDao()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient,
        @ApplicationContext context: Context
    ): LocationTracker {
        return DefaultLocationTracker(fusedLocationProviderClient, context)
    }

    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-2.5-flash",
            apiKey = BuildConfig.GEMINI_API_KEY
        )
    }

    @Provides
    @Singleton
    fun provideGenAiRepository(generativeModel: GenerativeModel): GenAiRepository {
        return DefaultGenAiRepository(generativeModel)
    }

    @Provides
    @Singleton
    fun provideAlertRepository(firestore: FirebaseFirestore): AlertRepository {
        return DefaultAlertRepository(firestore)
    }
}
