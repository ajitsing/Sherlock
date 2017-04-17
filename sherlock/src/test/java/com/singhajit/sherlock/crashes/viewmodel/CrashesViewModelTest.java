package com.singhajit.sherlock.crashes.viewmodel;

import android.view.View;

import com.singhajit.sherlock.core.investigation.CrashViewModel;

import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class CrashesViewModelTest {

  @Test
  public void shouldReturnCrashNotFoundVisibilityAsVISBILEWhenThereAreNoCrashes() throws Exception {
    CrashesViewModel viewModel = new CrashesViewModel(new ArrayList<CrashViewModel>());
    assertThat(viewModel.getCrashNotFoundViewState().getVisibility(), is(View.VISIBLE));
  }

  @Test
  public void shouldReturnCrashNotFoundVisibilityAsGONEWhenThereAreCrashes() throws Exception {
    CrashesViewModel viewModel = new CrashesViewModel(asList(mock(CrashViewModel.class)));
    assertThat(viewModel.getCrashNotFoundViewState().getVisibility(), is(View.GONE));
  }
}