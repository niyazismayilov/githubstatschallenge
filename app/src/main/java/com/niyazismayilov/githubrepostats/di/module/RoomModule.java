package com.niyazismayilov.githubrepostats.di.module;


import android.app.Application;

import androidx.room.Room;

import com.niyazismayilov.githubrepostats.data.local.CachedDao;
import com.niyazismayilov.githubrepostats.data.local.CachedDataSource;
import com.niyazismayilov.githubrepostats.data.local.CachedDatabase;
import com.niyazismayilov.githubrepostats.data.local.CachedRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public
class RoomModule {

    @Singleton
    @Provides
    CachedDatabase provideCacheDataBase(Application application) {
        return Room
                .databaseBuilder(application,CachedDatabase.class, "GithabStats_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    CachedDao provideCacheDao(CachedDatabase cacheDatabase) {
        return cacheDatabase.getCacheDao();
    }

    @Singleton
    @Provides
    CachedRepository provideCacheRepository(CachedDao iCacheDao) {
        return new CachedDataSource(iCacheDao);
    }

}
