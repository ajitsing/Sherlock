package com.singhajit.sherlock.crashes.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CrashReports;
import com.singhajit.sherlock.crashes.action.CrashListActions;
import com.singhajit.sherlock.crashes.adapter.CrashAdapter;
import com.singhajit.sherlock.crashes.presenter.CrashListPresenter;
import com.singhajit.sherlock.crashes.viewmodel.CrashesViewModel;
import com.singhajit.sherlock.databinding.CrashListBinding;

public class CrashListActivity extends BaseActivity implements CrashListActions {

  private CrashListPresenter presenter;
  private CrashListBinding binding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.crash_list_activity);

    enableHomeButton(binding.toolbar);
    setTitle(R.string.app_name);

    CrashReports crashReports = new CrashReports(SherlockRealm.create(this));
    presenter = new CrashListPresenter(this);
    presenter.render(crashReports);
  }

  @Override
  public void render(CrashesViewModel viewModel) {
    CrashAdapter crashAdapter = new CrashAdapter(viewModel.getCrashViewModels(), presenter);
    binding.setViewModel(viewModel);
    binding.crashList.setAdapter(crashAdapter);
    binding.crashList.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override
  public void openCrashDetails(int crashId) {
    Intent intent = new Intent(this, CrashActivity.class);
    intent.putExtra(CrashActivity.CRASH_ID, crashId);

    startActivity(intent);
  }
}
