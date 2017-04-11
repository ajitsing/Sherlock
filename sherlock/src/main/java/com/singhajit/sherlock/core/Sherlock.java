package com.singhajit.sherlock.core;

import android.content.Context;
import android.util.Log;

public class Sherlock {
  public static void initialize(Context context) {
    final Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread thread, Throwable throwable) {
        String stackTrace = StackTraceBuilder.buildStackTrace(thread, throwable);
        Log.d("Sherlock", stackTrace);
        handler.uncaughtException(thread, throwable);
      }
    });
  }
}
