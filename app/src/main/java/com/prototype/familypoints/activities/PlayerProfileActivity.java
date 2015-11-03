package com.prototype.familypoints.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.prototype.familypoints.R;
import com.prototype.familypoints.async.AsyncPicturesMock;
import com.prototype.familypoints.model.Player;
import com.prototype.familypoints.util.KeyArgs;

import java.util.Formatter;

public class PlayerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        Intent mIntent = getIntent();
        Player mPlayer = (Player) mIntent.getExtras().get(KeyArgs.PLAYER_OBJECT);


        ImageView profilePhoto = (ImageView) findViewById(R.id.profile_photoIV);
        ImageView coverPhoto = (ImageView) findViewById(R.id.cover_photoIV);
        TextView textViewName = (TextView) findViewById(R.id.playerNameTV);
        TextView textViewPointsText = (TextView) findViewById(R.id.playerTextPointsTV);
        textViewName.setText(mPlayer.getName());
        textViewPointsText.setText(String.format(getString(R.string.text_points), mPlayer.getName(), mPlayer.getPoints()));
        new AsyncPicturesMock(getResources(), profilePhoto, true).execute(mPlayer.getPicturePath());
        new AsyncPicturesMock(getResources(), coverPhoto, false).execute(mPlayer.getPicturePath());
    }
}
