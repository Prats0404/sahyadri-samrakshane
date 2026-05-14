package com.sahyadri.samrakshane.di;

import com.google.ai.client.generativeai.GenerativeModel;
import com.sahyadri.samrakshane.domain.repository.GenAiRepository;
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
public final class AppModule_ProvideGenAiRepositoryFactory implements Factory<GenAiRepository> {
  private final Provider<GenerativeModel> generativeModelProvider;

  public AppModule_ProvideGenAiRepositoryFactory(
      Provider<GenerativeModel> generativeModelProvider) {
    this.generativeModelProvider = generativeModelProvider;
  }

  @Override
  public GenAiRepository get() {
    return provideGenAiRepository(generativeModelProvider.get());
  }

  public static AppModule_ProvideGenAiRepositoryFactory create(
      Provider<GenerativeModel> generativeModelProvider) {
    return new AppModule_ProvideGenAiRepositoryFactory(generativeModelProvider);
  }

  public static GenAiRepository provideGenAiRepository(GenerativeModel generativeModel) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGenAiRepository(generativeModel));
  }
}
