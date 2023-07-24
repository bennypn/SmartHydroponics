package com.example.smarthydroponics;

public class TableItem {

    private int value;
    private long unixTime;
    private long keterangan;

    public long getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(long keterangan) {
        this.keterangan = keterangan;
    }

    public TableItem() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }
}
