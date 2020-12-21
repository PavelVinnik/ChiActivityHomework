package com.example.chiactivityhomework;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static final String SHARED_PREFERENCES_NAME = "prefs";
    private static final String IS_FIRST_LAUNCH_KEY = "isFirstLaunch";
    private static final String TAB_COLOR_KEY = "tabColorKey";
    private static final String TAB_NUMBER_EXTRA = "tabNumberExtra";
    private static final String TAB_BACKGROUND_EXTRA = "tabBackgroundExtra";

    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        if (isFirstLaunch()) {
            Log.d(TAG, "FirstLaunch");
            setColorPref();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = findViewById(R.id.navigation_view);
        int tabTextColor = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE).getInt(TAB_COLOR_KEY, Color.BLACK);
        navigationView.setItemTextColor(ColorStateList.valueOf(tabTextColor));
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String tabNumber = item.getTitle().toString();
        int color = getItemColor();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ContentFragment.newInstance(tabNumber, color))
                .commit();
        drawer.close();
        return true;
    }

    private boolean isFirstLaunch() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (sharedPreferences.getBoolean(IS_FIRST_LAUNCH_KEY, true)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_FIRST_LAUNCH_KEY, false).apply();
            return true;
        } else return false;
    }

    private void setColorPref() {
        Random rng = new Random();
        int color = Color.argb(255, rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        sharedPreferences.edit().putInt(TAB_COLOR_KEY, color).apply();
    }

    private int getItemColor() {
        return getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE).getInt(TAB_COLOR_KEY, Color.BLACK);
    }
}
