package com.sahyadri.samrakshane.presentation.viewmodel;

import android.content.Context;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sahyadri.samrakshane.data.local.dao.AlertDao;
import com.sahyadri.samrakshane.domain.repository.GenAiRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ReviewViewModel_Factory implements Factory<ReviewViewModel> {
  private final Provider<GenAiRepository> genAiRepositoryProvider;

  private final Provider<AlertDao> alertDaoProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<Context> contextProvider;

  public ReviewViewModel_Factory(Provider<GenAiRepository> genAiRepositoryProvider,
      Provider<AlertDao> alertDaoProvider, Provider<FirebaseFirestore> firestoreProvider,
      Provider<Context> contextProvider) {
    this.genAiRepositoryProvider = genAiRepositoryProvider;
    this.alertDaoProvider = alertDaoProvider;
    this.firestoreProvider = firestoreProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public ReviewViewModel get() {
    return newInstance(genAiRepositoryProvider.get(), alertDaoProvider.get(), firestoreProvider.get(), contextProvider.get());
  }

  public static ReviewViewModel_Factory create(Provider<GenAiRepository> genAiRepositoryProvider,
      Provider<AlertDao> alertDaoProvider, Provider<FirebaseFirestore> firestoreProvider,
      Provider<Context> contextProvider) {
    return new ReviewViewModel_Factory(genAiRepositoryProvider, alertDaoProvider, firestoreProvider, contextProvider);
  }

  public static ReviewViewModel newInstance(GenAiRepository genAiRepository, AlertDao alertDao,
      FirebaseFirestore firestore, Context context) {
    return new ReviewViewModel(genAiRepository, alertDao, firestore, context);
  }
}
