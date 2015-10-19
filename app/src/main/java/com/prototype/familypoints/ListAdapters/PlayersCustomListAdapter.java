package com.prototype.familypoints.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.prototype.familypoints.R;
import com.prototype.familypoints.model.Player;

import java.util.List;

public class PlayersCustomListAdapter implements ListAdapter {

    public PlayersCustomListAdapter(List<Player> players, Activity context){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.players = players;
        this.context = context;
    }

    private List<Player> players;
    private Activity context;
    private LayoutInflater inflater;

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
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
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
            convertView = inflater.inflate(R.layout.players_list_item, null, true);
        }
        Player player = players.get(position);
        ImageView picture = (ImageView) convertView.findViewById(R.id.itemListPicture);
        TextView name = (TextView) convertView.findViewById(R.id.itemListName);
        TextView points = (TextView) convertView.findViewById(R.id.itemListPoints);
        picture.setImageDrawable(player.getPicture());
        name.setText(player.getName());
        points.setText(player.getPoints() + " " + context.getString(R.string.word_points));
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
        return false;
    }
}
