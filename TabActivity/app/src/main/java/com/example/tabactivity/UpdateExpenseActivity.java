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

public class UpdateExpenseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Button b;
    EditText e,e1,e3;
    Spinner sp;
    String local_cat;
    String[] category= {"Select Your Category","Accomodations","Food","Flight","Shop","Others"};
    String notes,date;
    int balance,id;
    int old_balance;
    int tid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense);

        e = findViewById(R.id.editText);
        e1 = findViewById(R.id.editText1);
        e3 = findViewById(R.id.editText3);

        b = findViewById(R.id.button);
        sp = findViewById(R.id.spinner);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,category);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);

        b.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
         e.setText(b.getString("Notes"));
        e1.setText(b.getInt("Balance"));
        e3.setText(b.getString("Date"));

        old_balance = b.getInt("Balance");
        tid = b.getInt("TID");

        id = b.getInt("E_Id");


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
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        notes = e.getText().toString();
        date = e3.getText().toString();
        balance = Integer.parseInt(e1.getText().toString());

        SQLiteDatabase db = openOrCreateDatabase("Trip",MODE_PRIVATE,null);
        try {




            String sql = "update  expenseDetails set notes='"+notes+"' ,date='"+date+"' , amount_spend = "+balance+",category='"+local_cat+ "'  where expense_id="+id;


            db.execSQL(sql);


            int amount = balance - (old_balance);

            String countQuery = "SELECT  * FROM tripDetails where trip_id ="+tid;

            Cursor cursor = db.rawQuery(countQuery, null);
            int current_bal_db = 0;

            if(cursor.moveToFirst()) {
                current_bal_db = Integer.parseInt(cursor.getString(cursor.getColumnIndex("balance")));
            }



            int update_bal = current_bal_db - amount;


            String sql4 = "update  tripDetails set balance ="+ update_bal+ " where trip_id ="+tid;
            db.execSQL(sql4);

            Toast.makeText(this, "Current BALANCE :"+update_bal, Toast.LENGTH_SHORT).show();


            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed to Update "+e, Toast.LENGTH_SHORT).show();

        }

    }
}
