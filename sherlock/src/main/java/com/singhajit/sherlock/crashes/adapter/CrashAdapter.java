package com.singhajit.sherlock.crashes.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.crashes.presenter.CrashListPresenter;
import com.singhajit.sherlock.databinding.CrashCardBinding;

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
    CrashCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.crash_card, parent, false);
    return new CrashViewHolder(binding);
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
  private final CrashCardBinding binding;

  CrashViewHolder(CrashCardBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  void render(CrashViewModel gemViewModel, CrashListPresenter presenter) {
    binding.setViewModel(gemViewModel);
    binding.setPresenter(presenter);
  }
}