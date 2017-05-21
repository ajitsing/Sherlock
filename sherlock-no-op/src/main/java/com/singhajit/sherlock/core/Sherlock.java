package com.singhajit.sherlock.core;

import android.content.Context;

import com.singhajit.sherlock.core.investigation.AppInfo;
import com.singhajit.sherlock.core.investigation.AppInfoProvider;
import com.singhajit.sherlock.core.investigation.Crash;

import java.util.ArrayList;
import java.util.List;

public class Sherlock {
  public static void init(final Context context) {
  }

  public static boolean isInitialized() {
    return false;
  }

  public static Sherlock getInstance() {
    return new Sherlock();
  }

  public List<Crash> getAllCrashes() {
    return new ArrayList<>();
  }

  public static void setAppInfoProvider(AppInfoProvider appInfoProvider) {
  }

  public AppInfoProvider getAppInfoProvider() {
    return new AppInfoProvider() {
      @Override
      public AppInfo getAppInfo() {
        return new AppInfo();
      }
    };
  }
}
