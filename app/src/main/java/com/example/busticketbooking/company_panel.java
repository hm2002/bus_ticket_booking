package com.example.busticketbooking;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class company_panel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_panel);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.company_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.company_fragment_container, new company_home()).commit();
            navigationView.setCheckedItem(R.id.company_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.company_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.company_fragment_container, new company_home()).commit();
                break;

            case R.id.add_buses:
                getSupportFragmentManager().beginTransaction().replace(R.id.company_fragment_container, new addBuses()).commit();
                break;

            case R.id.company_buses:
                getSupportFragmentManager().beginTransaction().replace(R.id.company_fragment_container, new company_view_buses()).commit();
                break;

            case R.id.company_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.company_fragment_container, new help_and_support()).commit();
                break;

            case R.id.company_booking:
                getSupportFragmentManager().beginTransaction().replace(R.id.company_fragment_container, new company_view_booking()).commit();
                break;

            case R.id.company_logout:
                logoutmenu(company_panel.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutmenu(company_panel company_panel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(company_panel);
        builder.setTitle("Log out");
        builder.setMessage("Are you Sure You want to Logout ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}