package com.jahr.tobiasschoch.year;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker;
    TextView txtDate, txtDate2;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }


        btnDatePicker = (Button) findViewById(R.id.btn_date);
        txtDate = (TextView) findViewById(R.id.in_date);
        txtDate2 = (TextView) findViewById(R.id.in_date2);
        btnDatePicker.setOnClickListener((View.OnClickListener) this);
        txtDate2.setOnClickListener((View.OnClickListener) this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();
        String name = preferences.getString("date", "");
        txtDate2.setText(name);
        String d_date = txtDate2.getText().toString();
        if (d_date.equals("")) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate2.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                            String d_date = txtDate2.getText().toString();

                            editor.putString("date",d_date);
                            editor.apply();
                            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                            DateTime startDate = formatter.parseDateTime(d_date);
                            DateTime endDate = new DateTime();
                            txtDate.setText(startDate + " Days");
                            txtDate.setTypeface(null, Typeface.BOLD);

                            Days d = Days.daysBetween(startDate, endDate);
                            int days = d.getDays();
                            txtDate.setText(days + " Days");
                            txtDate.setTypeface(null, Typeface.BOLD);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();


        } else {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
            DateTime startDate = formatter.parseDateTime(d_date);
            DateTime endDate = new DateTime();
            txtDate.setText(startDate + " Days");
            txtDate.setTypeface(null, Typeface.BOLD);

            Days d = Days.daysBetween(startDate, endDate);
            int days = d.getDays();
            txtDate.setText(days + " Days");
            txtDate.setTypeface(null, Typeface.BOLD);
        }


    }


    @Override
    public void onClick(View v) {

        if (v == btnDatePicker || v == txtDate2) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate2.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                            String d_date = txtDate2.getText().toString();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                            final SharedPreferences.Editor editor = preferences.edit();
                            String name = preferences.getString("date", "");
                            editor.putString("date",d_date);
                            editor.apply();
                            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                            DateTime startDate = formatter.parseDateTime(d_date);
                            DateTime endDate = new DateTime();
                            txtDate.setText(startDate + " Days");
                            txtDate.setTypeface(null, Typeface.BOLD);

                            Days d = Days.daysBetween(startDate, endDate);
                            int days = d.getDays();
                            txtDate.setText(days + " Days");
                            txtDate.setTypeface(null, Typeface.BOLD);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}