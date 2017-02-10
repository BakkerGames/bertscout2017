package com.bertrobotics.bertscout2017;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.test.espresso.core.deps.guava.base.Charsets;
import android.support.test.espresso.core.deps.guava.io.CharStreams;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.net.Uri.encode;

public class MainActivity extends AppCompatActivity {

    StandScoutingFragment mStandScoutingFragment;
    PitScoutingFragment mPitScoutingFragment;
    StatisticsFragment mStatisticsFragment;

    View mRootView;

    public DBHelper dbHelper;
//    public ExportData exportData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRootView = findViewById(R.id.main_layout);

        mStatisticsFragment = new StatisticsFragment();
        //mStandScoutingFragment = new StandScoutingFragment(mStatisticsFragment);
        mStandScoutingFragment = new StandScoutingFragment();
//        mPitScoutingFragment = new PitScoutingFragment(mStatisticsFragment);
        mPitScoutingFragment = new PitScoutingFragment();

        dbHelper = new DBHelper(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Stand Scouting"));
        tabLayout.addTab(tabLayout.newTab().setText("Pit Scouting"));
        tabLayout.addTab(tabLayout.newTab().setText("Statistics"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setTitle("North Shore");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.north_shore:
                setTitle("North Shore");

//                mStandScoutingFragment.buildMatchSpinner("north_shore");
                mStandScoutingFragment.buildTeamSpinner("north_shore");

                mPitScoutingFragment.buildTeamSpinner("north_shore");

                mStatisticsFragment.populateList();
                break;
            case R.id.pine_tree:
                setTitle("Pine Tree");

//                mStandScoutingFragment.buildMatchSpinner("pine_tree");
                mStandScoutingFragment.buildTeamSpinner("pine_tree");

                mPitScoutingFragment.buildTeamSpinner("pine_tree");

                mStatisticsFragment.populateList();
                break;
            case R.id.sync_data:
                openSyncDataDialog(mRootView);
                break;
            case R.id.export_data:
                openExportDataDialog(mRootView);
                break;
            case R.id.clear_data:
                openClearDataDialog(mRootView);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.getTabAt(0).select();

        Integer match_no = data.getIntExtra("match_no", 0);
        Integer team = data.getIntExtra("team", 0);

        Spinner teamSpinner = (Spinner) findViewById(R.id.team_spinner);
        teamSpinner.setSelection(((ArrayAdapter) teamSpinner.getAdapter()).getPosition(team.toString()));

//        Spinner matchSpinner = (Spinner) findViewById(R.id.match_spinner);
//        matchSpinner.setSelection(((ArrayAdapter) matchSpinner.getAdapter()).getPosition(match_no.toString()));

        mStandScoutingFragment.loadScreen();
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return mStandScoutingFragment;
                case 1:
                    return mPitScoutingFragment;
                case 2:
                    return mStatisticsFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    public void openSyncDataDialog(final View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Sync data for " + getTitle() + "?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                AsyncTaskSyncData syncData = new AsyncTaskSyncData(view.getContext());
                syncData.execute();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private class AsyncTaskSyncData extends AsyncTask<String, Void, String> {

        ProgressDialog progress;

        private AsyncTaskSyncData(Context context) {
            progress = new ProgressDialog(context);
        }

        @Override
        protected String doInBackground(String... params) {
            String results;

            try {
                String urlString = "http://76.179.97.182/getData.php?event=" + getTitle().toString().replace(" ", "%20");

                // Do your long operations here and return the result
                URL url = new URL(urlString);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                results = CharStreams.toString(new InputStreamReader(
                        in, Charsets.UTF_8));

                JSONArray remoteData = new JSONArray(results);

                for (int i = 0; i < remoteData.length(); i++) {
                    JSONObject row = remoteData.getJSONObject(i);
                    dbHelper.updateStandInfo(row);
                }

                JSONArray localData = dbHelper.getDataAllStand(getTitle().toString());

                String insertResult;

                for (int i = 0; i < localData.length(); i++) {
                    JSONObject row = localData.getJSONObject(i);

                    try {
                        String insertString = "http://76.179.97.182/insertData.php?" +
                                "event=" + encode(row.getString("event")) +
                                "&match_no=" + row.getInt("match_no") +
                                "&team=" + row.getInt("team") +
                                "&auto_high=" + row.getInt("auto_high") +
                                "&auto_low=" + row.getInt("auto_low") +
                                "&auto_cross=" + row.getInt("auto_cross") +
                                "&tele_high=" + row.getInt("tele_high") +
                                "&tele_low=" + row.getInt("tele_low") +
                                "&tele_cross=" + row.getInt("tele_cross") +
                                "&endgame=" + row.getInt("endgame") +
                                "&comment=" + encode(row.getString("comment"));

                        // Do your long operations here and return the result

                        URL insertUrl = new URL(insertString);

                        HttpURLConnection insertUrlConnection = (HttpURLConnection) insertUrl.openConnection();

                        InputStream insertIn = new BufferedInputStream(insertUrlConnection.getInputStream());

                        insertResult = CharStreams.toString(new InputStreamReader(
                                insertIn, Charsets.UTF_8));

                    } catch (Exception e) {
                        return "Error";
                    }
                }
            } catch (Exception e) {
                return "Error";
            }

            return "Success";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress.setTitle("Syncing");
            progress.setMessage("Please wait...");
            progress.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            mStatisticsFragment.populateList();

            progress.dismiss();

            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    }

    public void openExportDataDialog(final View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Export data to Documents folder?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                ExportData exportData = new ExportData();
                String result;
                try
                {
                    JSONArray currStandInfoArray = dbHelper.getDataAllStand(getTitle().toString());
                    String standOutFilename = exportData.getDocumentStorageDir() + "/" +
                            DBContract.TableStandInfo.TABLE_NAME_STAND + ".json";
                    exportData.ExportData(standOutFilename, currStandInfoArray);
                    JSONArray currPitInfoArray = dbHelper.getDataAllPit(""); // ### getTitle().toString()
                    String pitOutFilename = exportData.getDocumentStorageDir() + "/" +
                            DBContract.TablePitInfo.TABLE_NAME_PIT + ".json";
                    exportData.ExportData(pitOutFilename, currPitInfoArray);
                    result = "Export done!";
                } catch (Exception e) {
                    result = "Error during export";
                }
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, result, duration);
                toast.show();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void openClearDataDialog(final View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Clear all data?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                String result;
                try
                {
                    dbHelper.deleteStandScouting();
                    dbHelper.deletePitInfo();
                    result = "Clear done!";
                } catch (Exception e) {
                    result = "Error during clear";
                }
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, result, duration);
                toast.show();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



//    public void openClearDataDialog(final View view){
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage("Clear all data?");
//
//        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//                AsyncTaskClearData clearData = new AsyncTaskClearData(view.getContext());
//                clearData.execute();
//            }
//        });
//
//        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // do nothing
//            }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }

//    private class AsyncTaskClearData extends AsyncTask<String, Void, String> {
//
//        ProgressDialog progress;
//
//        private AsyncTaskClearData(Context context) {
//            progress = new ProgressDialog(context);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            progress.setTitle("Saving");
//            progress.setMessage("Please wait...");
//            progress.show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                dbHelper.deleteStandScouting();
//                dbHelper.deletePitInfo();
//            } catch (Exception e) {
//                return "Failure";
//            }
//            return "Success";
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            mStatisticsFragment.populateList();
//
//            progress.dismiss();
//
//            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//        }
//    }

}