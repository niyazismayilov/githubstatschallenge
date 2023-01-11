package com.niyazismayilov.githubrepostats.data.local;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CachedDao_Impl implements CachedDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CachedModel> __insertionAdapterOfCachedModel;

  private final EntityDeletionOrUpdateAdapter<CachedModel> __deletionAdapterOfCachedModel;

  public CachedDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCachedModel = new EntityInsertionAdapter<CachedModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `favorites` (`id`,`description`,`name`,`stargazers_count`,`avatar_url`,`language`,`forks`,`created_at`,`html_url`,`login`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CachedModel value) {
        stmt.bindLong(1, value.getId());
        if (value.getDescription() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescription());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getStargazers_count() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getStargazers_count());
        }
        if (value.getAvatar_url() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAvatar_url());
        }
        if (value.getLanguage() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getLanguage());
        }
        if (value.getForks() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getForks());
        }
        if (value.getCreated_at() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getCreated_at());
        }
        if (value.getHtml_url() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getHtml_url());
        }
        if (value.getLogin() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLogin());
        }
      }
    };
    this.__deletionAdapterOfCachedModel = new EntityDeletionOrUpdateAdapter<CachedModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `favorites` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CachedModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public Completable insert(final CachedModel cachedModel) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCachedModel.insert(cachedModel);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final CachedModel cachedModel) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCachedModel.handle(cachedModel);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Flowable<List<CachedModel>> getAllData() {
    final String _sql = "SELECT * FROM favorites";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"favorites"}, new Callable<List<CachedModel>>() {
      @Override
      public List<CachedModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfStargazersCount = CursorUtil.getColumnIndexOrThrow(_cursor, "stargazers_count");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar_url");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfForks = CursorUtil.getColumnIndexOrThrow(_cursor, "forks");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfHtmlUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "html_url");
          final int _cursorIndexOfLogin = CursorUtil.getColumnIndexOrThrow(_cursor, "login");
          final List<CachedModel> _result = new ArrayList<CachedModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CachedModel _item;
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpStargazers_count;
            if (_cursor.isNull(_cursorIndexOfStargazersCount)) {
              _tmpStargazers_count = null;
            } else {
              _tmpStargazers_count = _cursor.getString(_cursorIndexOfStargazersCount);
            }
            final String _tmpAvatar_url;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatar_url = null;
            } else {
              _tmpAvatar_url = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final String _tmpForks;
            if (_cursor.isNull(_cursorIndexOfForks)) {
              _tmpForks = null;
            } else {
              _tmpForks = _cursor.getString(_cursorIndexOfForks);
            }
            final String _tmpCreated_at;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreated_at = null;
            } else {
              _tmpCreated_at = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final String _tmpHtml_url;
            if (_cursor.isNull(_cursorIndexOfHtmlUrl)) {
              _tmpHtml_url = null;
            } else {
              _tmpHtml_url = _cursor.getString(_cursorIndexOfHtmlUrl);
            }
            final String _tmpLogin;
            if (_cursor.isNull(_cursorIndexOfLogin)) {
              _tmpLogin = null;
            } else {
              _tmpLogin = _cursor.getString(_cursorIndexOfLogin);
            }
            _item = new CachedModel(_tmpDescription,_tmpName,_tmpStargazers_count,_tmpAvatar_url,_tmpLanguage,_tmpForks,_tmpCreated_at,_tmpHtml_url,_tmpLogin);
            _item.id = _cursor.getInt(_cursorIndexOfId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
