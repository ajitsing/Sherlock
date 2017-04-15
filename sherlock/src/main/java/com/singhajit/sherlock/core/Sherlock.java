package com.singhajit.sherlock.core;

import android.content.Context;

import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.investigation.CrashAnalyzer;
import com.singhajit.sherlock.core.investigation.CrashReporter;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CrashReports;

public class Sherlock {
  private Sherlock() {
  }

  public static void initialize(final Context context) {
    final Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();

    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread thread, Throwable throwable) {
        analyzeAndReportCrash(thread, throwable, context);
        handler.uncaughtException(thread, throwable);
      }
    });
  }

  private static void analyzeAndReportCrash(Thread thread, Throwable throwable, Context context) {
    CrashAnalyzer crashAnalyzer = new CrashAnalyzer(thread, throwable);
    Crash crash = crashAnalyzer.getAnalysis();

    CrashReports crashReports = new CrashReports(SherlockRealm.create(context));
    crashReports.add(crash);

    CrashReporter crashReporter = new CrashReporter(context);
    crashReporter.report(new CrashViewModel(crash));
  }
}
