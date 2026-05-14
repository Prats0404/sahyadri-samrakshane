package com.sahyadri.samrakshane.di;

import com.google.ai.client.generativeai.GenerativeModel;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AppModule_ProvideGenerativeModelFactory implements Factory<GenerativeModel> {
  @Override
  public GenerativeModel get() {
    return provideGenerativeModel();
  }

  public static AppModule_ProvideGenerativeModelFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GenerativeModel provideGenerativeModel() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGenerativeModel());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideGenerativeModelFactory INSTANCE = new AppModule_ProvideGenerativeModelFactory();
  }
}
