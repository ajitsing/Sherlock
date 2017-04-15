package com.singhajit.sherlock;

import android.support.test.InstrumentationRegistry;

import com.singhajit.sherlock.core.realm.SherlockRealm;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.realm.Realm;

public class RealmTestRule implements TestRule {
  @Override
  public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        resetRealm();
        base.evaluate();
      }
    };
  }

  private void resetRealm() {
    Realm realm = SherlockRealm.create(InstrumentationRegistry.getContext());
    realm.beginTransaction();
    realm.deleteAll();
    realm.commitTransaction();
  }
}
