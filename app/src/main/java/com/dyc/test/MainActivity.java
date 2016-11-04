package com.dyc.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.dyc.test.activity.SpalshScreenActivity;
import com.dyc.test.base.BaseActivity;
import com.dyc.test.fragment.BroadcastFragment;
import com.dyc.test.fragment.CardViewFragment;
import com.dyc.test.fragment.MessageFragment;
import com.dyc.test.fragment.NotificationFragment;
import com.dyc.test.fragment.PictureAndCameraFragment;
import com.dyc.test.fragment.QueryLocalMediaFragment;
import com.dyc.test.fragment.ScrolltitleFragment;
import com.dyc.test.fragment.TablayoutFragment;
import com.dyc.test.fragment.TestFramgent;
import com.dyc.test.fragment.TestRecycleViewFragment;
import com.dyc.test.fragment.TestServiceFagment;
import com.dyc.test.fragment.TestTouchFragment;
import com.dyc.test.fragment.TestVolleyFragment;
import com.dyc.test.tools.FragmentManager;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_broadcast);
        FragmentManager.setFragmentWithStrName(this, BroadcastFragment.class.getName());
        setTitle("broadcast");
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (FragmentManager.getFragmentBackCount(this) > 0) {
            getSupportFragmentManager().findFragmentById(R.id.fragmentlayout).getChildFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_broadcast) {
            // Handle the camera action
//            startActivity(new Intent(this,BroadcastActivity1.class));
            FragmentManager.setFragmentWithStrName(this, BroadcastFragment.class.getName());
        } else if (id == R.id.nav_message) {
            FragmentManager.setFragmentWithStrName(this, MessageFragment.class.getName());
        } else if (id == R.id.nav_service) {
            FragmentManager.setFragmentWithStrName(this, TestServiceFagment.class.getName());
        } else if (id == R.id.nav_fragment) {
            FragmentManager.setFragmentWithStrName(this, TestFramgent.class.getName());
        } else if (id == R.id.nav_splashes) {
            startActivity(new Intent(this, SpalshScreenActivity.class));
        } else if (id == R.id.nav_intent) {
            startActivity(new Intent(this, SpalshScreenActivity.class));
        } else if (id == R.id.nav_Touch) {
            FragmentManager.setFragmentWithStrName(this, TestTouchFragment.class.getName());
        } else if (id == R.id.nav_volley) {
            FragmentManager.setFragmentWithStrName(this, TestVolleyFragment.class.getName());
        } else if (id == R.id.nav_scrolltitle) {
            FragmentManager.setFragmentWithStrName(this, ScrolltitleFragment.class.getName());
        } else if(id==R.id.nav_recycleview) {
            FragmentManager.setFragmentWithStrName(this, TestRecycleViewFragment.class.getName());
        }else if(id==R.id.nav_cardview) {
            FragmentManager.setFragmentWithStrName(this, CardViewFragment.class.getName());
        }else if(id==R.id.nav_querymedia) {
            FragmentManager.setFragmentWithStrName(this, QueryLocalMediaFragment.class.getName());
        }else if(id==R.id.nav_querymedia2) {
          startActivity(new Intent(MainActivity.this,MediaCursorActivity.class));
        }else if(id==R.id.nav_piceandcam) {
            FragmentManager.setFragmentWithStrName(this,PictureAndCameraFragment.class.getName());
        }else if(id==R.id.nav_notify) {
            FragmentManager.setFragmentWithStrName(this,NotificationFragment.class.getName());
        }else if(id==R.id.nav_tablayout) {
            FragmentManager.setFragmentWithStrName(this,TablayoutFragment.class.getName());
        }

        setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
