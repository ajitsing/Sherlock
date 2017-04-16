package com.singhajit.sherlock.core.repo;

import com.singhajit.sherlock.RealmResetRule;
import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.realm.SherlockRealm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.YEAR;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CrashReportsTest {
  @Rule
  public RealmResetRule realmResetRule = new RealmResetRule();
  private Realm realm;

  @Before
  public void setUp() throws Exception {
    realm = SherlockRealm.create(getTargetContext());
  }

  @Test
  public void shouldAddCrashToCrashReports() throws Exception {
    CrashReports crashReports = new CrashReports(realm);
    String crashDetails = "crash details";
    String placeOfCrash = "Crash:23";

    int id = crashReports.add(new Crash(placeOfCrash, "Reason of crash", crashDetails));

    assertThat(realm.where(Crash.class).findAll().size(), is(1));

    Crash persistedCrash = realm.where(Crash.class).findFirst();
    assertThat(id, is(1));
    assertThat(persistedCrash.getStackTrace(), is(crashDetails));
    assertThat(persistedCrash.getPlace(), is(placeOfCrash));
    assertThat(persistedCrash.getId(), is(1));
    Date date = persistedCrash.getDate();

    Calendar todayDateCalender = Calendar.getInstance();
    Calendar actualDateCalender = Calendar.getInstance();
    actualDateCalender.setTime(date);

    assertThat(actualDateCalender.get(DATE), is(todayDateCalender.get(DATE)));
    assertThat(actualDateCalender.get(DAY_OF_MONTH), is(todayDateCalender.get(DAY_OF_MONTH)));
    assertThat(actualDateCalender.get(YEAR), is(todayDateCalender.get(YEAR)));
  }

  @Test
  public void shouldGetAllCrashesSortedByDate() throws Exception {
    CrashReports crashReports = new CrashReports(realm);
    String facts1 = "crash1 details";
    String facts2 = "crash2 details";
    String placeOfCrash = "Class1:1";

    crashReports.add(new Crash("ImpactedArea", "Reason of crash", facts1));
    crashReports.add(new Crash(placeOfCrash, "Reason of crash", facts2));

    List<Crash> crashes = crashReports.getAll();

    assertThat(crashes.size(), is(2));
    assertThat(crashes.get(0).getStackTrace(), is(facts2));
    assertThat(crashes.get(0).getPlace(), is(placeOfCrash));
    assertThat(crashes.get(1).getStackTrace(), is(facts1));
  }

  @Test
  public void shouldGetCrashById() throws Exception {
    CrashReports crashReports = new CrashReports(realm);
    String facts1 = "crash1 details";
    String facts2 = "crash2 details";
    String placeOfCrash = "Class1:1";

    crashReports.add(new Crash("ImpactedArea", "Reason of crash", facts1));
    crashReports.add(new Crash(placeOfCrash, "Reason of crash", facts2));

    Crash crash = crashReports.get(2);

    assertThat(crash.getId(), is(2));
    assertThat(crash.getStackTrace(), is(facts2));
    assertThat(crash.getPlace(), is(placeOfCrash));
  }
}