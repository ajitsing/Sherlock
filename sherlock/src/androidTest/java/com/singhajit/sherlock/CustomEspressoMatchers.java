package com.singhajit.sherlock;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;

public class CustomEspressoMatchers {
  public static Matcher<View> withRecyclerView(final int recyclerViewId, final int position) {
    return new CustomTypeSafeMatcher<View>("") {
      @Override
      protected boolean matchesSafely(View item) {
        RecyclerView view = (RecyclerView) item.getRootView().findViewById(recyclerViewId);
        return view.getChildAt(position) == item;
      }
    };
  }
}
