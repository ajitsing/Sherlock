package com.singhajit.sherlock.crashes.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.crashes.viewmodel.AppInfoRowViewModel;
import com.singhajit.sherlock.crashes.viewmodel.AppInfoViewModel;
import com.singhajit.sherlock.databinding.AppInfoRowBinding;

import java.util.List;

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoViewHolder> {
  private final List<AppInfoRowViewModel> appInfoViewModels;

  public AppInfoAdapter(AppInfoViewModel appInfoViewModel) {
    this.appInfoViewModels = appInfoViewModel.getAppInfoRowViewModels();
  }

  @Override
  public AppInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    AppInfoRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.app_info_row, parent, false);
    return new AppInfoViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(AppInfoViewHolder holder, int position) {
    holder.render(appInfoViewModels.get(position));
  }

  @Override
  public int getItemCount() {
    return appInfoViewModels.size();
  }
}

class AppInfoViewHolder extends RecyclerView.ViewHolder {
  private final AppInfoRowBinding binding;

  AppInfoViewHolder(AppInfoRowBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  void render(AppInfoRowViewModel appInfoViewModel) {
    binding.setAppInfo(appInfoViewModel);
  }
}

