package com.singhajit.sherlock.crashes.util;

import android.view.View;

public class ViewState {

  private final int visibility;

  private ViewState(int visibility) {
    this.visibility = visibility;
  }

  public int getVisibility() {
    return visibility;
  }

  public static class Builder {
    private boolean isVisible;

    public Builder withVisible(boolean isVisible) {
      this.isVisible = isVisible;
      return this;
    }

    public ViewState build() {
      return new ViewState(isVisible ? View.VISIBLE : View.GONE);
    }
  }
}
