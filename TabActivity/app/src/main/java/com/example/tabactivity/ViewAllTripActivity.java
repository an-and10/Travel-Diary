package com.example.tabactivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAllTripActivity extends ListActivity implements PopupMenu.OnMenuItemClickListener {
    Button b1 ;
    ArrayList a1,a2,a3,a4;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         //setContentView(R.layout.activity_view_all_trip);
            a1 = new ArrayList();
            a2 = new ArrayList();
            a3 = new ArrayList();
            a4 = new ArrayList();
//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
       // FloatingActionButton fab = findViewById(R.id.fab);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(ViewAllTripActivity.this,AddTripActivity.class);
//                startActivity(i);
//
//
//            }
//        });

       // lv = findViewById(R.id.list);


        SQLiteDatabase db = openOrCreateDatabase("Trip",MODE_PRIVATE,null);
        String sql = "select * from tripDetails";

        Cursor c = db.rawQuery(sql,null);

        while(c.moveToNext())
        {
            String trip_id = c.getString(0);
            String trip_name = c.getString(1);
            String place_from = c.getString(2);
            String place_to = c.getString(3);
            String start_date = c.getString(4);
            String end_date = c.getString(5);
            int budget   = c.getInt(6);
            int balance = c.getInt(7);

          String place  = place_from + " - " + place_to;
          String date = start_date + " - " + end_date;
          String money  = balance + " / " + budget;

          a1.add(trip_name);
          a2.add(place);
          a3.add(date);
          a4.add(money);



        }

        ArrayAdapter ad = new MyArrayAdapter(this,android.R.layout.simple_list_item_1,a1);
//        setListAdapter(ad);
            lv.setAdapter(ad);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.mail:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.upload:
                // do your code
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return false;
        }
    }

    private class MyArrayAdapter extends ArrayAdapter {
        public MyArrayAdapter(ViewAllTripActivity a, int b, ArrayList a1) {
            super(a,b,a1);
        }


        @Override
        public View getView(int position ,View convertView,ViewGroup parent) {

            LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = li.inflate(R.layout.activity_view_all_trip,parent,false);

            TextView t1 = v.findViewById(R.id.textView9);
            TextView t2 = v.findViewById(R.id.textView10);
            TextView t3 = v.findViewById(R.id.textView11);
            TextView t4 = v.findViewById(R.id.textView12);
            b1 = v.findViewById(R.id.button3);
            ImageView im = v.findViewById(R.id.imageView4);
            im.setImageResource(R.drawable.scene1);

            ImageView im2 = v.findViewById(R.id.imageView5);
            im2.setImageResource(R.drawable.destination);

            ImageView im3 = v.findViewById(R.id.imageView6);
            im3.setImageResource(R.drawable.date);

            ImageView im4 = v.findViewById(R.id.imageView7);
            im4.setImageResource(R.drawable.budget);



            Toast.makeText(ViewAllTripActivity.this, ""+a1.get(position), Toast.LENGTH_SHORT).show();
//           // String[] item = (String[]) a1.toArray(new String[a1.size()]);
            t1.setText(""+a1.get(position));
//          //  String[] item2 = (String[]) a2.toArray(new String[a2.size()]);
            t2.setText(""+a2.get(position));
//         //   String[] item3 = (String[]) a3.toArray(new String[a3.size()]);
            t3.setText(""+a3.get(position));
//        //    String[] item4 = (String[]) a4.toArray(new String[a4.size()]);
            t4.setText(""+a4.get(position));


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(ViewAllTripActivity.this, v);
                    popup.setOnMenuItemClickListener(ViewAllTripActivity.this);
                    popup.inflate(R.menu.menu_list);
                    popup.show();
                }
            });

            return v;
        }
    }

}
