package com.singhajit.sherlock.core.investigation;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrimeViewModelTest {

  @Test
  public void shouldReturnPlaceOfCrime() throws Exception {
    Crime crime = mock(Crime.class);
    when(crime.getPlaceOfCrime()).thenReturn("com.singhajit.sherlock.core.Crime:20");
    CrimeViewModel viewModel = new CrimeViewModel(crime);

    assertThat(viewModel.getPlaceOfCrime(), is("Crime:20"));
  }

  @Test
  public void shouldReturnDateOfCrime() throws Exception {
    Crime crime = mock(Crime.class);
    Calendar calendar = Calendar.getInstance();
    calendar.set(2017, 4, 15, 10, 50, 55);
    when(crime.getDate()).thenReturn(calendar.getTime());

    CrimeViewModel viewModel = new CrimeViewModel(crime);

    assertThat(viewModel.getDate(), is("10:50 AM Mon, May 15, 2017"));
  }

  @Test
  public void shouldReturnIdentifier() throws Exception {
    Crime crime = mock(Crime.class);
    when(crime.getId()).thenReturn(1);

    CrimeViewModel viewModel = new CrimeViewModel(crime);

    assertThat(viewModel.getIdentifier(), is(1));
  }
}