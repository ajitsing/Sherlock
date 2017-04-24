package com.singhajit.sherlock.core.investigation;

import io.realm.RealmObject;

public class Pair extends RealmObject {
  private String key;
  private String val;

  public Pair() {
  }

  public Pair(String key, String val) {
    this.key = key;
    this.val = val;
  }

  public String getKey() {
    return key;
  }

  public String getVal() {
    return val;
  }
}
