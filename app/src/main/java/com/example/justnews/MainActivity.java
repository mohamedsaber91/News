package com.example.justnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justnews.Base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    protected TextView textView;
    protected Spinner langSpinner;
    protected Button lanOkButton;
    protected String lang;
    protected static String LANGUAGE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
        langSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("English")){
                    Toast.makeText(activity, "English", Toast.LENGTH_SHORT).show();
                    LanguageDataHolder.currentLanguage = "en";
                }else if (parent.getItemAtPosition(position).equals("Arabic")){
                    Toast.makeText(activity, "Arabic", Toast.LENGTH_SHORT).show();
                    LanguageDataHolder.currentLanguage = "ar";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.lan_ok_button) {
            Intent intent = new Intent(activity,HomeNews.class);
            intent.putExtra(LANGUAGE,lang);
                startActivity(intent);
        }
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        langSpinner =  findViewById(R.id.lang_spinner);
        lanOkButton =  findViewById(R.id.lan_ok_button);
        lanOkButton.setOnClickListener(MainActivity.this);
    }
}
