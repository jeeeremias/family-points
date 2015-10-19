package com.prototype.familypoints.activities;

import android.animation.ValueAnimator;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.prototype.familypoints.ListAdapters.PlayersCustomListAdapter;
import com.prototype.familypoints.R;
import com.prototype.familypoints.mock.PlayersMock;
import com.prototype.familypoints.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersActivity extends AppCompatActivity {

    private ListView mPlayersListView;
    private List<Player> mPlayersData;
    private PlayersCustomListAdapter mPlayersCustomListAdapter;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
//                    case R.id.nav_home:
//                        // TODO - Do something
//                        break;
//                    // TODO - Handle other items
                }
                return true;
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.action_open,
                R.string.action_close
        );

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mPlayersListView = (ListView)findViewById(R.id.playersList);
        mockData();
        mPlayersCustomListAdapter = new PlayersCustomListAdapter(mPlayersData, this);
        mPlayersListView.setAdapter(mPlayersCustomListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.players_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void mockData() {
        mPlayersData = new ArrayList<>();
        Bitmap bitmap;
        RoundedBitmapDrawable roundedBitmapDrawable;
        for(int i = 0; i < PlayersMock.DATA_MOCK_LIST_SIZE; i ++) {
            bitmap = BitmapFactory.decodeResource(getResources(), PlayersMock.PICTURES[i]);
            roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            roundedBitmapDrawable.setCornerRadius(Math.min(roundedBitmapDrawable.getMinimumWidth(), roundedBitmapDrawable.getMinimumHeight()));
            mPlayersData.add(new Player(PlayersMock.NAMES[i], i*100, roundedBitmapDrawable));
        }
    }
}
