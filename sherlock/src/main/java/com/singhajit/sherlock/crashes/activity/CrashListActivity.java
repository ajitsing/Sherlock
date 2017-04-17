package com.singhajit.sherlock.crashes.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

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
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.sherlock, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.github_link) {
      Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.github_link)));
      startActivity(browserIntent);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void openCrashDetails(int crashId) {
    Intent intent = new Intent(this, CrashActivity.class);
    intent.putExtra(CrashActivity.CRASH_ID, crashId);

    startActivity(intent);
  }
}
