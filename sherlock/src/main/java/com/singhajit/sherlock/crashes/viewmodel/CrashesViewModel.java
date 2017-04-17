package com.singhajit.sherlock.crashes.viewmodel;

import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.crashes.util.ViewState;

import java.util.List;

public class CrashesViewModel {
  private final List<CrashViewModel> crashViewModels;

  public CrashesViewModel(List<CrashViewModel> crashViewModels) {
    this.crashViewModels = crashViewModels;
  }

  public List<CrashViewModel> getCrashViewModels() {
    return crashViewModels;
  }

  public ViewState getCrashNotFoundViewState() {
    return new ViewState.Builder().withVisible(crashViewModels.isEmpty()).build();
  }
}
