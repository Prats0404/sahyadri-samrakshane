package com.sahyadri.samrakshane.data.remote.worker;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.annotation.processing.Generated;

@Generated("androidx.hilt.AndroidXHiltProcessor")
@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = AlertSyncWorker.class
)
public interface AlertSyncWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.sahyadri.samrakshane.data.remote.worker.AlertSyncWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(AlertSyncWorker_AssistedFactory factory);
}
