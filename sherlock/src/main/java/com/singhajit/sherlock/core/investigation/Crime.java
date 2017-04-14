package com.singhajit.sherlock.core.investigation;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Crime extends RealmObject {
  @PrimaryKey
  private int id;
  private String placeOfCrime;
  private String facts;
  private Date date;

  public Crime() {
  }

  public Crime(String placeOfCrime, String facts) {
    this.placeOfCrime = placeOfCrime;
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

  public String getPlaceOfCrime() {
    return placeOfCrime;
  }

  public int getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }
}
