package com.singhajit.sherlock.core;

import android.content.Context;

import com.singhajit.sherlock.core.investigation.Crime;
import com.singhajit.sherlock.core.investigation.CrimeReporter;
import com.singhajit.sherlock.core.investigation.CrimeSceneInvestigator;
import com.singhajit.sherlock.core.investigation.CrimeViewModel;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CriminalRecords;

public class Sherlock {
  public static void startInvestigation(final Context context) {
    final Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread thread, Throwable throwable) {
        investigateAndCollectFacts(thread, throwable, context);
        handler.uncaughtException(thread, throwable);
      }
    });
  }

  private static void investigateAndCollectFacts(Thread thread, Throwable throwable, Context context) {
    CrimeSceneInvestigator crimeSceneInvestigator = new CrimeSceneInvestigator(thread, throwable);
    Crime crime = crimeSceneInvestigator.investigate();

    CriminalRecords criminalRecords = new CriminalRecords(SherlockRealm.create(context));
    criminalRecords.add(crime);

    CrimeReporter crimeReporter = new CrimeReporter(context);
    crimeReporter.report(new CrimeViewModel(crime));
  }
}
