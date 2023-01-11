package com.niyazismayilov.githubrepostats.di.component;

import com.niyazismayilov.githubrepostats.di.module.ActivityModule;
import com.niyazismayilov.githubrepostats.di.module.ActivityScope;
import com.niyazismayilov.githubrepostats.di.module.FragmentScope;
import com.niyazismayilov.githubrepostats.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

   void inject(MainActivity mainActivity);

}
