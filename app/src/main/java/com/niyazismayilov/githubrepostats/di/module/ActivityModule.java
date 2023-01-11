package com.niyazismayilov.githubrepostats.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.niyazismayilov.githubrepostats.data.IDataManager;
import com.niyazismayilov.githubrepostats.ui.activity.MainActivity;
import com.niyazismayilov.githubrepostats.ui.activity.MainActivityViewModel;
import com.niyazismayilov.githubrepostats.ui.base.BaseActivity;
import com.niyazismayilov.githubrepostats.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public class ActivityModule {
    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Provides
    MainActivityViewModel provideFeedViewModel(IDataManager dataManager) {
        Supplier<MainActivityViewModel> supplier = () -> new MainActivityViewModel(dataManager);
        ViewModelProviderFactory<MainActivityViewModel> factory = new ViewModelProviderFactory<>(MainActivityViewModel.class, supplier);
        return new ViewModelProvider(activity, (ViewModelProvider.Factory) factory).get(MainActivityViewModel.class);
    }
}