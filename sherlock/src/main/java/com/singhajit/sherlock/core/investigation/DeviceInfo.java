package com.singhajit.sherlock.core.investigation;

import io.realm.RealmObject;

public class DeviceInfo extends RealmObject {
  private String brand;
  private String manufacturer;
  private String name;
  private String sdk;

  public DeviceInfo() {
  }

  public DeviceInfo(String brand, String manufacturer, String name, String sdk) {
    this.brand = brand;
    this.manufacturer = manufacturer;
    this.name = name;
    this.sdk = sdk;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getBrand() {
    return brand;
  }

  public String getName() {
    return name;
  }

  public String getSdk() {
    return sdk;
  }

  public static class Builder {
    private String name;
    private String brand;
    private String sdk;
    private String manufacturer;

    public Builder withBrand(String brand) {
      this.brand = brand;
      return this;
    }

    public Builder withSDK(String sdk) {
      this.sdk = sdk;
      return this;
    }

    public Builder withModel(String name) {
      this.name = name;
      return this;
    }

    public Builder withManufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
      return this;
    }

    public DeviceInfo build() {
      return new DeviceInfo(brand, manufacturer, name, sdk);
    }
  }
}
