package com.singhajit.sherlock.core.investigation;

import com.singhajit.sherlock.crashes.viewmodel.AppInfoViewModel;

import java.text.SimpleDateFormat;

public class CrashViewModel {
  private Crash crash;
  private AppInfoViewModel appInfoViewModel;

  public CrashViewModel() {
  }

  public CrashViewModel(Crash crash) {
    populate(crash);
  }

  public String getPlace() {
    String[] placeTrail = crash.getPlace().split("\\.");
    return placeTrail[placeTrail.length - 1];
  }

  public String getExactLocationOfCrash() {
    return crash.getPlace();
  }

  public String getReasonOfCrash() {
    return crash.getReason();
  }

  public String getStackTrace() {
    return crash.getStackTrace();
  }

  public String getCrashInfo() {
    StringBuilder crashInfo = new StringBuilder();
    crashInfo.append("Device Info:\n");

    crashInfo.append("Name: ");
    crashInfo.append(getDeviceName() + "\n");

    crashInfo.append("Brand: ");
    crashInfo.append(getDeviceBrand() + "\n");

    crashInfo.append("Android API: ");
    crashInfo.append(getDeviceAndroidApiVersion() + "\n\n");

    crashInfo.append("App Info:\n");
    crashInfo.append(getAppInfoViewModel().getDetails());
    crashInfo.append("\n");

    crashInfo.append("StackTrace:\n");
    crashInfo.append(getStackTrace() + "\n");

    return crashInfo.toString();
  }

  public String getDeviceManufacturer() {
    return crash.getDeviceInfo().getManufacturer();
  }

  public String getDeviceName() {
    return crash.getDeviceInfo().getName();
  }

  public String getDeviceAndroidApiVersion() {
    return crash.getDeviceInfo().getSdk();
  }

  public String getDeviceBrand() {
    return crash.getDeviceInfo().getBrand();
  }

  public AppInfoViewModel getAppInfoViewModel() {
    return appInfoViewModel;
  }

  public int getIdentifier() {
    return crash.getId();
  }

  public String getDate() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a EEE, MMM d, yyyy");
    return simpleDateFormat.format(crash.getDate());
  }

  public void populate(Crash crash) {
    this.crash = crash;
    this.appInfoViewModel = new AppInfoViewModel(crash.getAppInfo());
  }
}
