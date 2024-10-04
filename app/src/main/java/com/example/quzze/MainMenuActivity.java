package com.example.quzze;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        // Set up toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        // Load the HomeFragment when activity starts
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, HomeFragment.newInstance())
                    .commit();
        }

        // Set up navigation drawer
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_rules) {
            toolbar.setTitle(R.string.rule);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new RulesFragment()).commit();
        } else if (itemId == R.id.nav_history) {
            toolbar.setTitle(R.string.history);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new HistoryFragment()).commit();
        } else if (itemId == R.id.nav_about) {
            toolbar.setTitle(R.string.about);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new AboutFragment()).commit();
        } else if (itemId == R.id.nav_settings) {
            toolbar.setTitle(R.string.settings);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new SettingsFragment()).commit();
        }else if (itemId == R.id.nav_home) {
            toolbar.setTitle(R.string.home);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new HomeFragment()).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
