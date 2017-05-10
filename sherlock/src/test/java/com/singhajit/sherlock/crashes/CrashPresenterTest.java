package com.singhajit.sherlock.crashes;

import com.singhajit.sherlock.core.database.SherlockDatabaseHelper;
import com.singhajit.sherlock.core.investigation.AppInfo;
import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.crashes.action.CrashActions;
import com.singhajit.sherlock.crashes.presenter.CrashPresenter;
import com.singhajit.sherlock.crashes.viewmodel.AppInfoViewModel;

import org.hamcrest.CustomTypeSafeMatcher;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CrashPresenterTest {
  @Test
  public void shouldInitializeCrashView() throws Exception {
    SherlockDatabaseHelper database = mock(SherlockDatabaseHelper.class);
    Crash crash = mock(Crash.class);
    when(crash.getId()).thenReturn(1);
    AppInfo appInfo = mock(AppInfo.class);
    when(crash.getAppInfo()).thenReturn(appInfo);
    when(database.getCrashById(1)).thenReturn(crash);
    CrashActions actions = mock(CrashActions.class);
    CrashPresenter presenter = new CrashPresenter(database, actions);

    presenter.render(1);

    verify(actions).render(argThat(new CustomTypeSafeMatcher<CrashViewModel>("") {
      @Override
      protected boolean matchesSafely(CrashViewModel crashViewModel) {
        return crashViewModel.getIdentifier() == 1;
      }
    }));
    verify(actions).renderAppInfo(any(AppInfoViewModel.class));
  }

  @Test
  public void shouldShareCrashDetails() throws Exception {
    CrashActions actions = mock(CrashActions.class);
    CrashPresenter presenter = new CrashPresenter(null, actions);

    CrashViewModel viewModel = mock(CrashViewModel.class);
    when(viewModel.getCrashInfo()).thenReturn("crashDetails");

    presenter.shareCrashDetails(viewModel);

    verify(actions).openSendApplicationChooser("crashDetails");
  }
}