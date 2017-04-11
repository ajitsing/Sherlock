package com.singhajit.sherlock.core;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StackTraceBuilder {

  public static String buildStackTrace(Thread thread, Throwable throwable) {
    StringBuilder builder = new StringBuilder();

    builder.append("Time: ");
    builder.append(new Date());
    builder.append("\n");
    builder.append("Thread: ");
    builder.append(thread.getName());
    builder.append("\n");
    builder.append("Message: ");
    builder.append(throwable.getLocalizedMessage());
    builder.append("\n");
    builder.append(stackTrace(throwable.getStackTrace()));
    builder.append("\n");
    builder.append("Caused By: ");
    if (throwable.getCause() != null) {
      builder.append(stackTrace(throwable.getCause().getStackTrace()));
    }

    return builder.toString();
  }

  private static String stackTrace(StackTraceElement[] stackTrace) {
    List<StackTraceElement> stackTraceElements = Arrays.asList(stackTrace);
    StringBuilder builder = new StringBuilder();
    for (StackTraceElement stackTraceElement : stackTraceElements) {
      builder.append("at ");
      builder.append(stackTraceElement.toString());
      builder.append("\n");
    }

    return builder.toString();
  }
}
