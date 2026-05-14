package com.sahyadri.samrakshane.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.sahyadri.samrakshane.data.local.entity.AlertEntity
import com.sahyadri.samrakshane.domain.repository.AlertRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DefaultAlertRepository(
    private val firestore: FirebaseFirestore
) : AlertRepository {

    override fun getAlertsRealtime(userId: String): Flow<List<AlertEntity>> = callbackFlow {
        val subscription = firestore.collection("alerts")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val alerts = snapshot.toObjects(AlertEntity::class.java)
                    trySend(alerts)
                }
            }

        awaitClose { subscription.remove() }
    }
}
