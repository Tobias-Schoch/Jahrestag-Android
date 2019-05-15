package com.example.tobiasschoch.year;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HomeActivity extends Activity implements OnClickListener {

    private EditText tag;
    private EditText monat;
    private EditText jahr;
    private ImageView hochtag;
    private ImageView hochmonat;
    private ImageView hochjahr;
    private ImageView runtertag;
    private ImageView runtermonat;
    private ImageView runterjahr;
    private ImageView inc;
    private Button button;

    String gesamt2 = "";
    String jahre = "";
    int day = 0;
    int month = 0;
    int year = 0;
    String zusamm = "";
    String all = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        Intent intent = getIntent();
        zusamm = intent.getStringExtra("key");

        button = findViewById(R.id.button);
        inc = findViewById(R.id.inc);
        tag = findViewById(R.id.tag);
        monat = findViewById(R.id.monat);
        jahr = findViewById(R.id.jahr);
        hochtag = findViewById(R.id.hochtag);
        hochmonat = findViewById(R.id.hochmonat);
        hochjahr = findViewById(R.id.hochjahr);
        runtertag = findViewById(R.id.runtertag);
        runtermonat = findViewById(R.id.runtermonat);
        runterjahr = findViewById(R.id.runterjahr);

        button.setOnClickListener(this);
        hochtag.setOnClickListener(this);
        hochmonat.setOnClickListener(this);
        hochjahr.setOnClickListener(this);
        runtertag.setOnClickListener(this);
        runtermonat.setOnClickListener(this);
        runterjahr.setOnClickListener(this);
        inc.setOnClickListener(this);

        DateFormat df = new SimpleDateFormat("ddMMyyyy");
        Date today = Calendar.getInstance().getTime();
        String date = df.format(today);

        if (zusamm == null) {
            day = Integer.parseInt(date.substring(0, 2));
            month = Integer.parseInt(date.substring(2, 4));
            year = Integer.parseInt(date.substring(4));
        } else if (zusamm != null) {
            day = Integer.parseInt(zusamm.substring(0, 2));
            month = Integer.parseInt(zusamm.substring(2, 4));
            year = Integer.parseInt(zusamm.substring(4));
        }


        if (day < 10 && month < 10) {
            tag.setText("0" + day + "");
            monat.setText("0" + month + "");
        } else if (day < 10) {
            tag.setText("0" + day + "");
            monat.setText(month + "");
        } else if (month < 10) {
            tag.setText(day + "");
            monat.setText("0" + month + "");
        } else {
            tag.setText(day + "");
            tag.setText(day + "");
            monat.setText(month + "");
        }

        jahr.setText(year + "");


        String all = tag.getText().toString() + monat.getText().toString() + jahr.getText().toString();

        if (gesamt2 != "" && all != date) {

            Intent mintent2 = new Intent(HomeActivity.this, SecondActivity.class);
            mintent2.putExtra("key", all);
            startActivity(mintent2);
        }


    }


    @Override
    public void onClick(View view) {


        day = Integer.parseInt(tag.getText().toString());
        month = Integer.parseInt(monat.getText().toString());
        year = Integer.parseInt(jahr.getText().toString());
        if (view == hochtag) {
            if (day == 29 && month == 2 && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
                day = 1;
                tag.setText("0" + day + "");
            } else if (day == 28 && month == 2 && (year % 4 != 0 && (year % 100 == 0 || year % 400 != 0))) {
                day = 1;
                tag.setText("0" + day + "");
            } else if (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
                day = 1;
                tag.setText("0" + day + "");
            } else if (day == 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
                day = 1;
                tag.setText("0" + day + "");
            } else if (day < 9) {
                day = day + 1;
                tag.setText("0" + day + "");
            } else {
                day = day + 1;
                tag.setText(day + "");
            }
        }
        if (view == hochmonat) {
            if (month == 12) {
                month = 1;
                monat.setText("0" + month + "");
            } else if (month < 9) {
                month = month + 1;
                monat.setText("0" + month + "");
            } else {
                month = month + 1;
                monat.setText(month + "");
            }
        }
        if (view == hochjahr) {
            year = year + 1;
            jahr.setText(year + "");
        }
        if (view == runtertag) {
            if (day == 1 && month == 2 && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
                day = 29;
                tag.setText(day + "");
            } else if (day == 1 && month == 2 && (year % 4 != 0 && (year % 100 == 0 || year % 400 != 0))) {
                day = 28;
                tag.setText(day + "");
            } else if (day == 1 && (month == 4 || month == 6 || month == 9 || month == 11)) {
                day = 30;
                tag.setText(day + "");
            } else if (day == 1 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
                day = 31;
                tag.setText(day + "");
            } else if (day < 11) {
                day = day - 1;
                tag.setText("0" + day + "");
            } else {
                day = day - 1;
                tag.setText(day + "");
            }
        }
        if (view == runtermonat) {
            if (month == 1) {
                month = 12;
                monat.setText(month + "");
            } else if (month < 11) {
                month = month - 1;
                monat.setText("0" + month + "");
            } else {
                month = month - 1;
                monat.setText(month + "");
            }
        }
        if (view == runterjahr) {
            year = year - 1;
            jahr.setText(year + "");
        }
        if (view == button) {
            all = tag.getText().toString() + monat.getText().toString() + jahr.getText().toString();

            Intent mintent = new Intent(HomeActivity.this, SecondActivity.class);
            mintent.putExtra("key", all);
            startActivity(mintent);
        }
    }
}