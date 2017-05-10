package com.singhajit.sherlock.crashes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.core.database.SherlockDatabaseHelper;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.crashes.action.CrashActions;
import com.singhajit.sherlock.crashes.adapter.AppInfoAdapter;
import com.singhajit.sherlock.crashes.presenter.CrashPresenter;
import com.singhajit.sherlock.crashes.viewmodel.AppInfoViewModel;

public class CrashActivity extends BaseActivity implements CrashActions {
  public static final String CRASH_ID = "com.singhajit.sherlock.CRASH_ID";
  private CrashViewModel viewModel = new CrashViewModel();
  private CrashPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    int crashId = intent.getIntExtra(CRASH_ID, -1);
    setContentView(R.layout.crash_activity);

    enableHomeButton((Toolbar) findViewById(R.id.toolbar));
    setTitle(R.string.crash_report);

    presenter = new CrashPresenter(new SherlockDatabaseHelper(this), this);
    presenter.render(crashId);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.crash_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.share) {
      presenter.shareCrashDetails(viewModel);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void render(CrashViewModel viewModel) {
    this.viewModel = viewModel;
    TextView crashLocation = (TextView) findViewById(R.id.crash_location);
    TextView crashReason = (TextView) findViewById(R.id.crash_reason);
    TextView stackTrace = (TextView) findViewById(R.id.stacktrace);

    crashLocation.setText(viewModel.getExactLocationOfCrash());
    crashReason.setText(viewModel.getReasonOfCrash());
    stackTrace.setText(viewModel.getStackTrace());

    renderDeviceInfo(viewModel);
  }

  @Override
  public void openSendApplicationChooser(String crashDetails) {
    Intent share = new Intent(Intent.ACTION_SEND);
    share.setType("text/plain");
    share.putExtra(Intent.EXTRA_TEXT, crashDetails);

    startActivity(Intent.createChooser(share, getString(R.string.share_dialog_message)));
  }

  @Override
  public void renderAppInfo(AppInfoViewModel viewModel) {
    RecyclerView appInfoDetails = (RecyclerView) findViewById(R.id.app_info_details);
    appInfoDetails.setAdapter(new AppInfoAdapter(viewModel));
    appInfoDetails.setLayoutManager(new LinearLayoutManager(this));
  }

  private void renderDeviceInfo(CrashViewModel viewModel) {
    TextView deviceName = (TextView) findViewById(R.id.device_name);
    TextView deviceBrand = (TextView) findViewById(R.id.device_brand);
    TextView deviceAndroidVersion = (TextView) findViewById(R.id.device_android_version);

    deviceName.setText(viewModel.getDeviceName());
    deviceBrand.setText(viewModel.getDeviceBrand());
    deviceAndroidVersion.setText(viewModel.getDeviceAndroidApiVersion());
  }
}
