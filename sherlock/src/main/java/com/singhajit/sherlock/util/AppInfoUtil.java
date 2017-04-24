package com.singhajit.sherlock.util;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppInfoUtil {
  public static String getAppVersion(Context context) {
    try {
      return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    } catch (PackageManager.NameNotFoundException e) {
      return "Not Found";
    }
  }
}
