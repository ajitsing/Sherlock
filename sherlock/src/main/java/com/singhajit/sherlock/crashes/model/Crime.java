package com.singhajit.sherlock.crashes.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Crime extends RealmObject {
  @PrimaryKey
  private int id;
  private String stackTrace;
  private Date date;

  public Crime() { }

  public Crime(String stackTrace) {
    this.stackTrace = stackTrace;
    this.date = new Date();
  }

  public void setId(int id) {
    this.id = id;
  }

  public Class<Crime> getType() {
    return Crime.class;
  }

  public String getDetails() {
    return stackTrace;
  }

  public int getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }
}
