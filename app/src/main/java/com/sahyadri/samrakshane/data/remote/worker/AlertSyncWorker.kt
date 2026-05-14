package com.sahyadri.samrakshane.data.remote.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sahyadri.samrakshane.data.local.dao.AlertDao
import com.sahyadri.samrakshane.domain.model.SyncStatus
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@HiltWorker
class AlertSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val alertDao: AlertDao,
    private val firestore: FirebaseFirestore
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val pendingAlerts = alertDao.getPendingAlerts()

        if (pendingAlerts.isEmpty()) {
            return Result.success()
        }

        return try {
            pendingAlerts.forEach { alert ->
                firestore.collection("alerts").document(alert.alertId).set(alert).await()
                alertDao.updateAlert(alert.copy(syncStatus = SyncStatus.SYNCED))
            }
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}
