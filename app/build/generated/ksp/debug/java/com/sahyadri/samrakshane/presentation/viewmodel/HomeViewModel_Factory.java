package com.sahyadri.samrakshane.presentation.viewmodel;

import com.google.firebase.firestore.FirebaseFirestore;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public HomeViewModel_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(firestoreProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<FirebaseFirestore> firestoreProvider) {
    return new HomeViewModel_Factory(firestoreProvider);
  }

  public static HomeViewModel newInstance(FirebaseFirestore firestore) {
    return new HomeViewModel(firestore);
  }
}
