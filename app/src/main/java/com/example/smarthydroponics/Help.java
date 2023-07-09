package com.example.smarthydroponics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //region "Init"
        ImageButton btnProfile = findViewById(R.id.btn_profile4);
        ImageButton btnHome = findViewById(R.id.btn_home4);
        ImageButton btnInfo = findViewById(R.id.btn_information4);
        Button btnBack = findViewById(R.id.btn_back4);

        //endregion "Init"

        //region "Toolbar"
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Help.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Help.this,
                        PlantProfile.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Help.this,
                        Information.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Help.this,
                        Information.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //endregion "Toolbar"

    }
}