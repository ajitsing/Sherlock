package com.singhajit.sherlock.crashes.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Crime extends RealmObject {
  @PrimaryKey
  private int id;
  private String facts;
  private Date date;

  public Crime() { }

  public Crime(String facts) {
    this.facts = facts;
    this.date = new Date();
  }

  public void setId(int id) {
    this.id = id;
  }

  public Class<Crime> getType() {
    return Crime.class;
  }

  public String getFacts() {
    return facts;
  }

  public int getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }
}
