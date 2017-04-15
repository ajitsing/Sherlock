package com.singhajit.sherlock.crashes.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CrashReports;
import com.singhajit.sherlock.crashes.action.CrashActions;
import com.singhajit.sherlock.crashes.presenter.CrashPresenter;
import com.singhajit.sherlock.databinding.CrashBinding;

public class CrashActivity extends AppCompatActivity implements CrashActions {
  public static final String CRASH_ID = "com.singhajit.sherlock.CRASH_ID";
  private final CrashViewModel viewModel = new CrashViewModel();
  private CrashPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    int crashId = intent.getIntExtra(CRASH_ID, -1);
    CrashBinding binding = DataBindingUtil.setContentView(this, R.layout.crash_activity);

    binding.setViewModel(viewModel);

    Toolbar toolbar = binding.toolbar;
    setSupportActionBar(toolbar);
    setTitle(R.string.crash_report);

    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    presenter = new CrashPresenter(new CrashReports(SherlockRealm.create(this)), this);
    presenter.render(crashId, viewModel);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.crash_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    } else if (item.getItemId() == R.id.share) {
      presenter.shareCrashDetails(viewModel);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void openSendApplicationChooser(String crashDetails) {
    Intent share = new Intent(Intent.ACTION_SEND);
    share.setType("text/plain");
    share.putExtra(Intent.EXTRA_TEXT, crashDetails);

    startActivity(Intent.createChooser(share, getString(R.string.share_dialog_message)));
  }
}
