package com.example.myapplication;

public class ClassHangMuc {
    int maHangMuc, maLoaiGiaoDich;
    String tenHangMuc;

    public ClassHangMuc(int maHangMuc, int maLoaiGiaoDich, String tenHangMuc) {
        this.maHangMuc = maHangMuc;
        this.maLoaiGiaoDich = maLoaiGiaoDich;
        this.tenHangMuc = tenHangMuc;
    }

    public ClassHangMuc() {
    }

    public int getMaHangMuc() {
        return maHangMuc;
    }

    public void setMaHangMuc(int maHangMuc) {
        this.maHangMuc = maHangMuc;
    }

    public int getMaLoaiGiaoDich() {
        return maLoaiGiaoDich;
    }

    public void setMaLoaiGiaoDich(int maLoaiGiaoDich) {
        this.maLoaiGiaoDich = maLoaiGiaoDich;
    }

    public String getTenHangMuc() {
        return tenHangMuc;
    }

    public void setTenHangMuc(String tenHangMuc) {
        this.tenHangMuc = tenHangMuc;
    }
}
