package com.singhajit.sherlock.crashes;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.RealmResetRule;
import com.singhajit.sherlock.core.Sherlock;
import com.singhajit.sherlock.core.investigation.AppInfo;
import com.singhajit.sherlock.core.investigation.AppInfoProvider;
import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CrashReports;
import com.singhajit.sherlock.crashes.activity.CrashActivity;

import org.junit.Rule;
import org.junit.Test;

import io.realm.Realm;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.singhajit.sherlock.CustomEspressoMatchers.withRecyclerView;
import static org.hamcrest.Matchers.allOf;

public class CrashActivityTest {

  @Rule
  public RealmResetRule realmResetRule = new RealmResetRule();

  @Rule
  public ActivityTestRule<CrashActivity> rule = new ActivityTestRule<>(CrashActivity.class, true, false);

  @Test
  public void shouldRenderCrashDetails() throws Exception {
    Context targetContext = InstrumentationRegistry.getTargetContext();
    Sherlock.init(targetContext);
    Sherlock.setAppInfoProvider(new AppInfoProvider() {
      @Override
      public AppInfo getAppInfo() {
        return new AppInfo.Builder()
            .with("Version", "221")
            .with("Build Number", "213432")
            .build();
      }
    });

    Realm realm = SherlockRealm.create(targetContext);
    CrashReports crashReports = new CrashReports(realm);
    String placeOfCrash = "com.singhajit.Sherlock:10";
    String stackTrace = "Thread: Main Thread\n" +
        "Message: Full Message\n" +
        "at Class1.method1(file1:1)\n" +
        "at Class2.method2(file2:2)\n\n" +
        "Caused By: at Class2.method2(file2:2)\n" +
        "at Class1.method1(file1:1)\n" +
        "at Class2.method2(file2:2)\n";

    crashReports.add(new Crash(placeOfCrash, "Reason of crash", stackTrace));

    Intent intent = new Intent();
    intent.putExtra(CrashActivity.CRASH_ID, 1);

    rule.launchActivity(intent);

    onView(withText("com.singhajit.Sherlock:10")).check(matches(isDisplayed()));
    onView(withText("Reason of crash")).check(matches(isDisplayed()));
    onView(withText(stackTrace)).check(matches(isDisplayed()));

    onView(withText(R.string.android_api_version)).perform(scrollTo()).check(matches(isDisplayed()));
    onView(withText(R.string.name)).check(matches(isDisplayed()));
    onView(withText(R.string.brand)).check(matches(isDisplayed()));

    onView(withText(R.string.about_app)).perform(scrollTo()).check(matches(isDisplayed()));

    onView(withRecyclerView(R.id.app_info_details, 0)).check(matches(allOf(
        hasDescendant(withText("Version")),
        hasDescendant(withText("221"))
    )));

    onView(withRecyclerView(R.id.app_info_details, 1)).check(matches(allOf(
        hasDescendant(withText("Build Number")),
        hasDescendant(withText("213432"))
    )));
  }
}