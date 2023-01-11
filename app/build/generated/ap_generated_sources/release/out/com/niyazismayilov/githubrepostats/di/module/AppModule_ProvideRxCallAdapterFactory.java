// Generated by Dagger (https://dagger.dev).
package com.niyazismayilov.githubrepostats.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideRxCallAdapterFactory implements Factory<RxJava2CallAdapterFactory> {
  private final AppModule module;

  public AppModule_ProvideRxCallAdapterFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public RxJava2CallAdapterFactory get() {
    return provideRxCallAdapter(module);
  }

  public static AppModule_ProvideRxCallAdapterFactory create(AppModule module) {
    return new AppModule_ProvideRxCallAdapterFactory(module);
  }

  public static RxJava2CallAdapterFactory provideRxCallAdapter(AppModule instance) {
    return Preconditions.checkNotNull(instance.provideRxCallAdapter(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
