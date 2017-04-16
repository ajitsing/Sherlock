package com.singhajit.sherlock.core.investigation;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class DefaultAppInfoProvider implements AppInfoProvider {
  private final Context context;

  public DefaultAppInfoProvider(Context context) {
    this.context = context;
  }

  @Override
  public AppInfo getAppInfo() {
    AppInfo.Builder appInfoBuilder = new AppInfo.Builder();
    try {
      PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
      appInfoBuilder.withVersionName(packageInfo.versionName);
    } catch (PackageManager.NameNotFoundException e) {

    }

    return appInfoBuilder.build();
  }
}
