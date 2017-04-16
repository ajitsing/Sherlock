package com.singhajit.sherlock.core.investigation;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrashViewModelTest {

  @Test
  public void shouldReturnPlaceOfCrash() throws Exception {
    Crash crash = mock(Crash.class);
    when(crash.getPlace()).thenReturn("com.singhajit.sherlock.core.Crash:20");
    CrashViewModel viewModel = new CrashViewModel(crash);

    assertThat(viewModel.getPlace(), is("Crash:20"));
  }

  @Test
  public void shouldReturnDateOfCrash() throws Exception {
    Crash crash = mock(Crash.class);
    Calendar calendar = Calendar.getInstance();
    calendar.set(2017, 4, 15, 10, 50, 55);
    when(crash.getDate()).thenReturn(calendar.getTime());

    CrashViewModel viewModel = new CrashViewModel(crash);

    assertThat(viewModel.getDate(), is("10:50 AM Mon, May 15, 2017"));
  }

  @Test
  public void shouldReturnIdentifier() throws Exception {
    Crash crash = mock(Crash.class);
    when(crash.getId()).thenReturn(1);

    CrashViewModel viewModel = new CrashViewModel(crash);

    assertThat(viewModel.getIdentifier(), is(1));
  }

  @Test
  public void shouldReturnCrashInfoIncludingDeviceInfoAndStackTrace() throws Exception {
    Crash crash = mock(Crash.class);
    when(crash.getStackTrace()).thenReturn("StackTrace");
    DeviceInfo deviceInfo = mock(DeviceInfo.class);
    when(deviceInfo.getBrand()).thenReturn("Motorola");
    when(deviceInfo.getName()).thenReturn("Moto X Play");
    when(deviceInfo.getSdk()).thenReturn("21");
    when(crash.getDeviceInfo()).thenReturn(deviceInfo);

    CrashViewModel viewModel = new CrashViewModel(crash);

    assertThat(viewModel.getCrashInfo(), is("Device Info:\n" +
        "Name: Moto X Play\n" +
        "Brand: Motorola\n" +
        "Android API: 21\n\n" +
        "StackTrace:\nStackTrace\n")
    );
  }
}