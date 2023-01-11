package com.niyazismayilov.githubrepostats.data;

import com.niyazismayilov.githubrepostats.data.api.IApi;

import javax.inject.Inject;

public class AppDataManager implements IDataManager {
    private IApi iApi;
    @Inject
    public AppDataManager(IApi iApi) {
        this.iApi=iApi;
    }

    @Override
    public IApi getApi() {
        return iApi;
    }
}
