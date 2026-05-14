package com.sahyadri.samrakshane.presentation.viewmodel;

import com.sahyadri.samrakshane.domain.repository.LocationTracker;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class CaptureViewModel_Factory implements Factory<CaptureViewModel> {
  private final Provider<LocationTracker> locationTrackerProvider;

  public CaptureViewModel_Factory(Provider<LocationTracker> locationTrackerProvider) {
    this.locationTrackerProvider = locationTrackerProvider;
  }

  @Override
  public CaptureViewModel get() {
    return newInstance(locationTrackerProvider.get());
  }

  public static CaptureViewModel_Factory create(Provider<LocationTracker> locationTrackerProvider) {
    return new CaptureViewModel_Factory(locationTrackerProvider);
  }

  public static CaptureViewModel newInstance(LocationTracker locationTracker) {
    return new CaptureViewModel(locationTracker);
  }
}
