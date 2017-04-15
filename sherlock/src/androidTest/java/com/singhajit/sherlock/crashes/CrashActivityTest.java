package com.singhajit.sherlock.crashes;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.singhajit.sherlock.RealmTestRule;
import com.singhajit.sherlock.core.investigation.Crime;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CriminalRecords;

import org.junit.Rule;
import org.junit.Test;

import io.realm.Realm;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CrashActivityTest {

  @Rule
  public RealmTestRule realmTestRule = new RealmTestRule();

  @Rule
  public ActivityTestRule<CrashActivity> rule = new ActivityTestRule<>(CrashActivity.class, true, false);

  @Test
  public void shouldRenderCrashDetails() throws Exception {
    Realm realm = SherlockRealm.create(InstrumentationRegistry.getTargetContext());
    CriminalRecords criminalRecords = new CriminalRecords(realm);
    String placeOfCrime = "com.singhajit.Sherlock:10";
    String statckTrace = "Thread: Main Thread\n" +
        "Message: Full Message\n" +
        "at Class1.method1(file1:1)\n" +
        "at Class2.method2(file2:2)\n\n" +
        "Caused By: at Class2.method2(file2:2)\n" +
        "at Class1.method1(file1:1)\n" +
        "at Class2.method2(file2:2)\n";

    criminalRecords.add(new Crime(placeOfCrime, statckTrace));

    Intent intent = new Intent();
    intent.putExtra(CrashActivity.CRASH_ID, 1);

    rule.launchActivity(intent);

    onView(withText("com.singhajit.Sherlock:10")).check(matches(isDisplayed()));
    onView(withText(statckTrace)).check(matches(isDisplayed()));
  }
}