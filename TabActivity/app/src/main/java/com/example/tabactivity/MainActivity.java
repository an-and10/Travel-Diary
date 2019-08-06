package com.example.tabactivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabactivity.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity  {
ProgressBar progressBar;
int trip_id;
//    private ActionBar toolbar;
    TextView t3,t2;
    int t_budget,left;
ActionBar mActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ham4);

        getSupportActionBar().setElevation(0);
        progressBar = findViewById(R.id.progressBar2);
        t3 = findViewById(R.id.textView3);
        t2 = findViewById(R.id.textView2);
        t3.setTextColor(Color.WHITE);
        t2.setTextColor(Color.WHITE);


//        progressBar.setBackgroundColor(Color.GREEN);
//        progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        SQLiteDatabase db = openOrCreateDatabase("Trip",MODE_PRIVATE,null);
        String sql = "Select * from tripdetails";



        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
//
            trip_id = cursor.getInt(0);
            t_budget = cursor.getInt(6);
            left = cursor.getInt(7);
        }
        t2.setText("LEFT: ₹"+left );
        int left2 = t_budget-left;
        t3.setText(" ₹ "+left2 );

        progressBar.setMax(t_budget);
        progressBar.setProgress(left2);


        Toast.makeText(this, "Latest Trip Id :"+ trip_id, Toast.LENGTH_SHORT).show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,AddExpenseActivity.class);
                i.putExtra("id",trip_id);
                startActivity(i);


            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(MainActivity.this,ViewAllTripActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {


        MenuItem item1 = menu.add(0, 1, 1, "ADD");
        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, 2, 2, "UPDATE");
        menu.add(0, 3, 3, "UPDATE");
        menu.add(0, 4, 4, "DELETE");
        menu.add(0, 5, 5, "EXIT");

        return super.onCreateOptionsMenu(menu);
    }
}