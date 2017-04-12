package com.singhajit.sherlock.core.realm;

import io.realm.Realm;
import io.realm.RealmObject;

public class RealmSequenceGenerator {
  public static <T extends RealmObject> int generate(Realm realm, Class<T> model) {
    return realm.where(model).findAll().isEmpty() ? 1 : realm.where(model).max("id").intValue() + 1;
  }
}
