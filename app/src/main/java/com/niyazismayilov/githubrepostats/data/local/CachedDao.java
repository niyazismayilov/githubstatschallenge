package com.niyazismayilov.githubrepostats.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.niyazismayilov.githubrepostats.utils.Constants;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public
interface CachedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(CachedModel cachedModel);

    @Delete
    Completable delete(CachedModel cachedModel);

    @Query("SELECT * FROM " + Constants.TABLE_FAVS)
    Flowable<List<CachedModel>> getAllData();

}
