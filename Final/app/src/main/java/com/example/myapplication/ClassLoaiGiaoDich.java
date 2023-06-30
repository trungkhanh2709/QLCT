package com.example.myapplication;

public class ClassLoaiGiaoDich {
    int maLoaiGiaoDich;
    String tenLoaiGiaoDich;

    public ClassLoaiGiaoDich(int maLoaiGiaoDich, String tenLoaiGiaoDich) {
        this.maLoaiGiaoDich = maLoaiGiaoDich;
        this.tenLoaiGiaoDich = tenLoaiGiaoDich;
    }

    public ClassLoaiGiaoDich() {
    }

    public int getMaLoaiGiaoDich() {
        return maLoaiGiaoDich;
    }

    public void setMaLoaiGiaoDich(int maLoaiGiaoDich) {
        this.maLoaiGiaoDich = maLoaiGiaoDich;
    }

    public String getTenLoaiGiaoDich() {
        return tenLoaiGiaoDich;
    }

    public void setTenLoaiGiaoDich(String tenLoaiGiaoDich) {
        this.tenLoaiGiaoDich = tenLoaiGiaoDich;
    }
}
