package com.singhajit.sherlock.core.repo;

import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.realm.RealmSequenceGenerator;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

public class CrashReports {
  private final Realm realm;

  public CrashReports(Realm realm) {
    this.realm = realm;
  }

  public int add(Crash crash) {
    int id = RealmSequenceGenerator.generate(realm, crash.getType());
    crash.setId(id);
    realm.beginTransaction();
    realm.copyToRealm(crash);
    realm.commitTransaction();
    return id;
  }

  public List<Crash> getAll() {
    return realm.where(Crash.class).findAllSorted("date", Sort.DESCENDING);
  }

  public Crash get(int id) {
    return realm.where(Crash.class).equalTo("id", id).findFirst();
  }
}
