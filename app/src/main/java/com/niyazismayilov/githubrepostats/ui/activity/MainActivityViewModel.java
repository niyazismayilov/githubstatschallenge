package com.niyazismayilov.githubrepostats.ui.activity;

import com.niyazismayilov.githubrepostats.data.IDataManager;
import com.niyazismayilov.githubrepostats.ui.base.BaseViewModel;

import javax.inject.Inject;



public class MainActivityViewModel extends BaseViewModel {
    IDataManager iDataManager;

    @Inject
    public MainActivityViewModel(IDataManager iDataManager) {
        this.iDataManager = iDataManager;
    }
}