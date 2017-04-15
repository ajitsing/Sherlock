package com.singhajit.sherlock.core.investigation;

import java.text.SimpleDateFormat;

public class CrimeViewModel {
  private Crime crime;

  public CrimeViewModel() {
  }

  public CrimeViewModel(Crime crime) {
    this.crime = crime;
  }

  public String getPlaceOfCrime() {
    String[] placeTrail = crime.getPlaceOfCrime().split("\\.");
    return placeTrail[placeTrail.length - 1];
  }

  public String getExactLocationOfCrash() {
    return crime.getPlaceOfCrime();
  }

  public String getFacts() {
    return crime.getFacts();
  }

  public int getIdentifier() {
    return crime.getId();
  }

  public String getDate() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a EEE, MMM d, yyyy");
    return simpleDateFormat.format(crime.getDate());
  }

  public void populate(Crime crime) {
    this.crime = crime;
  }
}
