package com.sahyadri.samrakshane.di;

import com.sahyadri.samrakshane.data.local.SahyadriDatabase;
import com.sahyadri.samrakshane.data.local.dao.AlertDao;
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
public final class AppModule_ProvideAlertDaoFactory implements Factory<AlertDao> {
  private final Provider<SahyadriDatabase> databaseProvider;

  public AppModule_ProvideAlertDaoFactory(Provider<SahyadriDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AlertDao get() {
    return provideAlertDao(databaseProvider.get());
  }

  public static AppModule_ProvideAlertDaoFactory create(
      Provider<SahyadriDatabase> databaseProvider) {
    return new AppModule_ProvideAlertDaoFactory(databaseProvider);
  }

  public static AlertDao provideAlertDao(SahyadriDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAlertDao(database));
  }
}
