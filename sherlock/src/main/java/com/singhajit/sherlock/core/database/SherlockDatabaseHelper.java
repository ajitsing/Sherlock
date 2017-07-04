package com.singhajit.sherlock.core.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.singhajit.sherlock.core.investigation.Crash;

import java.util.ArrayList;
import java.util.List;

public class SherlockDatabaseHelper extends SQLiteOpenHelper {
  private static final int VERSION = 2;
  private static final String DB_NAME = "Sherlock";

  public SherlockDatabaseHelper(Context context) {
    super(context, DB_NAME, null, VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(CrashTable.CREATE_QUERY);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL(CrashTable.DROP_QUERY);
    sqLiteDatabase.execSQL(CrashTable.CREATE_QUERY);
  }

  public int insertCrash(CrashRecord crashRecord) {
    ContentValues values = new ContentValues();
    values.put(CrashTable.PLACE, crashRecord.getPlace());
    values.put(CrashTable.REASON, crashRecord.getReason());
    values.put(CrashTable.STACKTRACE, crashRecord.getStackTrace());
    values.put(CrashTable.DATE, crashRecord.getDate());

    SQLiteDatabase database = getWritableDatabase();
    long id = database.insert(CrashTable.TABLE_NAME, null, values);
    database.close();

    return Long.valueOf(id).intValue();
  }

  public List<Crash> getCrashes() {
    SQLiteDatabase readableDatabase = getReadableDatabase();
    ArrayList<Crash> crashes = new ArrayList<>();
    Cursor cursor = readableDatabase.rawQuery(CrashTable.SELECT_ALL, null);
    if (isCursorPopulated(cursor)) {
      do {
        crashes.add(toCrash(cursor));
      } while (cursor.moveToNext());
    }

    cursor.close();
    readableDatabase.close();

    return crashes;
  }

  public Crash getCrashById(int id) {
    SQLiteDatabase readableDatabase = getReadableDatabase();
    Cursor cursor = readableDatabase.rawQuery(CrashTable.selectById(id), null);
    Crash crash = null;

    if (isCursorPopulated(cursor)) {
      crash = toCrash(cursor);
      cursor.close();
      readableDatabase.close();
    }

    return crash;
  }

  @NonNull
  private Crash toCrash(Cursor cursor) {
    int id = cursor.getInt(cursor.getColumnIndex(CrashTable._ID));
    String placeOfCrash = cursor.getString(cursor.getColumnIndex(CrashTable.PLACE));
    String reasonOfCrash = cursor.getString(cursor.getColumnIndex(CrashTable.REASON));
    String stacktrace = cursor.getString(cursor.getColumnIndex(CrashTable.STACKTRACE));
    String date = cursor.getString(cursor.getColumnIndex(CrashTable.DATE));
    return new Crash(id, placeOfCrash, reasonOfCrash, stacktrace, date);
  }

  private boolean isCursorPopulated(Cursor cursor) {
    return cursor != null && cursor.moveToFirst();
  }
}
