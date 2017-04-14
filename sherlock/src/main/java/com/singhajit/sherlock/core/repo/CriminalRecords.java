package com.singhajit.sherlock.core.repo;

import com.singhajit.sherlock.core.realm.RealmSequenceGenerator;
import com.singhajit.sherlock.crashes.model.Crime;

import io.realm.Realm;

public class CriminalRecords {
  private final Realm realm;

  public CriminalRecords(Realm realm) {
    this.realm = realm;
  }

  public int add(Crime crime) {
    int id = RealmSequenceGenerator.generate(realm, crime.getType());
    crime.setId(id);
    realm.beginTransaction();
    realm.copyToRealm(crime);
    realm.commitTransaction();
    return id;
  }
}
