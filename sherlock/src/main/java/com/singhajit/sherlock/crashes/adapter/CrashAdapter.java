package com.singhajit.sherlock.crashes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.crashes.presenter.CrashListPresenter;

import java.util.List;

public class CrashAdapter extends RecyclerView.Adapter<CrashViewHolder> {
  private final List<CrashViewModel> crashes;
  private final CrashListPresenter presenter;

  public CrashAdapter(List<CrashViewModel> crashes, CrashListPresenter presenter) {
    this.crashes = crashes;
    this.presenter = presenter;
  }

  @Override
  public CrashViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    LinearLayout crashCardView = (LinearLayout) inflater.inflate(R.layout.crash_card, parent, false);
    return new CrashViewHolder(crashCardView);
  }

  @Override
  public void onBindViewHolder(CrashViewHolder holder, int position) {
    holder.render(crashes.get(position), presenter);
  }

  @Override
  public int getItemCount() {
    return crashes.size();
  }
}

class CrashViewHolder extends RecyclerView.ViewHolder {
  private final LinearLayout rootView;

  CrashViewHolder(LinearLayout rootView) {
    super(rootView);
    this.rootView = rootView;
  }

  void render(final CrashViewModel crashViewModel, final CrashListPresenter presenter) {
    TextView crashPlace = (TextView) rootView.findViewById(R.id.crash_place);
    TextView crashDate = (TextView) rootView.findViewById(R.id.crash_date);

    crashPlace.setText(crashViewModel.getPlace());
    crashDate.setText(crashViewModel.getDate());
    rootView.findViewById(R.id.crash_card_view).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onCrashClicked(crashViewModel);
      }
    });
  }
}