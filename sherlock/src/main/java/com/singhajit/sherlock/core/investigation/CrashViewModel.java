package com.singhajit.sherlock.core.investigation;

import java.text.SimpleDateFormat;

public class CrashViewModel {
  private Crash crash;

  public CrashViewModel() {
  }

  public CrashViewModel(Crash crash) {
    this.crash = crash;
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

  public int getIdentifier() {
    return crash.getId();
  }

  public String getDate() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a EEE, MMM d, yyyy");
    return simpleDateFormat.format(crash.getDate());
  }

  public void populate(Crash crash) {
    this.crash = crash;
  }
}
