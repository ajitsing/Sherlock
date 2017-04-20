package com.singhajit.sherlock.core.investigation;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class CrashAnalyzer {

  private final Throwable throwable;

  public CrashAnalyzer(Throwable throwable) {
    this.throwable = throwable;
  }

  public Crash getAnalysis() {
    StringBuilder factsBuilder = new StringBuilder();
    String placeOfCrash;

    factsBuilder.append(throwable.getLocalizedMessage());
    factsBuilder.append("\n");
    factsBuilder.append(stackTrace(throwable.getStackTrace()));
    factsBuilder.append("\n");
    if (throwable.getCause() != null) {
      factsBuilder.append("Caused By: ");
      StackTraceElement[] stackTrace = throwable.getCause().getStackTrace();
      placeOfCrash = getCrashOriginatingClass(stackTrace);
      factsBuilder.append(stackTrace(stackTrace));
    } else {
      placeOfCrash = getCrashOriginatingClass(throwable.getStackTrace());
    }

    return new Crash(placeOfCrash, throwable.getLocalizedMessage(), factsBuilder.toString());
  }

  private String getCrashOriginatingClass(StackTraceElement[] stackTraceElements) {
    if (stackTraceElements.length > 0) {
      StackTraceElement stackTraceElement = stackTraceElements[0];
      return format("%s:%d", stackTraceElement.getClassName(), stackTraceElement.getLineNumber());
    }
    return "";
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
