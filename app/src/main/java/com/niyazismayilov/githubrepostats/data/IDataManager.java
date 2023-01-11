package com.niyazismayilov.githubrepostats.data;

import com.niyazismayilov.githubrepostats.data.api.IApi;
import com.niyazismayilov.githubrepostats.data.local.CachedRepository;

public interface IDataManager {
    IApi getApi();

    CachedRepository getCachedRepo();

}
