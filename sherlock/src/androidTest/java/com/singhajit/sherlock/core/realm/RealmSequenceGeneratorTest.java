package com.singhajit.sherlock.core.realm;

import com.singhajit.sherlock.RealmTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RealmSequenceGeneratorTest {
  @Rule
  public RealmTestRule realmTestRule = new RealmTestRule();
  private Realm realm;

  @Before
  public void setUp() throws Exception {
    Realm.init(getTargetContext());
    RealmConfiguration configuration = new RealmConfiguration.Builder()
        .name("sherlocktest.realm")
        .deleteRealmIfMigrationNeeded()
        .modules(new SherlockTestModule())
        .build();
    realm = Realm.getInstance(configuration);
  }

  @Test
  public void shouldReturnIdAs_1_ForTheFirstModel() throws Exception {
    RealmSequenceGenerator.generate(realm, TestModel.class);
    assertThat(RealmSequenceGenerator.generate(realm, TestModel.class), is(1));
  }

  @Test
  public void shouldIncrementAndReturnNextId() throws Exception {
    TestModel model = new TestModel();
    model.setId(RealmSequenceGenerator.generate(realm, TestModel.class));
    saveModel(model);
    assertThat(RealmSequenceGenerator.generate(realm, TestModel.class), is(2));
  }

  private void saveModel(RealmObject object) {
    realm.beginTransaction();
    realm.copyToRealm(object);
    realm.commitTransaction();
  }
}