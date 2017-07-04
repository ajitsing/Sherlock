package com.singhajit.sherlock.core.database;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.singhajit.sherlock.core.investigation.Crash;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SherlockDatabaseHelperTest {

  private SherlockDatabaseHelper database;

  @Rule
  public DatabaseResetRule resetRule = new DatabaseResetRule();

  @Before
  public void setUp() throws Exception {
    Context targetContext = InstrumentationRegistry.getTargetContext();
    database = new SherlockDatabaseHelper(targetContext);
  }

  @Test
  public void shouldGetAllCrashes() throws Exception {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Crash.DATE_FORMAT);
    Date date1 = new Date();
    Date date2 = new Date();
    CrashRecord crashRecord1 = new CrashRecord("place1", "reason1", simpleDateFormat.format(date1), "stacktrace1");
    CrashRecord crashRecord2 = new CrashRecord("place2", "reason2", simpleDateFormat.format(date2), "stacktrace2");
    database.insertCrash(crashRecord1);
    database.insertCrash(crashRecord2);

    List<Crash> crashes = database.getCrashes();

    assertThat(crashes.size(), is(2));
    assertThat(crashes.get(0).getReason(), is("reason2"));
    assertThat(crashes.get(0).getPlace(), is("place2"));
    assertThat(crashes.get(0).getStackTrace(), is("stacktrace2"));
    assertNotNull(crashes.get(0).getAppInfo());
    assertNotNull(crashes.get(0).getDeviceInfo());
    assertThat(simpleDateFormat.format(crashes.get(0).getDate()), is(simpleDateFormat.format(date2)));
  }

  @Test
  public void shouldGetCrashById() throws Exception {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Crash.DATE_FORMAT);
    Date date1 = new Date();
    Date date2 = new Date();
    CrashRecord crashRecord1 = new CrashRecord("place1", "reason1", simpleDateFormat.format(date1), "stacktrace1");
    CrashRecord crashRecord2 = new CrashRecord("place2", "reason2", simpleDateFormat.format(date2), "stacktrace2");
    database.insertCrash(crashRecord1);
    int crash2Id = database.insertCrash(crashRecord2);

    Crash crash = database.getCrashById(crash2Id);

    assertNotNull(crash);
    assertThat(crash.getReason(), is("reason2"));
    assertThat(crash.getPlace(), is("place2"));
    assertThat(crash.getStackTrace(), is("stacktrace2"));
    assertNotNull(crash.getAppInfo());
    assertNotNull(crash.getDeviceInfo());
  }
}