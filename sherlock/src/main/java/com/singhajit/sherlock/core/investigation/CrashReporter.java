package com.singhajit.sherlock.core.investigation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.crashes.CrashActivity;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class CrashReporter {
  private final Context context;

  public CrashReporter(Context context) {
    this.context = context;
  }

  public void report(CrashViewModel crashViewModel) {
    Intent crashActivityIntent = new Intent(context, CrashActivity.class);
    crashActivityIntent.putExtra(CrashActivity.CRASH_ID, crashViewModel.getIdentifier());

    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
    stackBuilder.addParentStack(CrashActivity.class);
    stackBuilder.addNextIntent(crashActivityIntent);

    PendingIntent pendingIntent = stackBuilder.getPendingIntent(221, FLAG_UPDATE_CURRENT);

    Notification notification = new Notification.Builder(context)
        .setContentTitle(crashViewModel.getPlace())
        .setContentText(crashViewModel.getDate())
        .setSmallIcon(R.mipmap.ic_stat_sherlock_logo)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build();

    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(crashViewModel.getIdentifier(), notification);
  }
}
