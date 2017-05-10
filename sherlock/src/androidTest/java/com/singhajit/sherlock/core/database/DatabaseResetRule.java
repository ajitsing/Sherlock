package com.singhajit.sherlock.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import com.singhajit.sherlock.core.Sherlock;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DatabaseResetRule implements TestRule {
  @Override
  public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        resetDB();
        initializeSherlock();
        base.evaluate();
      }
    };
  }

  private void initializeSherlock() {
    Context targetContext = InstrumentationRegistry.getTargetContext();
    Sherlock.init(targetContext);
  }

  private void resetDB() {
    Context targetContext = InstrumentationRegistry.getTargetContext();
    SherlockDatabaseHelper database = new SherlockDatabaseHelper(targetContext);
    SQLiteDatabase writableDatabase = database.getWritableDatabase();
    writableDatabase.execSQL(CrashTable.TRUNCATE_QUERY);
    writableDatabase.close();
  }
}
