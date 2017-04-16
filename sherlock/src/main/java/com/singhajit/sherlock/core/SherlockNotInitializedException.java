package com.singhajit.sherlock.core;

public class SherlockNotInitializedException extends RuntimeException {
  public SherlockNotInitializedException() {
    super("Initialize Sherlock using Sherlock.init(context) before using its methods");
  }
}
