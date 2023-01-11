package com.niyazismayilov.githubrepostats;

import android.app.Application;

import com.niyazismayilov.githubrepostats.di.component.AppComponent;
import com.niyazismayilov.githubrepostats.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class ApplicationClass extends Application implements HasAndroidInjector {
    public AppComponent appComponent;
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);

    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}