package com.sahyadri.samrakshane.di;

import android.content.Context;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.sahyadri.samrakshane.domain.repository.LocationTracker;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideLocationTrackerFactory implements Factory<LocationTracker> {
  private final Provider<FusedLocationProviderClient> fusedLocationProviderClientProvider;

  private final Provider<Context> contextProvider;

  public AppModule_ProvideLocationTrackerFactory(
      Provider<FusedLocationProviderClient> fusedLocationProviderClientProvider,
      Provider<Context> contextProvider) {
    this.fusedLocationProviderClientProvider = fusedLocationProviderClientProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public LocationTracker get() {
    return provideLocationTracker(fusedLocationProviderClientProvider.get(), contextProvider.get());
  }

  public static AppModule_ProvideLocationTrackerFactory create(
      Provider<FusedLocationProviderClient> fusedLocationProviderClientProvider,
      Provider<Context> contextProvider) {
    return new AppModule_ProvideLocationTrackerFactory(fusedLocationProviderClientProvider, contextProvider);
  }

  public static LocationTracker provideLocationTracker(
      FusedLocationProviderClient fusedLocationProviderClient, Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideLocationTracker(fusedLocationProviderClient, context));
  }
}
