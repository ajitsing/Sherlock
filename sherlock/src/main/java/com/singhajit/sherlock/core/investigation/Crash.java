package com.singhajit.sherlock.core.investigation;

import android.os.Parcel;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Crash extends RealmObject {
  @PrimaryKey
  private int id;
  private String place;
  private String stackTrace;
  private Date date;

  public Crash() {
  }

  public Crash(String place, String stackTrace) {
    this.place = place;
    this.stackTrace = stackTrace;
    this.date = new Date();
  }

  protected Crash(Parcel in) {
    id = in.readInt();
    place = in.readString();
    stackTrace = in.readString();
  }

  public void setId(int id) {
    this.id = id;
  }

  public Class<Crash> getType() {
    return Crash.class;
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
