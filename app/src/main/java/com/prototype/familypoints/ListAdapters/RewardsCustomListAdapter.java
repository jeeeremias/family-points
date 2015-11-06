package com.prototype.familypoints.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.prototype.familypoints.R;
import com.prototype.familypoints.activities.PlayerProfileActivity;
import com.prototype.familypoints.async.AsyncPicturesMock;
import com.prototype.familypoints.model.Player;
import com.prototype.familypoints.model.Reward;
import com.prototype.familypoints.util.KeyArgs;

import java.util.List;

public class RewardsCustomListAdapter implements ListAdapter {

    public RewardsCustomListAdapter(List<Reward> mRewards, Activity mContext){
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mRewards = mRewards;
        this.mContext = mContext;
    }

    private List<Reward> mRewards;
    private Activity mContext;
    private LayoutInflater mInflater;

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
        return mRewards.size();
    }

    @Override
    public Object getItem(int position) {
        return mRewards.get(position);
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
            convertView = mInflater.inflate(R.layout.two_line_avatar_list_item, null, true);
        }
        final Reward reward = mRewards.get(position);
        ImageView picture = (ImageView) convertView.findViewById(R.id.itemListPictureIV);
        TextView name = (TextView) convertView.findViewById(R.id.itemListFirstTextTV);
        TextView points = (TextView) convertView.findViewById(R.id.itemListSecondTextTV);
        name.setText(reward.getName());
        new AsyncPicturesMock(mContext.getResources(), picture, true).execute(reward.getDrawable());
        points.setText(reward.getPoints() + " " + mContext.getString(R.string.word_points));
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
        return mRewards.isEmpty();
    }
}
