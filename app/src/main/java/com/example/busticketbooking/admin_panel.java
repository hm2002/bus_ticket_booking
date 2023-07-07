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

public class admin_panel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new admin_home()).commit();
            navigationView.setCheckedItem(R.id.home_menu);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new admin_home()).commit();
                break;

            case R.id.new_request:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new new_userRequest()).commit();
                break;

            case R.id.admin_users:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new admin_view_users()).commit();
                break;

            case R.id.manage_buses:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new admin_view_buses()).commit();
                break;

            case R.id.admin_booking:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new admin_view_bookings()).commit();
                break;

            case R.id.admin_logout:
                logoutmenu(admin_panel.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutmenu(admin_panel admin_panel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(admin_panel);
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
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}