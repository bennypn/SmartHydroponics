package com.example.smarthydroponics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //region "Init"
        ImageButton btnProfile = findViewById(R.id.btn_profile3);
        ImageButton btnHome = findViewById(R.id.btn_home3);
        Button btnHelp = findViewById(R.id.btn_help);
        Button btnAbout = findViewById(R.id.btn_about_us);

        //endregion "Init"

        //region "Toolbar"
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Information.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Information.this,
                        PlantProfile.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Information.this,
                        Help.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Information.this,
                        About.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //endregion "Toolbar"
    }
}