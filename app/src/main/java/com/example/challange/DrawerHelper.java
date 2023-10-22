package com.example.challange;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class DrawerHelper {
    static ActionBarDrawerToggle drawerToggle;
    static DrawerLayout drawerLayout;
    static NavigationView navigationView;
    AppCompatActivity activity;

    public DrawerHelper(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void init() {
        drawerLayout = activity.findViewById(R.id.drawer_layout);
        navigationView = activity.findViewById(R.id.navigation_view);
        drawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static ActionBarDrawerToggle getDrawerToggle(){
        return drawerToggle;
    }

    public static DrawerLayout getDrawerLayout(){
        return drawerLayout;
    }

    public static NavigationView getNavigationView(){
        return navigationView;
    }
}