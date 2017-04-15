package com.singhajit.sherlock.crashes;

import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.core.repo.CrashReports;

public class CrashPresenter {
  private final CrashReports crashReports;
  private final CrashActions actions;

  public CrashPresenter(CrashReports crashReports, CrashActions actions) {
    this.crashReports = crashReports;
    this.actions = actions;
  }

  public void render(int crashId, CrashViewModel crashViewModel) {
    Crash crash = crashReports.get(crashId);
    crashViewModel.populate(crash);
  }

  public void shareCrashDetails(CrashViewModel viewModel) {
    actions.openSendApplicationChooser(viewModel.getStackTrace());
  }
}
