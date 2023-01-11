package com.niyazismayilov.githubrepostats.data;

import com.niyazismayilov.githubrepostats.data.api.IApi;
import com.niyazismayilov.githubrepostats.data.local.CachedRepository;

import javax.inject.Inject;

public class AppDataManager implements IDataManager {
    private IApi iApi;
    private CachedRepository iCachedRepository;

    @Inject
    public AppDataManager(IApi iApi, CachedRepository iCachedRepository) {
        this.iApi = iApi;
        this.iCachedRepository = iCachedRepository;
    }

    @Override
    public IApi getApi() {
        return iApi;
    }

    @Override
    public CachedRepository getCachedRepo() {
        return iCachedRepository;
    }
}
