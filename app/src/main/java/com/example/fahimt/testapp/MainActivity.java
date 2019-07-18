package com.example.fahimt.testapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fahimt.testapp.utils.LocaleHelper;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        Toast.makeText(this, new LocaleHelper().getLocale(this).substring(0,2), Toast.LENGTH_LONG).show();
        Log.v("Locale:", new LocaleHelper().getLocale(this));
        getSupportActionBar().setTitle(R.string.app_name);
        String name = "Fahim";
        String str = "My name is: %s";
        Toast.makeText(this, String.format(str, name), Toast.LENGTH_LONG).show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new LocaleHelper().getLocale(MainActivity.this).substring(0,2).equals("en")){
//                    LocaleHelper.setLocale(MainActivity.this, "ar");
                    Locale myLocale = new Locale("ar");
                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    Configuration conf = res.getConfiguration();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        conf.setLayoutDirection(myLocale);
                        conf.setLocale(myLocale); // API 17+ only.
                    } else {
                        conf.locale = myLocale;
                    }
                    res.updateConfiguration(conf, dm);
                    Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(refresh);
                    finish();
                }else{
//                    LocaleHelper.setLocale(MainActivity.this, "en");
                    Locale myLocale = new Locale("en");
                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    Configuration conf = res.getConfiguration();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        conf.setLayoutDirection(myLocale);
                        conf.setLocale(myLocale); // API 17+ only.
                    } else {
                        conf.locale = myLocale;
                    }
                    res.updateConfiguration(conf, dm);
                    Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(refresh);
                    finish();
                }
            }
        });
        findViewById(R.id.start_activity_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, AddViewsActivity.class));
                startActivity(new Intent(MainActivity.this, TestDynamic.class));
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
