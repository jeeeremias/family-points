package com.prototype.familypoints.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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
import com.prototype.familypoints.util.KeyArgs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PlayersCustomListAdapter implements ListAdapter {

    public PlayersCustomListAdapter(List<Player> mPlayers, Activity mContext){
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mPlayers = mPlayers;
        this.mContext = mContext;
    }

    private List<Player> mPlayers;
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
        return mPlayers.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlayers.get(position);
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
            convertView = mInflater.inflate(R.layout.players_list_item, null, true);
        }
        final Player player = mPlayers.get(position);
        ImageView picture = (ImageView) convertView.findViewById(R.id.itemPlayersListPictureIV);
        TextView name = (TextView) convertView.findViewById(R.id.itemPlayersListNameTV);
        TextView points = (TextView) convertView.findViewById(R.id.itemPlayersListPointsTV);
        name.setText(player.getName());
        new AsyncPicturesMock(mContext.getResources(), picture, true).execute(player.getPicturePath());
        points.setText(player.getPoints() + " " + mContext.getString(R.string.word_points));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, PlayerProfileActivity.class);
                mIntent.putExtra(KeyArgs.PLAYER_OBJECT, player);
                mContext.startActivity(mIntent);
            }
        });
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
        return mPlayers.isEmpty();
    }
}
