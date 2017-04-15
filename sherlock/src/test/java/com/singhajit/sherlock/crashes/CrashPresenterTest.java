package com.singhajit.sherlock.crashes;

import com.singhajit.sherlock.core.investigation.Crime;
import com.singhajit.sherlock.core.investigation.CrimeViewModel;
import com.singhajit.sherlock.core.repo.CriminalRecords;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CrashPresenterTest {
  @Test
  public void shouldInitializeCrashView() throws Exception {
    CriminalRecords criminalRecords = mock(CriminalRecords.class);
    Crime crime = mock(Crime.class);
    when(criminalRecords.get(1)).thenReturn(crime);
    CrashPresenter presenter = new CrashPresenter(criminalRecords, null);

    CrimeViewModel viewModel = mock(CrimeViewModel.class);
    presenter.render(1, viewModel);

    verify(viewModel).populate(crime);
  }

  @Test
  public void shouldShareCrashDetails() throws Exception {
    CrashActions actions = mock(CrashActions.class);
    CrashPresenter presenter = new CrashPresenter(null, actions);

    CrimeViewModel viewModel = mock(CrimeViewModel.class);
    when(viewModel.getFacts()).thenReturn("crashDetails");

    presenter.shareCrashDetails(viewModel);

    verify(actions).openSendApplicationChooser("crashDetails");
  }
}