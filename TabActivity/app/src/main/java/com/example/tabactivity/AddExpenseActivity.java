package com.example.tabactivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button b;
    EditText e,e1,e3;
    Spinner sp;
    String[] category= {"Select Your Category","Accomodations","Food","Flight","Shop","Others"};
    String local_cat= "";
    int trip_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        Bundle b1  = getIntent().getExtras();
        trip_id = b1.getInt("id");
        e = findViewById(R.id.editText);
        e1 = findViewById(R.id.editText1);
        e3 = findViewById(R.id.editText3);

        b = findViewById(R.id.button);
        sp = findViewById(R.id.spinner);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,category);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);

        b.setOnClickListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position==0)
        {
           local_cat= category[position];
        }
        else if(position==1)
        {
            local_cat= category[position];
        }
        else if(position==2)
        {
            local_cat= category[position];
        }
        else if(position==3)
        {
            local_cat= category[position];
        }
        else if(position==4)
        {
            local_cat= category[position];
        }



    }

    @Override
    public void onClick(View v) {



        String notes = e.getText().toString();
        int amount = Integer.parseInt(e1.getText().toString());
        String date = e3.getText().toString();

        String category = local_cat;
        Toast.makeText(this, ""+category, Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = openOrCreateDatabase("Trip",MODE_PRIVATE,null);
        String sql2 = "insert into expenseDetails(notes,category,amount_spend,date,trip_id) values('" + notes + "','"+ category +"','"+ amount +"','"+ date +"'," + trip_id + ")";

        db.execSQL(sql2);

        String countQuery = "SELECT  * FROM tripDetails where trip_id ="+trip_id;

        Cursor cursor = db.rawQuery(countQuery, null);
        int current_bal = -3;

        if(cursor.moveToFirst()) {
            current_bal = Integer.parseInt(cursor.getString(cursor.getColumnIndex("balance")));
        }



        int update_bal = current_bal - amount;


        String sql4 = "update  tripDetails set balance ="+ update_bal+ " where trip_id ="+trip_id;
        db.execSQL(sql4);

        Toast.makeText(this, "Current BLANCE :"+update_bal, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "SuccessFully Added", Toast.LENGTH_LONG).show();
        Toast.makeText(this, ""+notes + "\n"+amount + "\n"+date +"\n" + category, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }




    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
