package com.niyazismayilov.githubrepostats.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.niyazismayilov.githubrepostats.BR;
import com.niyazismayilov.githubrepostats.R;
import com.niyazismayilov.githubrepostats.databinding.ActivityMainBinding;
import com.niyazismayilov.githubrepostats.di.component.ActivityComponent;
import com.niyazismayilov.githubrepostats.ui.base.BaseActivity;

import dagger.Provides;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> {


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}