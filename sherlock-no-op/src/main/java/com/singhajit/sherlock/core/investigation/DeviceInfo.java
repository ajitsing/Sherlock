package com.singhajit.sherlock.core.investigation;

public class DeviceInfo {
  private DeviceInfo() {
  }

  public String getManufacturer() {
    return "";
  }

  public String getBrand() {
    return "";
  }

  public String getName() {
    return "";
  }

  public String getSdk() {
    return "";
  }

  public static class Builder {
    public Builder withBrand(String brand) {
      return this;
    }

    public Builder withSDK(String sdk) {
      return this;
    }

    public Builder withModel(String name) {
      return this;
    }

    public Builder withManufacturer(String manufacturer) {
      return this;
    }

    public DeviceInfo build() {
      return new DeviceInfo();
    }
  }
}
