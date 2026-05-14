package com.sahyadri.samrakshane.data.remote.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sahyadri.samrakshane.data.local.dao.AlertDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AlertSyncWorker_Factory {
  private final Provider<AlertDao> alertDaoProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public AlertSyncWorker_Factory(Provider<AlertDao> alertDaoProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    this.alertDaoProvider = alertDaoProvider;
    this.firestoreProvider = firestoreProvider;
  }

  public AlertSyncWorker get(Context appContext, WorkerParameters workerParams) {
    return newInstance(appContext, workerParams, alertDaoProvider.get(), firestoreProvider.get());
  }

  public static AlertSyncWorker_Factory create(Provider<AlertDao> alertDaoProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    return new AlertSyncWorker_Factory(alertDaoProvider, firestoreProvider);
  }

  public static AlertSyncWorker newInstance(Context appContext, WorkerParameters workerParams,
      AlertDao alertDao, FirebaseFirestore firestore) {
    return new AlertSyncWorker(appContext, workerParams, alertDao, firestore);
  }
}
