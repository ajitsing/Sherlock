package com.singhajit.sherlock.crashes;

import com.singhajit.sherlock.core.investigation.Crime;
import com.singhajit.sherlock.core.investigation.CrimeViewModel;
import com.singhajit.sherlock.core.repo.CriminalRecords;

public class CrashPresenter {
  private final CriminalRecords criminalRecords;

  public CrashPresenter(CriminalRecords criminalRecords) {
    this.criminalRecords = criminalRecords;
  }

  public void render(int crashId, CrimeViewModel crimeViewModel) {
    Crime crime = criminalRecords.get(crashId);
    crimeViewModel.populate(crime);
  }
}
