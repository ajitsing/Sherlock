package com.singhajit.sherlock.core.database;

import android.provider.BaseColumns;

public class CrashTable implements BaseColumns {
  public static final String TABLE_NAME = "crashes";
  public static final String PLACE = "place";
  public static final String REASON = "reason";
  public static final String STACKTRACE = "stacktrace";
  public static final String DATE = "date";

  public static final String CREATE_QUERY = "create table " + TABLE_NAME + " (" +
      _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
      PLACE + " TEXT, " +
      REASON + " TEXT, " +
      STACKTRACE + " TEXT, " +
      DATE + " TEXT)";

  public static final String DROP_QUERY = "drop table " + TABLE_NAME;
  public static final String TRUNCATE_QUERY = "delete from " + TABLE_NAME;
  public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + _ID + " DESC";

  public static String selectById(int id) {
    return "SELECT * FROM " + TABLE_NAME + " WHERE " + _ID + " = " + id;
  }
}
