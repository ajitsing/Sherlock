package com.singhajit.sherlock.core.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TestModel extends RealmObject {
  @PrimaryKey
  private int id;

  public void setId(int id) {
    this.id = id;
  }
}
