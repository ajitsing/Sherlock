package com.singhajit.sherlock.crashes.viewmodel;

public class AppInfoRowViewModel {
  private final String attr;
  private final String val;

  public AppInfoRowViewModel(String attr, String val) {
    this.attr = attr;
    this.val = val;
  }

  public String getAttr() {
    return attr;
  }

  public String getVal() {
    return val;
  }
}
