package com.example.smarthydroponics;

import static android.content.ContentValues.TAG;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlantHeights extends AppCompatActivity {
    private ImageView btnInfo, btnHome, btnProfil;
    private int mYear, mMonth, mDay;
    private List<ImageItem> originalImageItemList; // Untuk menyimpan data asli
    private List<ImageItem> filteredImageItemList; // Untuk menyimpan data yang telah difilter
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_heights);

        ImageView imageView = findViewById(R.id.imageView);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("transaction/img");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String imageUrl = dataSnapshot.getValue(String.class);
                Picasso.get()
                        .load(imageUrl)
                        .into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        btnInfo = findViewById(R.id.btn_information9);
        btnHome = findViewById(R.id.btn_home9);
        btnProfil = findViewById(R.id.btn_profile9);
        Button btnBack = findViewById(R.id.btn_back9);

        // Inisialisasi originalImageItemList sebagai ArrayList kosong
        originalImageItemList = new ArrayList<>();
        filteredImageItemList = new ArrayList<>();

        // Inisialisasi RecyclerView dan adapter seperti sebelumnya
        recyclerView = findViewById(R.id.rc9);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageAdapter = new ImageAdapter(originalImageItemList);
        recyclerView.setAdapter(imageAdapter);

        // Panggil filterAndSortData dengan tanggal "0" untuk menampilkan semua data history secara default
        filterAndSortData(0, 0, 0);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantHeights.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantHeights.this,
                        PlantProfile.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantHeights.this,
                        Information.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(PlantHeights.this,
                        MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

    private void filterAndSortData(int year, int month, int day) {
        // Mendapatkan data dari Firebase Realtime Database (Contoh)
        // Initialize RecyclerView
        DatabaseReference historyRef = FirebaseDatabase.getInstance().getReference("history/img");
        historyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                originalImageItemList.clear(); // Hapus data sebelumnya
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    ImageItem imageItem = itemSnapshot.getValue(ImageItem.class);

                    originalImageItemList.add(imageItem);
                }

                // Filter data berdasarkan tanggal yang dipilih
                if (year == 0 && month == 0 && day == 0) {
                    // Tampilkan semua data history karena tanggal yang dipilih adalah tanggal "0-0-0"
                    filteredImageItemList.clear();
                    filteredImageItemList.addAll(originalImageItemList);
                }

                Log.d("PlantHeights", "Jumlah data [originalImageItemList]: " + originalImageItemList.size());
                Log.d("PlantHeights", "Jumlah data [filteredImageItemList]: " + filteredImageItemList.size());

                if (filteredImageItemList.isEmpty()) {
                    Toast.makeText(PlantHeights.this, "Data tidak ada di database", Toast.LENGTH_SHORT).show();
                }
                // Sort data berdasarkan unixTime
                Collections.sort(filteredImageItemList, new Comparator<ImageItem>() {
                    @Override
                    public int compare(ImageItem item1, ImageItem item2) {
                        return Long.compare(item1.getUnixTime(), item2.getUnixTime());
                    }
                });

                // Perbarui data di RecyclerView
                imageAdapter.updateData(filteredImageItemList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("PlantNutHis", "Error fetching data from Firebase", databaseError.toException());
            }
        });
    }
}