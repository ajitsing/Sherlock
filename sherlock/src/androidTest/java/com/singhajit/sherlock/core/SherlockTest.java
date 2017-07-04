package com.singhajit.sherlock.core;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.singhajit.sherlock.core.database.CrashRecord;
import com.singhajit.sherlock.core.database.DatabaseResetRule;
import com.singhajit.sherlock.core.database.SherlockDatabaseHelper;
import com.singhajit.sherlock.core.investigation.AppInfo;
import com.singhajit.sherlock.core.investigation.AppInfoProvider;
import com.singhajit.sherlock.core.investigation.Crash;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SherlockTest {

  @Rule
  public DatabaseResetRule databaseResetRule = new DatabaseResetRule();

  @Test
  public void shouldReturnAllCapturedCrashes() throws Exception {
    Context targetContext = InstrumentationRegistry.getTargetContext();
    Sherlock.init(targetContext);

    SherlockDatabaseHelper database = new SherlockDatabaseHelper(targetContext);
    String placeOfCrash1 = "com.singhajit.Sherlock:10";
    String stackTrace1 = "Crash 1 details";

    String placeOfCrash2 = "com.singhajit.SherlockAssistant:5";
    String stackTrace2 = "Crash 2 details";

    Crash crash1 = new Crash(placeOfCrash1, "Reason of crash", stackTrace1);
    Crash crash2 = new Crash(placeOfCrash2, "Reason of crash", stackTrace2);
    database.insertCrash(CrashRecord.createFrom(crash1));
    database.insertCrash(CrashRecord.createFrom(crash2));

    List<Crash> allCrashes = Sherlock.getInstance().getAllCrashes();

    assertThat(allCrashes.size(), is(2));
  }

  @Test
  public void shouldSetAppInfoProvider() throws Exception {
    Context targetContext = InstrumentationRegistry.getTargetContext();
    Sherlock.init(targetContext);

    Sherlock.setAppInfoProvider(new AppInfoProvider() {
      @Override
      public AppInfo getAppInfo() {
        return new AppInfo.Builder().with("Version", "2.21").with("BuildNumber", "221B").build();
      }
    });

    AppInfo appInfo = Sherlock.getInstance().getAppInfoProvider().getAppInfo();
    assertThat(appInfo.getAppDetails().size(), is(2));
    assertThat(appInfo.getAppDetails().get("Version"), is("2.21"));
    assertThat(appInfo.getAppDetails().get("BuildNumber"), is("221B"));
  }
}