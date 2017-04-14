package com.singhajit.sherlock.core.repo;

import com.singhajit.sherlock.core.realm.RealmSequenceGenerator;
import com.singhajit.sherlock.core.investigation.Crime;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

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

  public List<Crime> getAll() {
    return realm.where(Crime.class).findAllSorted("date", Sort.DESCENDING);
  }
}
