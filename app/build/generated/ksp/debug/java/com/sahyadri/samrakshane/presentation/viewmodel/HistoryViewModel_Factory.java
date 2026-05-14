package com.sahyadri.samrakshane.presentation.viewmodel;

import com.sahyadri.samrakshane.domain.repository.AlertRepository;
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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<AlertRepository> alertRepositoryProvider;

  public HistoryViewModel_Factory(Provider<AlertRepository> alertRepositoryProvider) {
    this.alertRepositoryProvider = alertRepositoryProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(alertRepositoryProvider.get());
  }

  public static HistoryViewModel_Factory create(Provider<AlertRepository> alertRepositoryProvider) {
    return new HistoryViewModel_Factory(alertRepositoryProvider);
  }

  public static HistoryViewModel newInstance(AlertRepository alertRepository) {
    return new HistoryViewModel(alertRepository);
  }
}
