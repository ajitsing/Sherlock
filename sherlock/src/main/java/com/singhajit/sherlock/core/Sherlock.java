package com.singhajit.sherlock.core;

import android.content.Context;
import android.util.Log;

import com.singhajit.sherlock.core.database.CrashRecord;
import com.singhajit.sherlock.core.database.SherlockDatabaseHelper;
import com.singhajit.sherlock.core.investigation.AppInfoProvider;
import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.investigation.CrashAnalyzer;
import com.singhajit.sherlock.core.investigation.CrashReporter;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.core.investigation.DefaultAppInfoProvider;

import java.util.List;

public class Sherlock {
  private static final String TAG = Sherlock.class.getSimpleName();
  private static Sherlock instance;
  private final SherlockDatabaseHelper database;
  private final CrashReporter crashReporter;
  private AppInfoProvider appInfoProvider;

  private Sherlock(Context context) {
    database = new SherlockDatabaseHelper(context);
    crashReporter = new CrashReporter(context);
    appInfoProvider = new DefaultAppInfoProvider(context);
  }

  public static void init(final Context context) {
    Log.d(TAG, "Initializing Sherlock...");
    instance = new Sherlock(context);

    final Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();

    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread thread, Throwable throwable) {
        analyzeAndReportCrash(throwable);
        handler.uncaughtException(thread, throwable);
      }
    });
  }

  public static boolean isInitialized() {
    return instance != null;
  }

  public static Sherlock getInstance() {
    if (!isInitialized()) {
      throw new SherlockNotInitializedException();
    }
    Log.d(TAG, "Returning existing instance...");
    return instance;
  }

  public List<Crash> getAllCrashes() {
    return getInstance().database.getCrashes();
  }

  private static void analyzeAndReportCrash(Throwable throwable) {
    Log.d(TAG, "Analyzing Crash...");
    CrashAnalyzer crashAnalyzer = new CrashAnalyzer(throwable);
    Crash crash = crashAnalyzer.getAnalysis();
    int crashId = instance.database.insertCrash(CrashRecord.createFrom(crash));
    crash.setId(crashId);
    instance.crashReporter.report(new CrashViewModel(crash));
    Log.d(TAG, "Crash analysis completed!");
  }

  public static void setAppInfoProvider(AppInfoProvider appInfoProvider) {
    getInstance().appInfoProvider = appInfoProvider;
  }

  public AppInfoProvider getAppInfoProvider() {
    return getInstance().appInfoProvider;
  }
}
