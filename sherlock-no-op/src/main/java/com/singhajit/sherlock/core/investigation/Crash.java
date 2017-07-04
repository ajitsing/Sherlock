package com.singhajit.sherlock.core.investigation;

import java.util.Date;

public class Crash {
  public static final String DATE_FORMAT = "EEE MMM dd kk:mm:ss z yyyy";

  public Crash(String place, String reason, String stackTrace) {
  }

  public Crash(int id, String placeOfCrash, String reasonOfCrash, String stacktrace, String date) {
  }

  public DeviceInfo getDeviceInfo() {
    return new DeviceInfo.Builder().build();
  }

  public AppInfo getAppInfo() {
    return new AppInfo();
  }

  public void setId(int id) {
  }

  public Class<Crash> getType() {
    return Crash.class;
  }

  public String getReason() {
    return "";
  }

  public String getStackTrace() {
    return "";
  }

  public String getPlace() {
    return "";
  }

  public int getId() {
    return 0;
  }

  public Date getDate() {
    return new Date();
  }
}
