package com.niyazismayilov.githubrepostats.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.niyazismayilov.githubrepostats.data.IDataManager;
import com.niyazismayilov.githubrepostats.ui.base.BaseFragment;
import com.niyazismayilov.githubrepostats.ui.fragment.favlist.FavoriteListViewModel;
import com.niyazismayilov.githubrepostats.ui.fragment.repolist.RepoListFragment;
import com.niyazismayilov.githubrepostats.ui.fragment.repolist.RepoListViewModel;
import com.niyazismayilov.githubrepostats.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;


@Module
public class FragmentBuilderModule {

    private BaseFragment<?, ?> fragment;

    public FragmentBuilderModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Provides
    RepoListViewModel provideAboutViewModel(IDataManager dataManager) {
        Supplier<RepoListViewModel> supplier = () -> new RepoListViewModel(dataManager);
        ViewModelProviderFactory factory = new ViewModelProviderFactory<RepoListViewModel>(RepoListViewModel.class, supplier);
        return new ViewModelProvider(fragment, (ViewModelProvider.Factory) factory).get(RepoListViewModel.class);
    }

    @Provides
    FavoriteListViewModel provideFavoriteViewModel(IDataManager dataManager) {
        Supplier<FavoriteListViewModel> supplier = () -> new FavoriteListViewModel(dataManager);
        ViewModelProviderFactory factory = new ViewModelProviderFactory<FavoriteListViewModel>(FavoriteListViewModel.class, supplier);
        return new ViewModelProvider(fragment, (ViewModelProvider.Factory) factory).get(FavoriteListViewModel.class);
    }

}
