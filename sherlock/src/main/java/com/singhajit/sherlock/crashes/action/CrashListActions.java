package com.singhajit.sherlock.crashes.action;

import com.singhajit.sherlock.crashes.viewmodel.CrashesViewModel;

public interface CrashListActions {
  void render(CrashesViewModel viewModel);

  void openCrashDetails(int crashId);
}
