package com.example.smarthydroponics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<ImageItem> imageItemList;

    public ImageAdapter(List<ImageItem> imageItemList) {
        this.imageItemList = imageItemList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_layout, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageItem imageItem = imageItemList.get(position);
        String dateTimeString = convertUnixTimeToDateTime(imageItem.getUnixTime());
        holder.unixTimeTextView.setText(dateTimeString);
        // Menggunakan Picasso untuk menampilkan gambar dari URL ke ImageView
        Picasso.get()
                .load(imageItem.getValue()) // Ganti dengan URL gambar dari ImageItem
                .placeholder(R.drawable.camera_logo) // Gambar placeholder saat gambar sedang dimuat
                .error(R.drawable.baseline_error_outline_24) // Gambar default jika terjadi kesalahan
                .into(holder.valueImageView);

    }

    @Override
    public int getItemCount() {
        return imageItemList.size();
    }

    // ...
    public void updateData(List<ImageItem> newData) {
        this.imageItemList = newData;
        notifyDataSetChanged();
    }

    public String convertUnixTimeToDateTime(long unixTime) {
        // Waktu dari UnixTime dalam bentuk milidetik (ms)
        Date date = new Date(unixTime * 1000);

        // Format yang ingin Anda gunakan untuk dateTime (misalnya "dd/MM/yyyy HH:mm:ss")
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        // Mengonversi Date menjadi string dalam format yang diinginkan
        return sdf.format(date);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView unixTimeTextView;
        public ImageView valueImageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            unixTimeTextView = itemView.findViewById(R.id.unixTimeTextView2);
            valueImageView = itemView.findViewById(R.id.valueImageView);
        }
    }
}
