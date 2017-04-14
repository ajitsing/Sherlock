package com.singhajit.sherlock.core.repo;

import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.crashes.model.Crime;

import org.junit.Before;
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

public class CriminalRecordsTest {
  private Realm realm;

  @Before
  public void setUp() throws Exception {
    realm = SherlockRealm.create(getTargetContext());
    realm.beginTransaction();
    realm.deleteAll();
    realm.commitTransaction();
  }

  @Test
  public void shouldAddCrimeToCriminalRecords() throws Exception {
    CriminalRecords criminalRecords = new CriminalRecords(realm);
    String crimeDetails = "crime details";

    int id = criminalRecords.add(new Crime(crimeDetails));

    assertThat(realm.where(Crime.class).findAll().size(), is(1));

    Crime persistedCrime = realm.where(Crime.class).findFirst();
    assertThat(id, is(1));
    assertThat(persistedCrime.getFacts(), is(crimeDetails));
    assertThat(persistedCrime.getId(), is(1));
    Date date = persistedCrime.getDate();

    Calendar todayDateCalender = Calendar.getInstance();
    Calendar actualDateCalender = Calendar.getInstance();
    actualDateCalender.setTime(date);

    assertThat(actualDateCalender.get(DATE), is(todayDateCalender.get(DATE)));
    assertThat(actualDateCalender.get(DAY_OF_MONTH), is(todayDateCalender.get(DAY_OF_MONTH)));
    assertThat(actualDateCalender.get(YEAR), is(todayDateCalender.get(YEAR)));
  }

  @Test
  public void shouldGetAllCrimesSortedByDate() throws Exception {
    CriminalRecords criminalRecords = new CriminalRecords(realm);
    String facts1 = "crime1 details";
    String facts2 = "crime2 details";

    criminalRecords.add(new Crime(facts1));
    criminalRecords.add(new Crime(facts2));

    List<Crime> crimes = criminalRecords.getAll();

    assertThat(crimes.size(), is(2));
    assertThat(crimes.get(0).getFacts(), is(facts2));
    assertThat(crimes.get(1).getFacts(), is(facts1));
  }
}