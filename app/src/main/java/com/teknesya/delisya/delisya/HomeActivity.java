package com.teknesya.delisya.delisya;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button contact;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<FireModal> list;
    RecyclerView recycle;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contact=(Button)findViewById(R.id.contact);

        final Toolbar tool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Restaurants");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recycle = (RecyclerView) findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        //You set the layout manage, default item animator and dividers for recyclerview here, you were doing it in on click.
        RecyclerView.LayoutManager recyce = new GridLayoutManager(HomeActivity.this,2);
        /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
        // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycle.setLayoutManager(recyce);
        recycle.setItemAnimator( new DefaultItemAnimator());

        loadRestaurants();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

        if (id == R.id.nav_location) {
            // Handle the camera action
        } else if (id == R.id.nav_restaurant) {

        } else if (id == R.id.nav_deal) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_add_book) {

        } else if (id == R.id.nav_rev) {

        } else if (id == R.id.nav_about) {

        }else if (id == R.id.nav_faq) {

        }else if (id == R.id.nav_signout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadRestaurants(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                list = new ArrayList<FireModal>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    FireModal value = dataSnapshot1.getValue(FireModal.class);
//                    FireModal fire = new FireModal();
//                    String name = value.getName();
//                    String address = value.getAddress();
//                    String email = value.getEmail();
//                    fire.setName(name);
//                    fire.setEmail(email);
//                    fire.setAddress(address);
                    list.add(value);

                }
                recyclerAdapter = new RecyclerAdapter(list,HomeActivity.this);
                recycle.setAdapter(recyclerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

    }
}
