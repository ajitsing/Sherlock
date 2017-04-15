package com.singhajit.sherlock.crashes.action;

import com.singhajit.sherlock.core.investigation.CrashViewModel;

import java.util.List;

public interface CrashListActions {
  void render(List<CrashViewModel> crashes);

  void openCrashDetails(int crashId);
}
