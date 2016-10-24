package com.bertrobotics.bertscout2017;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class PitScoutingFragment extends Fragment {

    public View mRootView;
    public DBHelper dbHelper;

    StatisticsFragment mStatisticsFragment;

    public PitScoutingFragment(StatisticsFragment statisticsFragment) {
        mStatisticsFragment = statisticsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.pit_scouting, container, false);
        dbHelper = new DBHelper(mRootView.getContext());

        return mRootView;
    }

    private class AsyncTaskDeleteData extends AsyncTask<String, Void, String> {

        View rootView;

        ProgressDialog progress;

        private AsyncTaskDeleteData(View pRootView) {

            rootView = pRootView;

            progress = new ProgressDialog(rootView.getContext());
        }

        @Override
        protected void onPreExecute() {
            progress.setTitle("Saving");
            progress.setMessage("Please wait...");
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                dbHelper.deleteStandScouting();

            } catch (Exception e) {
                return "Failure";
            }

            return "Success";
        }

        @Override
        protected void onPostExecute(String result) {
            mStatisticsFragment.populateList();

            progress.dismiss();

            Toast.makeText(rootView.getContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}