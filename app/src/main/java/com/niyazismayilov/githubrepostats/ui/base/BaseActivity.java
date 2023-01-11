package com.niyazismayilov.githubrepostats.ui.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


import com.niyazismayilov.githubrepostats.ApplicationClass;
import com.niyazismayilov.githubrepostats.di.component.ActivityComponent;
import com.niyazismayilov.githubrepostats.di.component.DaggerActivityComponent;
import com.niyazismayilov.githubrepostats.di.module.ActivityModule;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding, V  extends BaseViewModel> extends
        AppCompatActivity implements BaseFragment.Callback {


    T viewDataBinding;

    public abstract int getBindingVariable();
    public abstract
    @LayoutRes
    int getLayoutId();

    @Inject
    V mViewModel;

    public T getViewDataBinding() {
        return viewDataBinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection(getBuildComponent());
        super.onCreate(savedInstanceState);
        performDataBinding();
    }
    public abstract void performDependencyInjection(ActivityComponent buildComponent);

    private ActivityComponent getBuildComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(((ApplicationClass)getApplication()).appComponent)
                .build();
    }
    public void performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewDataBinding.setVariable(getBindingVariable(), mViewModel);
        viewDataBinding.executePendingBindings();
    }


    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(@Nullable String tag) {

    }
}