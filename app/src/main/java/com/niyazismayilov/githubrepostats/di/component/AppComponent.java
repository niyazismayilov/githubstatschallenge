package com.niyazismayilov.githubrepostats.di.component;


import android.app.Application;

import com.niyazismayilov.githubrepostats.ApplicationClass;
import com.niyazismayilov.githubrepostats.data.IDataManager;
import com.niyazismayilov.githubrepostats.di.module.ActivityModule;
import com.niyazismayilov.githubrepostats.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent {

    void inject(ApplicationClass app);

    IDataManager getDataManager();


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
