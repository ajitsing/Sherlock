package com.singhajit.sherlock.core.investigation;

import android.os.Build;

public class DeviceInfoProvider {
  public static DeviceInfo getDeviceInfo() {
    return new DeviceInfo.Builder()
        .withManufacturer(Build.MANUFACTURER)
        .withModel(Build.MODEL)
        .withBrand(Build.BRAND)
        .withSDK(String.valueOf(Build.VERSION.SDK_INT))
        .build();
  }
}
