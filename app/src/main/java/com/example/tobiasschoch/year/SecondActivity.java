package com.example.tobiasschoch.year;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class SecondActivity extends Activity implements View.OnClickListener {

    private TextView dayssince1;
    private TextView dayse;
    private TextView anders;
    int tag = 0;
    int monat = 0;
    int jahr = 0;

    String tag2 = "";
    String monat2 = "";
    String jahr2 = "";
    String all = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();

        all = intent.getStringExtra("hi");
        if (all == null) {
            all = intent.getStringExtra("key");
        }

        dayssince1 = findViewById(R.id.dayssince1);
        dayse = findViewById(R.id.dayse);
        anders = findViewById(R.id.anders);
        anders.setOnClickListener(this);

        tag = Integer.parseInt(all.substring(0,2));
        monat = Integer.parseInt(all.substring(2,4));
        jahr = Integer.parseInt(all.substring(4));

        tag2 = all.substring(0,2);
        monat2 = all.substring(2,4);
        jahr2 = all.substring(4);

        dayssince1.setText("seit dem " + tag2 + "." + monat2 + "." + jahr2 + "");



        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        String gesamt2 = preferences2.getString("jahr", "");
        DateTime startDate = new DateTime(jahr, monat, tag, 0, 0, 0, 0);
        DateTime endDate = new DateTime();
        Days d = Days.daysBetween(startDate, endDate);
        int days = d.getDays();

        dayse.setText(days + " Tage");
    }


    @Override
    public void onClick(View view) {
        if (view == anders) {
            String a = "";

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("jahr", a);
            editor.apply();

            String all = tag2 + monat2 + jahr2;

            Intent mintent = new Intent(SecondActivity.this, HomeActivity.class);
            mintent.putExtra("key", all);
            startActivity(mintent);


        }
    }
}
