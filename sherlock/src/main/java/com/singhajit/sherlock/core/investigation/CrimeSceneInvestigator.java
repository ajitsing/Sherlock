package com.singhajit.sherlock.core.investigation;

import com.singhajit.sherlock.crashes.model.Crime;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CrimeSceneInvestigator {

  private final Thread thread;
  private final Throwable throwable;

  public CrimeSceneInvestigator(Thread thread, Throwable throwable) {
    this.thread = thread;
    this.throwable = throwable;
  }

  public Crime getCrime() {
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

    return new Crime(builder.toString());
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
