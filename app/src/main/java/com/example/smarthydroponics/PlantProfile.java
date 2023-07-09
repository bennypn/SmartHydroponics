package com.example.smarthydroponics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PlantProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        //region "Init"
        ImageButton btnInfo = findViewById(R.id.btn_information2);
        ImageButton btnHome = findViewById(R.id.btn_home2);

        //endregion "Init"

        //region "Toolbar"
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantProfile.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantProfile.this,
                        Information.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //endregion "Toolbar"
    }
}