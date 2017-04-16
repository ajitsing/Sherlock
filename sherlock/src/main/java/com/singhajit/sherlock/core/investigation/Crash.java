package com.singhajit.sherlock.core.investigation;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Crash extends RealmObject {
  @PrimaryKey
  private int id;
  private String place;
  private String reason;
  private String stackTrace;
  private Date date;

  public Crash() {
  }

  public Crash(String place, String reason, String stackTrace) {
    this.place = place;
    this.stackTrace = stackTrace;
    this.reason = reason;
    this.date = new Date();
  }

  public void setId(int id) {
    this.id = id;
  }

  public Class<Crash> getType() {
    return Crash.class;
  }

  public String getReason() {
    return reason;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public String getPlace() {
    return place;
  }

  public int getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }
}
