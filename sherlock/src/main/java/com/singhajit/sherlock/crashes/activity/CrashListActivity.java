package com.singhajit.sherlock.crashes.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.singhajit.sherlock.R;
import com.singhajit.sherlock.core.database.SherlockDatabaseHelper;
import com.singhajit.sherlock.crashes.action.CrashListActions;
import com.singhajit.sherlock.crashes.adapter.CrashAdapter;
import com.singhajit.sherlock.crashes.presenter.CrashListPresenter;
import com.singhajit.sherlock.crashes.viewmodel.CrashesViewModel;

import static com.singhajit.sherlock.crashes.activity.CrashActivity.CRASH_ID;


public class CrashListActivity extends BaseActivity implements CrashListActions {
  public static final String CLEAR_CRASHES = "com.singhajit.sherlock.CLEAR_CRASHES";
  private CrashListPresenter presenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.crash_list_activity);

    Intent intent = getIntent();
    boolean clearCrashes = intent.getBooleanExtra(CLEAR_CRASHES, false);
    ((CheckBox) findViewById(R.id.erase_crashes)).setChecked(clearCrashes);

    enableHomeButton((Toolbar) findViewById(R.id.toolbar));
    setTitle(R.string.app_name);

    SherlockDatabaseHelper database = new SherlockDatabaseHelper(this);
    presenter = new CrashListPresenter(this);
    presenter.render(database);
  }

  @Override
  protected void onDestroy()
  {
    CheckBox eraseAll = (CheckBox) findViewById(R.id.erase_crashes);
    if (eraseAll != null && eraseAll.isChecked())
    {
      SherlockDatabaseHelper dbHelper = new SherlockDatabaseHelper(this);
      dbHelper.clearCrashes();
    }
    super.onDestroy();
  }

  @Override
  public void render(CrashesViewModel viewModel) {
    RecyclerView crashList = (RecyclerView) findViewById(R.id.crash_list);
    CrashAdapter crashAdapter = new CrashAdapter(viewModel.getCrashViewModels(), presenter);
    crashList.setAdapter(crashAdapter);
    crashList.setLayoutManager(new LinearLayoutManager(this));

    LinearLayout noCrashFoundLayout = (LinearLayout) findViewById(R.id.no_crash_found_layout);
    //noinspection WrongConstant
    noCrashFoundLayout.setVisibility(viewModel.getCrashNotFoundViewState().getVisibility());
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
    intent.putExtra(CRASH_ID, crashId);

    startActivity(intent);
  }
}
