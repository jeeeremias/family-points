package com.prototype.familypoints.activities;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.prototype.familypoints.ListAdapters.TasksCustomListAdapter;
import com.prototype.familypoints.R;
import com.prototype.familypoints.mock.Mock;
import com.prototype.familypoints.model.Task;

import java.util.ArrayList;
import java.util.List;

public class DailyProgressActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_progress);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "D";
                case 1:
                    return "S";
                case 2:
                    return "T";
                case 3:
                    return "Q";
                case 4:
                    return "Q";
                case 5:
                    return "S";
                case 6:
                    return "S";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private ListView mTasksListView;
        private List<Task> mTasksData;
        private TasksCustomListAdapter mTasksCustomListAdapter;
        TextView tasksDoneTextView;
        TextView tasksToDoTextView;
        TextView tasksTotalTextView;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View mView = inflater.inflate(R.layout.fragment_daily_progress, container, false);
            tasksDoneTextView = (TextView) mView.findViewById(R.id.numberTasksDoneTV);
            tasksToDoTextView = (TextView) mView.findViewById(R.id.numberTasksToDoTV);
            tasksTotalTextView = (TextView) mView.findViewById(R.id.numberTasksTotalTV);
            mTasksListView = (ListView) mView.findViewById(R.id.tasksLV);
            mockData();
            mTasksCustomListAdapter = new TasksCustomListAdapter(mTasksData, getActivity(), this);
            mTasksListView.setAdapter(mTasksCustomListAdapter);
            updateNumbers();

            mView.post(new Runnable() {
                @Override
                public void run() {
                    LinearLayout tasksLinearLayout = (LinearLayout) mView.findViewById(R.id.numberTasksLL);
                    LinearLayout tasksToDoLinearLayout = (LinearLayout) mView.findViewById(R.id.numberTasksToDoLL);
                    LinearLayout tasksDoneLinearLayout = (LinearLayout) mView.findViewById(R.id.numberTasksDoneLL);
                    LinearLayout tasksTotalLinearLayout = (LinearLayout) mView.findViewById(R.id.numberTasksTotalLL);
                    int size = tasksLinearLayout.getWidth() / 3;
                    tasksToDoLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(size, size));
                    tasksDoneLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(size, size));
                    tasksTotalLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(size, size));
                }
            });

            return mView;
        }

        private void mockData() {
            mTasksData = new ArrayList<>();
            for(int i = 0; i < Mock.TASKS_DATA_MOCK_LIST_SIZE; i ++) {
                mTasksData.add(new Task(Mock.TASKS_NAMES[i]));
            }
        }

        public void updateNumbers() {
            tasksDoneTextView.setText(String.valueOf(mTasksCustomListAdapter.getCheckedCount()));
            tasksToDoTextView.setText(String.valueOf(mTasksCustomListAdapter.getCount() - mTasksCustomListAdapter.getCheckedCount()));
            tasksTotalTextView.setText(String.valueOf(mTasksCustomListAdapter.getCount()));
        }
    }
}
