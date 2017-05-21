package com.singhajit.sherlock.core.investigation;

import java.util.HashMap;
import java.util.Map;

public class AppInfo {
  public Map<String, String> getAppDetails() {
    return new HashMap<>();
  }

  public static class Builder {
    public Builder with(String key, String value) {
      return this;
    }

    public AppInfo build() {
      return new AppInfo();
    }
  }
}
