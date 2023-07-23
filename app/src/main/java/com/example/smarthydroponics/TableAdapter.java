package com.example.smarthydroponics;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {
    private List<TableItem> tableItemList;

    public TableAdapter(List<TableItem> tableItemList) {
        this.tableItemList = tableItemList;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item_layout, parent, false);
        return new TableViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        TableItem tableItem = tableItemList.get(position);
        String dateTimeString = convertUnixTimeToDateTime(tableItem.getUnixTime());
        holder.unixTimeTextView.setText(dateTimeString);
        holder.valueTextView.setText(String.valueOf(tableItem.getValue()));

    }

    @Override
    public int getItemCount() {
        return tableItemList.size();
    }

    // ...
    public void updateData(List<TableItem> newData) {
        this.tableItemList = newData;
        notifyDataSetChanged();
    }

    public String convertUnixTimeToDateTime(long unixTime) {
        // Waktu dari UnixTime dalam bentuk milidetik (ms)
        Date date = new Date(unixTime * 1000);

        // Format yang ingin Anda gunakan untuk dateTime (misalnya "dd/MM/yyyy HH:mm:ss")
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Mengonversi Date menjadi string dalam format yang diinginkan
        return sdf.format(date);
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder {
        public TextView unixTimeTextView;
        public TextView valueTextView;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            unixTimeTextView = itemView.findViewById(R.id.unixTimeTextView);
            valueTextView = itemView.findViewById(R.id.valueTextView);
        }
    }
}