package com.niyazismayilov.githubrepostats.data.local;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface CachedRepository {

    Completable insert(CachedModel cachedModel);

    Completable delete(CachedModel cachedModel);

    Flowable<List<CachedModel>> getAllData();


}