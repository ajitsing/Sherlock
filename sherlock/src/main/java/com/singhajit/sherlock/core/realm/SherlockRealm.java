package com.singhajit.sherlock.core.realm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SherlockRealm {
  public static Realm create(Context context) {
    Realm.init(context);
    RealmConfiguration configuration = new RealmConfiguration.Builder()
        .name("sherlock.realm")
        .modules(new SherlockRealmModule())
        .build();
    return Realm.getInstance(configuration);
  }
}
