package com.singhajit.sherlock.core.investigation;

import io.realm.RealmObject;

public class AppInfo extends RealmObject {
  private String version;
  private int versionCode;
  private String details;

  public AppInfo() {
  }

  public AppInfo(String version, int versionCode, String details) {
    this.version = version;
    this.versionCode = versionCode;
    this.details = details;
  }

  public String getVersion() {
    return version;
  }

  public int getVersionCode() {
    return versionCode;
  }

  public static class Builder {
    private String version;
    private int versionCode;
    private String details;

    public Builder withVersionName(String version) {
      this.version = version;
      return this;
    }

    public Builder withVersionCode(int versionCode) {
      this.versionCode = versionCode;
      return this;
    }

    public Builder withOtherDetails(String details) {
      this.details = details;
      return this;
    }

    public AppInfo build() {
      return new AppInfo(version, versionCode, details);
    }
  }
}
