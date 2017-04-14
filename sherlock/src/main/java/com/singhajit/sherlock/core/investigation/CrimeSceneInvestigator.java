package com.singhajit.sherlock.core.investigation;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

public class CrimeSceneInvestigator {

  private final Thread thread;
  private final Throwable throwable;

  public CrimeSceneInvestigator(Thread thread, Throwable throwable) {
    this.thread = thread;
    this.throwable = throwable;
  }

  public Crime investigate() {
    StringBuilder factsBuilder = new StringBuilder();
    String placeOfCrime = "";

    factsBuilder.append("Time: ");
    factsBuilder.append(new Date());
    factsBuilder.append("\n");
    factsBuilder.append("Thread: ");
    factsBuilder.append(thread.getName());
    factsBuilder.append("\n");
    factsBuilder.append("Message: ");
    factsBuilder.append(throwable.getLocalizedMessage());
    factsBuilder.append("\n");
    factsBuilder.append(stackTrace(throwable.getStackTrace()));
    factsBuilder.append("\n");
    factsBuilder.append("Caused By: ");
    if (throwable.getCause() != null) {
      StackTraceElement[] stackTrace = throwable.getCause().getStackTrace();
      StackTraceElement stackTraceElement = stackTrace[0];
      placeOfCrime = format("%s:%d", stackTraceElement.getClassName(), stackTraceElement.getLineNumber());
      factsBuilder.append(stackTrace(stackTrace));
    }

    return new Crime(placeOfCrime, factsBuilder.toString());
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
