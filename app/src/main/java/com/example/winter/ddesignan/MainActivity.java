package com.example.winter.ddesignan;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private List<String> listStr;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerl);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //-------------------

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        //--------
        listStr = new ArrayList<String>();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tably);
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        for (int i = 0; i < 30; i++) {
            listStr.add(i + "intdata");
        }
        vp.setAdapter(new myAdapter(getSupportFragmentManager(), listStr));
        tabLayout.setupWithViewPager(vp);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            // Handle the camera action
            Toast.makeText(this,"one",Toast.LENGTH_LONG).show();
        } else if (id == R.id.item2) {

        } else if (id == R.id.item3) {

        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
