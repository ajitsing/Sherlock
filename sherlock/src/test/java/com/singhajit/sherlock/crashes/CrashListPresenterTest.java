package com.singhajit.sherlock.crashes;

import com.singhajit.sherlock.core.database.SherlockDatabaseHelper;
import com.singhajit.sherlock.core.investigation.AppInfo;
import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.crashes.action.CrashListActions;
import com.singhajit.sherlock.crashes.presenter.CrashListPresenter;
import com.singhajit.sherlock.crashes.viewmodel.CrashesViewModel;

import org.hamcrest.CustomTypeSafeMatcher;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CrashListPresenterTest {
  @Test
  public void shouldRenderAllCrashes() throws Exception {
    CrashListActions actions = mock(CrashListActions.class);
    CrashListPresenter presenter = new CrashListPresenter(actions);
    SherlockDatabaseHelper database = mock(SherlockDatabaseHelper.class);
    Crash crash1 = mock(Crash.class);
    when(crash1.getId()).thenReturn(1);
    when(crash1.getAppInfo()).thenReturn(mock(AppInfo.class));
    Crash crash2 = mock(Crash.class);
    when(crash2.getAppInfo()).thenReturn(mock(AppInfo.class));
    List<Crash> crashes = asList(crash1, crash2);
    when(database.getCrashes()).thenReturn(crashes);

    presenter.render(database);

    verify(actions).render(argThat(new CustomTypeSafeMatcher<CrashesViewModel>("") {
      @Override
      protected boolean matchesSafely(CrashesViewModel viewModel) {
        List<CrashViewModel> crashes = viewModel.getCrashViewModels();
        return crashes.size() == 2 && crashes.get(0).getIdentifier() == 1;
      }
    }));
  }
}