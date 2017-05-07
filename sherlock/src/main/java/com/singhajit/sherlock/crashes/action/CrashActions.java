package com.singhajit.sherlock.crashes.action;

import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.crashes.viewmodel.AppInfoViewModel;

public interface CrashActions {
  void openSendApplicationChooser(String crashDetails);

  void renderAppInfo(AppInfoViewModel viewModel);

  void render(CrashViewModel viewModel);
}
