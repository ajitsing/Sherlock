package com.singhajit.sherlock.core.investigation;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrashAnalyzerTest {
  @Test
  public void shouldBuildStackTrace() throws Exception {
    Throwable throwable = mock(Throwable.class);
    when(throwable.getLocalizedMessage()).thenReturn("Full Message");

    StackTraceElement stackTraceElement1 = new StackTraceElement("Class1", "method1", "file1", 1);
    StackTraceElement stackTraceElement2 = new StackTraceElement("Class2", "method2", "file2", 2);
    StackTraceElement[] stackTraceElements = {stackTraceElement1, stackTraceElement2};

    Throwable appThrowable = mock(Throwable.class);
    StackTraceElement[] actualStackTraceElements = {stackTraceElement2, stackTraceElement1, stackTraceElement2};
    when(appThrowable.getStackTrace()).thenReturn(actualStackTraceElements);
    when(throwable.getCause()).thenReturn(appThrowable);
    when(throwable.getStackTrace()).thenReturn(stackTraceElements);

    CrashAnalyzer investigator = new CrashAnalyzer(throwable);

    Crash crash = investigator.getAnalysis();
    assertThat(crash.getStackTrace(), containsString("Full Message\n" +
        "at Class1.method1(file1:1)\n" +
        "at Class2.method2(file2:2)\n\n" +
        "Caused By: at Class2.method2(file2:2)\n" +
        "at Class1.method1(file1:1)\n" +
        "at Class2.method2(file2:2)\n")
    );
    assertThat(crash.getPlace(), containsString("Class2:2"));
  }

  @Test
  public void shouldBuildStackTraceWhenCauseIsNotPresentInsideThrowable() throws Exception {
    Throwable throwable = mock(Throwable.class);
    when(throwable.getLocalizedMessage()).thenReturn("Full Message");

    StackTraceElement stackTraceElement1 = new StackTraceElement("Class1", "method1", "file1", 1);
    StackTraceElement stackTraceElement2 = new StackTraceElement("Class2", "method2", "file2", 2);
    StackTraceElement[] stackTraceElements = {stackTraceElement1, stackTraceElement2};

    when(throwable.getCause()).thenReturn(null);
    when(throwable.getStackTrace()).thenReturn(stackTraceElements);

    CrashAnalyzer investigator = new CrashAnalyzer(throwable);

    Crash crash = investigator.getAnalysis();
    assertThat(crash.getStackTrace(), containsString("Full Message\n" +
        "at Class1.method1(file1:1)\n" +
        "at Class2.method2(file2:2)\n\n")
    );
    assertThat(crash.getPlace(), containsString("Class1:1"));
  }
}