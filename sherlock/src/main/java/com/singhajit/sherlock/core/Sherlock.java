package com.singhajit.sherlock.core;

import android.content.Context;

import com.singhajit.sherlock.core.investigation.CrimeSceneInvestigator;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CrimeRegister;

public class Sherlock {
  public static void initialize(final Context context) {
    final Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread thread, Throwable throwable) {
        collectCrimeDetails(thread, throwable, context);
        handler.uncaughtException(thread, throwable);
      }
    });
  }

  private static void collectCrimeDetails(Thread thread, Throwable throwable, Context context) {
    CrimeSceneInvestigator crimeSceneInvestigator = new CrimeSceneInvestigator(thread, throwable);
    CrimeRegister crimeRegister = new CrimeRegister(SherlockRealm.create(context));
    crimeRegister.put(crimeSceneInvestigator.getCrime());
  }
}
