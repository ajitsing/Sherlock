package com.singhajit.sherlock.crashes;

import com.singhajit.sherlock.core.investigation.Crime;
import com.singhajit.sherlock.core.investigation.CrimeViewModel;
import com.singhajit.sherlock.core.repo.CriminalRecords;

public class CrashPresenter {
  private final CriminalRecords criminalRecords;
  private final CrashActions actions;

  public CrashPresenter(CriminalRecords criminalRecords, CrashActions actions) {
    this.criminalRecords = criminalRecords;
    this.actions = actions;
  }

  public void render(int crashId, CrimeViewModel crimeViewModel) {
    Crime crime = criminalRecords.get(crashId);
    crimeViewModel.populate(crime);
  }

  public void shareCrashDetails(CrimeViewModel viewModel) {
    actions.openSendApplicationChooser(viewModel.getFacts());
  }
}
