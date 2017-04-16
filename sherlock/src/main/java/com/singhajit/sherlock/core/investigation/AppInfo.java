package com.singhajit.sherlock.core.investigation;

import io.realm.RealmObject;

public class AppInfo extends RealmObject {
  private String version;
  private String details;

  public AppInfo() {
  }

  public AppInfo(String version, String details) {
    this.version = version;
    this.details = details;
  }

  public String getVersion() {
    return version;
  }

  public String getDetails() {
    return details;
  }

  public static class Builder {
    private String version;
    private String details;

    public Builder withVersionName(String version) {
      this.version = version;
      return this;
    }

    public Builder withOtherDetails(String details) {
      this.details = details;
      return this;
    }

    public AppInfo build() {
      return new AppInfo(version, details);
    }
  }
}
