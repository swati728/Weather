package com.example.weatherapp.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WeatherDao_Impl implements WeatherDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WeatherEntity> __insertionAdapterOfWeatherEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public WeatherDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeatherEntity = new EntityInsertionAdapter<WeatherEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `weather_cache` (`cityKey`,`cityName`,`temperatureC`,`feelsLikeC`,`condition`,`conditionIconUrl`,`humidity`,`windKph`,`lastUpdatedEpochMillis`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WeatherEntity entity) {
        if (entity.getCityKey() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getCityKey());
        }
        if (entity.getCityName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCityName());
        }
        statement.bindDouble(3, entity.getTemperatureC());
        statement.bindDouble(4, entity.getFeelsLikeC());
        if (entity.getCondition() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCondition());
        }
        if (entity.getConditionIconUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getConditionIconUrl());
        }
        statement.bindLong(7, entity.getHumidity());
        statement.bindDouble(8, entity.getWindKph());
        statement.bindLong(9, entity.getLastUpdatedEpochMillis());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM weather_cache WHERE cityKey = ?";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final WeatherEntity entity, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWeatherEntity.insert(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final String cityKey, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
        int _argIndex = 1;
        if (cityKey == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, cityKey);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDelete.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<WeatherEntity> observeByCity(final String cityKey) {
    final String _sql = "SELECT * FROM weather_cache WHERE cityKey = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (cityKey == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, cityKey);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"weather_cache"}, new Callable<WeatherEntity>() {
      @Override
      @Nullable
      public WeatherEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCityKey = CursorUtil.getColumnIndexOrThrow(_cursor, "cityKey");
          final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(_cursor, "cityName");
          final int _cursorIndexOfTemperatureC = CursorUtil.getColumnIndexOrThrow(_cursor, "temperatureC");
          final int _cursorIndexOfFeelsLikeC = CursorUtil.getColumnIndexOrThrow(_cursor, "feelsLikeC");
          final int _cursorIndexOfCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "condition");
          final int _cursorIndexOfConditionIconUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionIconUrl");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfWindKph = CursorUtil.getColumnIndexOrThrow(_cursor, "windKph");
          final int _cursorIndexOfLastUpdatedEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdatedEpochMillis");
          final WeatherEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpCityKey;
            if (_cursor.isNull(_cursorIndexOfCityKey)) {
              _tmpCityKey = null;
            } else {
              _tmpCityKey = _cursor.getString(_cursorIndexOfCityKey);
            }
            final String _tmpCityName;
            if (_cursor.isNull(_cursorIndexOfCityName)) {
              _tmpCityName = null;
            } else {
              _tmpCityName = _cursor.getString(_cursorIndexOfCityName);
            }
            final double _tmpTemperatureC;
            _tmpTemperatureC = _cursor.getDouble(_cursorIndexOfTemperatureC);
            final double _tmpFeelsLikeC;
            _tmpFeelsLikeC = _cursor.getDouble(_cursorIndexOfFeelsLikeC);
            final String _tmpCondition;
            if (_cursor.isNull(_cursorIndexOfCondition)) {
              _tmpCondition = null;
            } else {
              _tmpCondition = _cursor.getString(_cursorIndexOfCondition);
            }
            final String _tmpConditionIconUrl;
            if (_cursor.isNull(_cursorIndexOfConditionIconUrl)) {
              _tmpConditionIconUrl = null;
            } else {
              _tmpConditionIconUrl = _cursor.getString(_cursorIndexOfConditionIconUrl);
            }
            final int _tmpHumidity;
            _tmpHumidity = _cursor.getInt(_cursorIndexOfHumidity);
            final double _tmpWindKph;
            _tmpWindKph = _cursor.getDouble(_cursorIndexOfWindKph);
            final long _tmpLastUpdatedEpochMillis;
            _tmpLastUpdatedEpochMillis = _cursor.getLong(_cursorIndexOfLastUpdatedEpochMillis);
            _result = new WeatherEntity(_tmpCityKey,_tmpCityName,_tmpTemperatureC,_tmpFeelsLikeC,_tmpCondition,_tmpConditionIconUrl,_tmpHumidity,_tmpWindKph,_tmpLastUpdatedEpochMillis);
          } else {
            _result = null;
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

  @Override
  public Object getByCity(final String cityKey,
      final Continuation<? super WeatherEntity> $completion) {
    final String _sql = "SELECT * FROM weather_cache WHERE cityKey = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (cityKey == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, cityKey);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WeatherEntity>() {
      @Override
      @Nullable
      public WeatherEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCityKey = CursorUtil.getColumnIndexOrThrow(_cursor, "cityKey");
          final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(_cursor, "cityName");
          final int _cursorIndexOfTemperatureC = CursorUtil.getColumnIndexOrThrow(_cursor, "temperatureC");
          final int _cursorIndexOfFeelsLikeC = CursorUtil.getColumnIndexOrThrow(_cursor, "feelsLikeC");
          final int _cursorIndexOfCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "condition");
          final int _cursorIndexOfConditionIconUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionIconUrl");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfWindKph = CursorUtil.getColumnIndexOrThrow(_cursor, "windKph");
          final int _cursorIndexOfLastUpdatedEpochMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdatedEpochMillis");
          final WeatherEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpCityKey;
            if (_cursor.isNull(_cursorIndexOfCityKey)) {
              _tmpCityKey = null;
            } else {
              _tmpCityKey = _cursor.getString(_cursorIndexOfCityKey);
            }
            final String _tmpCityName;
            if (_cursor.isNull(_cursorIndexOfCityName)) {
              _tmpCityName = null;
            } else {
              _tmpCityName = _cursor.getString(_cursorIndexOfCityName);
            }
            final double _tmpTemperatureC;
            _tmpTemperatureC = _cursor.getDouble(_cursorIndexOfTemperatureC);
            final double _tmpFeelsLikeC;
            _tmpFeelsLikeC = _cursor.getDouble(_cursorIndexOfFeelsLikeC);
            final String _tmpCondition;
            if (_cursor.isNull(_cursorIndexOfCondition)) {
              _tmpCondition = null;
            } else {
              _tmpCondition = _cursor.getString(_cursorIndexOfCondition);
            }
            final String _tmpConditionIconUrl;
            if (_cursor.isNull(_cursorIndexOfConditionIconUrl)) {
              _tmpConditionIconUrl = null;
            } else {
              _tmpConditionIconUrl = _cursor.getString(_cursorIndexOfConditionIconUrl);
            }
            final int _tmpHumidity;
            _tmpHumidity = _cursor.getInt(_cursorIndexOfHumidity);
            final double _tmpWindKph;
            _tmpWindKph = _cursor.getDouble(_cursorIndexOfWindKph);
            final long _tmpLastUpdatedEpochMillis;
            _tmpLastUpdatedEpochMillis = _cursor.getLong(_cursorIndexOfLastUpdatedEpochMillis);
            _result = new WeatherEntity(_tmpCityKey,_tmpCityName,_tmpTemperatureC,_tmpFeelsLikeC,_tmpCondition,_tmpConditionIconUrl,_tmpHumidity,_tmpWindKph,_tmpLastUpdatedEpochMillis);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
