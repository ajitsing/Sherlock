package com.singhajit.sherlock.crashes.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.RealmResetRule;
import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CrashReports;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.anyIntent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNot.not;

public class CrashListActivityTest {
  @Rule
  public RealmResetRule realmResetRule = new RealmResetRule();

  @Rule
  public IntentsTestRule<CrashListActivity> rule = new IntentsTestRule<>(CrashListActivity.class, true, false);

  @Test
  public void shouldRenderAllCrashes() throws Exception {
    Realm realm = SherlockRealm.create(InstrumentationRegistry.getTargetContext());
    CrashReports crashReports = new CrashReports(realm);
    String placeOfCrash1 = "com.singhajit.Sherlock:10";
    String stackTrace1 = "Crash 1 details";

    String placeOfCrash2 = "com.singhajit.SherlockAssistant:5";
    String stackTrace2 = "Crash 2 details";

    crashReports.add(new Crash(placeOfCrash1, "Reason of crash", stackTrace1));
    crashReports.add(new Crash(placeOfCrash2, "Reason of crash", stackTrace2));

    rule.launchActivity(null);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy");

    onView(withRecyclerView(R.id.crash_list, 0)).check(matches(allOf(
        hasDescendant(withText("SherlockAssistant:5")),
        hasDescendant(withText(containsString(simpleDateFormat.format(new Date()))))
    )));

    onView(withRecyclerView(R.id.crash_list, 1)).check(matches(allOf(
        hasDescendant(withText("Sherlock:10")),
        hasDescendant(withText(containsString(simpleDateFormat.format(new Date()))))
    )));

    onView(withText(R.string.not_found)).check(matches(not(isDisplayed())));
  }

  @Test
  public void shouldOpenCrashDetails() throws Exception {
    Realm realm = SherlockRealm.create(InstrumentationRegistry.getTargetContext());
    CrashReports crashReports = new CrashReports(realm);
    String placeOfCrash1 = "com.singhajit.Sherlock:10";
    String stackTrace1 = "Crash 1 details";

    crashReports.add(new Crash(placeOfCrash1, "Reason of crash", stackTrace1));

    rule.launchActivity(null);

    intending(anyIntent()).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, new Intent()));
    onView(withRecyclerView(R.id.crash_list, 0)).perform(click());
    intended(allOf(
        hasComponent(CrashActivity.class.getName()),
        hasExtra(CrashActivity.CRASH_ID, 1)));
  }

  @Test
  public void shouldShowNoCrashFoundMessage() throws Exception {
    rule.launchActivity(null);
    onView(withId(R.id.no_crash_found_layout)).check(matches(isDisplayed()));
  }

  private Matcher<View> withRecyclerView(final int recyclerViewId, final int position) {
    return new CustomTypeSafeMatcher<View>("") {
      @Override
      protected boolean matchesSafely(View item) {
        RecyclerView view = (RecyclerView) item.getRootView().findViewById(recyclerViewId);
        return view.getChildAt(position) == item;
      }
    };
  }
}