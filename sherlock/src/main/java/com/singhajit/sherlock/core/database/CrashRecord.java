package com.singhajit.sherlock.core.database;

import com.singhajit.sherlock.core.investigation.Crash;

import java.text.SimpleDateFormat;

public class CrashRecord {
  private final int id;
  private String place;
  private String reason;
  private String date;
  private String stackTrace;

  public CrashRecord(int id, String place, String reason, String date, String stackTrace) {
    this.id = id;
    this.place = place;
    this.reason = reason;
    this.date = date;
    this.stackTrace = stackTrace;
  }

  public int getId() {
    return id;
  }

  public String getPlace() {
    return place;
  }

  public String getReason() {
    return reason;
  }

  public String getDate() {
    return date;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public static CrashRecord createFrom(Crash crash) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(Crash.DATE_FORMAT);
    return new CrashRecord(crash.getId(), crash.getPlace(), crash.getReason(), dateFormat.format(crash.getDate()), crash.getStackTrace());
  }
}
