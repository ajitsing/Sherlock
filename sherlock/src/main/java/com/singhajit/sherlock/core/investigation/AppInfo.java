package com.singhajit.sherlock.core.investigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AppInfo {
  private List<Pair> appDetails = new ArrayList<>();

  private AppInfo(List<Pair> appDetails) {
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
    List<Pair> appDetails = new ArrayList<>();

    public Builder with(String key, String value) {
      appDetails.add(new Pair(key, value));
      return this;
    }

    public AppInfo build() {
      return new AppInfo(appDetails);
    }
  }
}
