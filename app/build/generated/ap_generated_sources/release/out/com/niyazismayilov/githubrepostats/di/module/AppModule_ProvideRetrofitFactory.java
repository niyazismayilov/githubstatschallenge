// Generated by Dagger (https://dagger.dev).
package com.niyazismayilov.githubrepostats.di.module;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final AppModule module;

  private final Provider<Gson> gsonProvider;

  private final Provider<RxJava2CallAdapterFactory> rxJava2CallAdapterFactoryProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public AppModule_ProvideRetrofitFactory(AppModule module, Provider<Gson> gsonProvider,
      Provider<RxJava2CallAdapterFactory> rxJava2CallAdapterFactoryProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    this.module = module;
    this.gsonProvider = gsonProvider;
    this.rxJava2CallAdapterFactoryProvider = rxJava2CallAdapterFactoryProvider;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public Retrofit get() {
    return provideRetrofit(module, gsonProvider.get(), rxJava2CallAdapterFactoryProvider.get(), okHttpClientProvider.get());
  }

  public static AppModule_ProvideRetrofitFactory create(AppModule module,
      Provider<Gson> gsonProvider,
      Provider<RxJava2CallAdapterFactory> rxJava2CallAdapterFactoryProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    return new AppModule_ProvideRetrofitFactory(module, gsonProvider, rxJava2CallAdapterFactoryProvider, okHttpClientProvider);
  }

  public static Retrofit provideRetrofit(AppModule instance, Gson gson,
      RxJava2CallAdapterFactory rxJava2CallAdapterFactory, OkHttpClient okHttpClient) {
    return Preconditions.checkNotNull(instance.provideRetrofit(gson, rxJava2CallAdapterFactory, okHttpClient), "Cannot return null from a non-@Nullable @Provides method");
  }
}
