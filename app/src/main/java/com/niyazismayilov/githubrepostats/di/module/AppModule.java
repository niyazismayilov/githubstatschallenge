package com.niyazismayilov.githubrepostats.di.module;


import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niyazismayilov.githubrepostats.BuildConfig;
import com.niyazismayilov.githubrepostats.data.AppDataManager;
import com.niyazismayilov.githubrepostats.data.IDataManager;
import com.niyazismayilov.githubrepostats.data.api.IApi;
import com.niyazismayilov.githubrepostats.data.local.CachedRepository;
import com.niyazismayilov.githubrepostats.utils.ViewModelProviderFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(Application application) {
        return application;
    }


    @Provides
    IApi provideApi(Retrofit retrofit) {
        return retrofit.create(IApi.class);
    }

    @Provides
    IDataManager provideDataManager(IApi iApiMethod, CachedRepository iCacheRepository) {
        return new AppDataManager(iApiMethod,iCacheRepository);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                             OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .callTimeout(1, TimeUnit.MINUTES)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }


}