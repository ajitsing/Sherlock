package com.singhajit.sherlock.core.investigation;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.singhajit.sherlock.R;

public class CrimeReporter {
  private final Context context;

  public CrimeReporter(Context context) {
    this.context = context;
  }

  public void report(CrimeViewModel crimeViewModel) {
    Notification notification = new Notification.Builder(context)
        .setContentTitle(crimeViewModel.getPlaceOfCrime())
        .setContentText(crimeViewModel.getDate())
        .setSmallIcon(R.mipmap.ic_launcher)
        .build();

    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(crimeViewModel.getIdentifier(), notification);
  }
}
