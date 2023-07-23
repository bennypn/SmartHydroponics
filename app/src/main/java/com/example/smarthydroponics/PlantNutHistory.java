package com.example.smarthydroponics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlantNutHistory extends AppCompatActivity {
    private ImageView btnInfo, btnHome, btnProfil;
    private int mYear, mMonth, mDay;
    private List<TableItem> originalTableItemList; // Untuk menyimpan data asli
    private List<TableItem> filteredTableItemList; // Untuk menyimpan data yang telah difilter
    private RecyclerView recyclerView;
    private TableAdapter tableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantnut_history);

        btnInfo = findViewById(R.id.btn_information6);
        btnHome = findViewById(R.id.btn_home6);
        btnProfil = findViewById(R.id.btn_profile6);
        Button btnBack = findViewById(R.id.btn_back6);

        // Inisialisasi originalTableItemList sebagai ArrayList kosong
        originalTableItemList = new ArrayList<>();
        filteredTableItemList = new ArrayList<>();

        // Inisialisasi RecyclerView dan adapter seperti sebelumnya
        recyclerView = findViewById(R.id.rc6);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tableAdapter = new TableAdapter(originalTableItemList);
        recyclerView.setAdapter(tableAdapter);

        // Panggil filterAndSortData dengan tanggal "0" untuk menampilkan semua data history secara default
        filterAndSortData(0, 0, 0);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantNutHistory.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantNutHistory.this,
                        PlantProfile.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantNutHistory.this,
                        Information.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantNutHistory.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

    private void filterAndSortData(int year, int month, int day) {
        // Mendapatkan data dari Firebase Realtime Database (Contoh)
        // Initialize RecyclerView
        DatabaseReference historyRef = FirebaseDatabase.getInstance().getReference("history/plantNutrition");
        historyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                originalTableItemList.clear(); // Hapus data sebelumnya
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    TableItem tableItem = itemSnapshot.getValue(TableItem.class);

                    originalTableItemList.add(tableItem);
                }

                // Filter data berdasarkan tanggal yang dipilih
                if (year == 0 && month == 0 && day == 0) {
                    // Tampilkan semua data history karena tanggal yang dipilih adalah tanggal "0-0-0"
                    filteredTableItemList.clear();
                    filteredTableItemList.addAll(originalTableItemList);
                }

                Log.d("PlantNutHistory", "Jumlah data [originalTableItemList]: " + originalTableItemList.size());
                Log.d("PlantNutHistory", "Jumlah data [filteredTableItemList]: " + filteredTableItemList.size());

                if (filteredTableItemList.isEmpty()) {
                    Toast.makeText(PlantNutHistory.this, "Data tidak ada di database", Toast.LENGTH_SHORT).show();
                }
                // Sort data berdasarkan unixTime
                Collections.sort(filteredTableItemList, new Comparator<TableItem>() {
                    @Override
                    public int compare(TableItem item1, TableItem item2) {
                        return Long.compare(item1.getUnixTime(), item2.getUnixTime());
                    }
                });

                // Perbarui data di RecyclerView
                tableAdapter.updateData(filteredTableItemList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("PlantNutHis", "Error fetching data from Firebase", databaseError.toException());
            }
        });
    }
}