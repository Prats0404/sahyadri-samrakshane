package com.sahyadri.samrakshane;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SahyadriApp_MembersInjector implements MembersInjector<SahyadriApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public SahyadriApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<SahyadriApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new SahyadriApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(SahyadriApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.sahyadri.samrakshane.SahyadriApp.workerFactory")
  public static void injectWorkerFactory(SahyadriApp instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
