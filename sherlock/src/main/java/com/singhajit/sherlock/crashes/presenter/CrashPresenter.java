package com.singhajit.sherlock.crashes.presenter;

import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.core.repo.CrashReports;
import com.singhajit.sherlock.crashes.action.CrashActions;
import com.singhajit.sherlock.crashes.viewmodel.AppInfoViewModel;

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
    actions.renderAppInfo(crashViewModel.getAppInfoViewModel());
  }

  public void shareCrashDetails(CrashViewModel viewModel) {
    actions.openSendApplicationChooser(viewModel.getCrashInfo());
  }
}
