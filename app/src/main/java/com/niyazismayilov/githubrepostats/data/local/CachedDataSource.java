package com.niyazismayilov.githubrepostats.data.local;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class CachedDataSource implements CachedRepository {
    CachedDao iCacheDao;

    @Inject
    public CachedDataSource(CachedDao iCacheDao) {
        this.iCacheDao = iCacheDao;
    }

    @Override
    public Completable insert(CachedModel cachedModel) {
        return iCacheDao.insert(cachedModel);
    }

    @Override
    public Completable delete(CachedModel cachedModel) {
        return iCacheDao.delete(cachedModel);
    }

    @Override
    public Flowable<List<CachedModel>> getAllData() {
        return iCacheDao.getAllData();
    }
}