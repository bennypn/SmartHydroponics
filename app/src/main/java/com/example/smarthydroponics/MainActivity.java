package com.example.smarthydroponics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region "Init"
        ImageButton btnProfile = findViewById(R.id.btn_profile);
        ImageButton btnInfo = findViewById(R.id.btn_information);
        ImageButton btnCam = findViewById(R.id.btn_camera);

        TextView waterPump = findViewById(R.id.txt_water_temp);
        TextView phLevel = findViewById(R.id.txt_ph_level);
        TextView plantNut = findViewById(R.id.txt_plant_nut);

        DatabaseReference transaction = FirebaseDatabase.getInstance().getReference().child("transaction");
        //endregion "Init"

        //region "GetData"
        transaction.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String wt = snapshot.child("waterTemp").getValue(Integer.class).toString();
                String pl = snapshot.child("phLevel").getValue(Integer.class).toString();
                String pn = snapshot.child("plantNutrition").getValue(Integer.class).toString();

                waterPump.setText(wt);
                phLevel.setText(pl);
                plantNut.setText(pn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //endregion "GetData"

        //region "Toolbar"
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainActivity.this,
                        PlantProfile.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainActivity.this,
                        Information.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainActivity.this,
                        PlantHeights.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        //endregion "Toolbar"
    }
}