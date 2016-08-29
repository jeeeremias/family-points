package com.prototype.familypoints.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.prototype.familypoints.R;
import com.prototype.familypoints.fragments.PlayersFragment;
import com.prototype.familypoints.fragments.RewardsFragment;

public class MainActivity extends AppCompatActivity implements PlayersFragment.OnFragmentInteractionListener, RewardsFragment.OnFragmentInteractionListener{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private android.support.v4.app.FragmentManager mFragmentManager;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        mFragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null) {
            mFragment = PlayersFragment.newInstance();
            mFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, mFragment)
                    .commit();
        }

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
                    case R.id.players_item:
                        mFragment = PlayersFragment.newInstance();
                        mFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_container, mFragment)
                                .commit();
                        break;
                    case R.id.rewards_item:
                        mFragment = RewardsFragment.newInstance();
                        mFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_container, mFragment)
                                .commit();
                        break;
                    case R.id.exit_item:
                        finish();
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_items, menu);
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

    //TODO: Fragment
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
