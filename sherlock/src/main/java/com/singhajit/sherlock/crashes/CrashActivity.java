package com.singhajit.sherlock.crashes;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.core.investigation.CrimeViewModel;
import com.singhajit.sherlock.core.realm.SherlockRealm;
import com.singhajit.sherlock.core.repo.CriminalRecords;
import com.singhajit.sherlock.databinding.CrashBinding;

public class CrashActivity extends AppCompatActivity {
  public static final String CRASH_ID = "com.singhajit.sherlock.CRASH_ID";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    int crashId = intent.getIntExtra(CRASH_ID, -1);
    CrashBinding binding = DataBindingUtil.setContentView(this, R.layout.crash_activity);

    CrimeViewModel viewModel = new CrimeViewModel();
    binding.setViewModel(viewModel);

    CrashPresenter presenter = new CrashPresenter(new CriminalRecords(SherlockRealm.create(this)));
    presenter.render(crashId, viewModel);
  }
}
