package com.niyazismayilov.githubrepostats.di.component;

import com.niyazismayilov.githubrepostats.di.module.FragmentBuilderModule;
import com.niyazismayilov.githubrepostats.di.scope.FragmentScope;
import com.niyazismayilov.githubrepostats.ui.fragment.favlist.FavoriteListFragment;
import com.niyazismayilov.githubrepostats.ui.fragment.repolist.RepoListFragment;

import dagger.Component;


@FragmentScope
@Component(modules = FragmentBuilderModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    void inject(RepoListFragment fragment);

    void inject(FavoriteListFragment fragment);

}
