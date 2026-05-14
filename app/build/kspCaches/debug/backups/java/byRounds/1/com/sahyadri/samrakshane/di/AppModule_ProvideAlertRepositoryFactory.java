package com.sahyadri.samrakshane.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.sahyadri.samrakshane.domain.repository.AlertRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideAlertRepositoryFactory implements Factory<AlertRepository> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public AppModule_ProvideAlertRepositoryFactory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public AlertRepository get() {
    return provideAlertRepository(firestoreProvider.get());
  }

  public static AppModule_ProvideAlertRepositoryFactory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new AppModule_ProvideAlertRepositoryFactory(firestoreProvider);
  }

  public static AlertRepository provideAlertRepository(FirebaseFirestore firestore) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAlertRepository(firestore));
  }
}
