package com.example.tabactivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTripActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    EditText e1,e2,e3,e4,e5,e6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        b1 = findViewById(R.id.button);
        e1 = findViewById(R.id.editText);               //Trip name
        e2 = findViewById(R.id.editText2);              //From
        e3 = findViewById(R.id.editText3);              //To
        e4 = findViewById(R.id.editText4);              //start
        e5 = findViewById(R.id.editText5);              //end
        e6 = findViewById(R.id.editText6);              //budget

        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String trip_name = e1.getText().toString();
        String place_from = e2.getText().toString();
        String place_to = e3.getText().toString();
        String start_date = e4.getText().toString();
        String end_date = e5.getText().toString();
        int budget  = Integer.parseInt(e6.getText().toString());

        Toast.makeText(this, "" +trip_name+"\n"+place_from+"\n"+place_to+"\n"+start_date+"\n"+end_date+"\n"+budget, Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = openOrCreateDatabase("Trip",MODE_PRIVATE,null);

        try {
            String sql = "insert into tripDetails(trip_name,place_from,place_to,start_date,end_date,budget,balance) values('" + trip_name + "','" + place_from +"','"+ place_to +"','"+ start_date +"','"+ end_date +"'," + budget + ","+budget+")";

            db.execSQL(sql);
            Toast.makeText(this, "SuccessFully Registered", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed"+e, Toast.LENGTH_SHORT).show();

        }

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
