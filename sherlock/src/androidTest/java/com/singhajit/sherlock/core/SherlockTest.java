package com.singhajit.sherlock.core;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.singhajit.sherlock.RealmResetRule;
import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CrashReports;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import io.realm.Realm;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SherlockTest {

  @Rule
  public RealmResetRule realmResetRule = new RealmResetRule();

  @Test
  public void shouldReturnAllCapturedCrashes() throws Exception {
    Context targetContext = InstrumentationRegistry.getTargetContext();
    Sherlock.init(targetContext);

    Realm realm = SherlockRealm.create(targetContext);
    CrashReports crashReports = new CrashReports(realm);
    String placeOfCrash1 = "com.singhajit.Sherlock:10";
    String stackTrace1 = "Crash 1 details";

    String placeOfCrash2 = "com.singhajit.SherlockAssistant:5";
    String stackTrace2 = "Crash 2 details";

    crashReports.add(new Crash(placeOfCrash1, "Reason of crash", stackTrace1));
    crashReports.add(new Crash(placeOfCrash2, "Reason of crash", stackTrace2));

    List<Crash> allCrashes = Sherlock.getInstance().getAllCrashes();

    assertThat(allCrashes.size(), is(2));
  }
}