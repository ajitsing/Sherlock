package com.singhajit.sherlock.core.repo;

import com.singhajit.sherlock.core.realm.RealmSequenceGenerator;
import com.singhajit.sherlock.crashes.model.Crime;

import io.realm.Realm;

public class CrimeRegister {
  private final Realm realm;

  public CrimeRegister(Realm realm) {
    this.realm = realm;
  }

  public int put(Crime crime) {
    int id = RealmSequenceGenerator.generate(realm, crime.getType());
    crime.setId(id);
    realm.beginTransaction();
    realm.copyToRealm(crime);
    realm.commitTransaction();
    return id;
  }
}
