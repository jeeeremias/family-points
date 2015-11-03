package com.prototype.familypoints.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.prototype.familypoints.R;
import com.prototype.familypoints.activities.DailyProgressActivity;
import com.prototype.familypoints.model.Task;

import java.util.List;

public class TasksCustomListAdapter implements ListAdapter {

    public TasksCustomListAdapter(List<Task> mTasks, Activity mContext, DailyProgressActivity.PlaceholderFragment mFragment) {
        this.mTasks = mTasks;
        this.mContext = mContext;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mFragment = mFragment;
    }

    private List<Task> mTasks;
    private Activity mContext;
    private LayoutInflater mInflater;
    private int checkedCount;
    private DailyProgressActivity.PlaceholderFragment mFragment;

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Object getItem(int position) {
        return mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.tasks_list_item, null, true);
        }
        final Task mTask = mTasks.get(position);
        TextView name = (TextView) convertView.findViewById(R.id.itemTasksListNameTV);
        CheckBox mCheckBox = (CheckBox) convertView.findViewById(R.id.itemTasksListIconCB);
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    checkedCount++;
                    mTask.setDone(true);
                } else {
                    checkedCount--;
                    mTask.setDone(false);
                }
                mFragment.updateNumbers();
            }
        });
        if(mTask.isDone()) {
            mCheckBox.setChecked(true);
        } else {
            mCheckBox.setChecked(false);
        }

        name.setText(mTask.getName());
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return mTasks.isEmpty();
    }

    public int getCheckedCount() {
        return checkedCount;
    }
}
