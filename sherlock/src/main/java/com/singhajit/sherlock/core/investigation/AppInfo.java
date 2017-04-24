package com.singhajit.sherlock.core.investigation;

import java.util.Map;
import java.util.TreeMap;

import io.realm.RealmList;
import io.realm.RealmObject;

public class AppInfo extends RealmObject {
  private RealmList<Pair> appDetails = new RealmList<>();

  public AppInfo() {
  }

  private AppInfo(RealmList<Pair> appDetails) {
    this.appDetails = appDetails;
  }

  public Map<String, String> getAppDetails() {
    TreeMap<String, String> details = new TreeMap<>();
    for (Pair pair : appDetails) {
      details.put(pair.getKey(), pair.getVal());
    }
    return details.descendingMap();
  }

  public static class Builder {
    RealmList<Pair> appDetails = new RealmList<>();

    public Builder with(String key, String value) {
      appDetails.add(new Pair(key, value));
      return this;
    }

    public AppInfo build() {
      return new AppInfo(appDetails);
    }
  }
}
