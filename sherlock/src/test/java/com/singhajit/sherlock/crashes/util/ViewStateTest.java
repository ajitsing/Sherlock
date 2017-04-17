package com.singhajit.sherlock.crashes.util;

import android.view.View;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ViewStateTest {
  @Test
  public void shouldReturnViewStateBasedOnVisibilityCriteria() throws Exception {
    assertThat(new ViewState.Builder().withVisible(true).build().getVisibility(), is(View.VISIBLE));
    assertThat(new ViewState.Builder().withVisible(false).build().getVisibility(), is(View.GONE));
  }
}