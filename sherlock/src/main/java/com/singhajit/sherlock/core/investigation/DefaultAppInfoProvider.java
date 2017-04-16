package com.singhajit.sherlock.core.investigation;

import android.support.compat.BuildConfig;

public class DefaultAppInfoProvider implements AppInfoProvider {
  @Override
  public AppInfo getAppInfo() {
    return new AppInfo.Builder()
        .withVersionName(BuildConfig.VERSION_NAME)
        .withVersionCode(BuildConfig.VERSION_CODE)
        .build();
  }
}
