package com.example.androidlab;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button b,t;
    EditText n1,n2,n3,n4,n5,n6;
    String gender,marital,addiction;
    TableLayout tableLayout;
    RadioButton male,female;
    CheckBox smoking,alcohol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        t = findViewById(R.id.time);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
        n4 = findViewById(R.id.n4);
        n5 = findViewById(R.id.n5);
        n6 = findViewById(R.id.n6);
        b.setOnClickListener(this);
        t.setOnClickListener(this);
        tableLayout = findViewById(R.id.display);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        smoking = findViewById(R.id.smoking);
        alcohol = findViewById(R.id.alcohol);

        Spinner spinner =  findViewById(R.id.marital);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marital, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == b) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            n4.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == t) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            n6.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }



    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    gender = "male";
                break;
            case R.id.female:
                if (checked)
                    gender = "female";
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.alcohol:
                if (checked)
                    addiction = "alcohol";
                else
                    break;
            case R.id.smoking:
                if (checked)
                    addiction = "smoking";
                else
                    break;
                // TODO: Veggie sandwich
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        marital = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    public void onSubmitClicked(View view) {
        String name = n1.getText().toString();
        String dob = n4.getText().toString();
        String number = n5.getText().toString();
        int i=0;
        ArrayList<String> values = new ArrayList<>();
        values.add(name);
        values.add(dob);
        values.add(gender);
        values.add(marital);
        values.add(number);
        values.add(addiction);
        for(i=0;i<tableLayout.getChildCount();i++){
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            TextView temp = (TextView)tableRow.getChildAt(1);
            temp.setText(values.get(i));
        }
     }

    public void onResetClicked(View view) {
        n1.setText("");
        n2.setText("");
        n3.setText("");
        n4.setText("");
        n5.setText("");
        n6.setText("");
        male.setChecked(false);
        female.setChecked(false);
        smoking.setChecked(false);
        alcohol.setChecked(false);
        for(int i=0;i<tableLayout.getChildCount();i++){
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            TextView temp = (TextView)tableRow.getChildAt(1);
            temp.setText("");
        }
    }

}
