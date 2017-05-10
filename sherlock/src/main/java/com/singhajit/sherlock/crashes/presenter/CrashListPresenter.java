package com.singhajit.sherlock.crashes.presenter;

import com.singhajit.sherlock.core.database.SherlockDatabaseHelper;
import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.crashes.action.CrashListActions;
import com.singhajit.sherlock.crashes.viewmodel.CrashesViewModel;

import java.util.ArrayList;
import java.util.List;

public class CrashListPresenter {
  private final CrashListActions actions;

  public CrashListPresenter(CrashListActions actions) {
    this.actions = actions;
  }

  public void render(SherlockDatabaseHelper database) {
    List<Crash> crashes = database.getCrashes();
    ArrayList<CrashViewModel> crashViewModels = new ArrayList<>();
    for (Crash crash : crashes) {
      crashViewModels.add(new CrashViewModel(crash));
    }
    actions.render(new CrashesViewModel(crashViewModels));
  }

  public void onCrashClicked(CrashViewModel viewModel) {
    actions.openCrashDetails(viewModel.getIdentifier());
  }
}
