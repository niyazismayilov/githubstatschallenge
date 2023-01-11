package com.niyazismayilov.githubrepostats.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = CachedModel.class, version = 1, exportSchema = false)
public abstract class CachedDatabase extends RoomDatabase {
public abstract CachedDao getCacheDao();
}