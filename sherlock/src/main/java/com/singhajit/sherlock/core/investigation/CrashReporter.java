package com.singhajit.sherlock.core.investigation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.crashes.activity.CrashActivity;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class CrashReporter {
  private final Context context;
  private String CHANNEL_ID = "com.singhajit.com.221B";

  public CrashReporter(Context context) {
    this.context = context;
  }

  public void report(CrashViewModel crashViewModel) {
    Notification notification = notification(crashViewModel);

    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(
          CHANNEL_ID,
          "Sherlock",
          IMPORTANCE_DEFAULT
      );
      notificationManager.createNotificationChannel(channel);
    }

    notificationManager.notify(crashViewModel.getIdentifier(), notification);
  }

  private Notification notification(CrashViewModel crashViewModel) {
    Intent crashActivityIntent = new Intent(context, CrashActivity.class);
    crashActivityIntent.putExtra(CrashActivity.CRASH_ID, crashViewModel.getIdentifier());

    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
    stackBuilder.addParentStack(CrashActivity.class);
    stackBuilder.addNextIntent(crashActivityIntent);

    PendingIntent pendingIntent = stackBuilder.getPendingIntent(221, FLAG_UPDATE_CURRENT);

    Notification.Builder notificationBuilder = new Notification.Builder(context)
        .setContentTitle(crashViewModel.getPlace())
        .setContentText(crashViewModel.getDate())
        .setSmallIcon(R.mipmap.ic_stat_sherlock_logo)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      notificationBuilder.setColor(ContextCompat.getColor(context, R.color.sherlock_colorAccent));
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      notificationBuilder.setChannelId(CHANNEL_ID);
    }

    return notificationBuilder.build();
  }
}
