package com.example.myapplication;

public class ClassLoaiHangMuc {
    private int MaLoaiHangMuc;
    private String TenLoaiHangMuc;
    private int MaLoaiGiaoDich;

    public ClassLoaiHangMuc(int maLoaiHangMuc, String tenLoaiHangMuc, int maLoaiGiaoDich) {
        MaLoaiHangMuc = maLoaiHangMuc;
        TenLoaiHangMuc = tenLoaiHangMuc;
        MaLoaiGiaoDich = maLoaiGiaoDich;
    }

    public int getMaLoaiHangMuc() {
        return MaLoaiHangMuc;
    }

    public void setMaLoaiHangMuc(int maLoaiHangMuc) {
        MaLoaiHangMuc = maLoaiHangMuc;
    }

    public String getTenLoaiHangMuc() {
        return TenLoaiHangMuc;
    }

    public void setTenLoaiHangMuc(String tenLoaiHangMuc) {
        TenLoaiHangMuc = tenLoaiHangMuc;
    }

    public int getMaLoaiGiaoDich() {
        return MaLoaiGiaoDich;
    }

    public void setMaLoaiGiaoDich(int maLoaiGiaoDich) {
        MaLoaiGiaoDich = maLoaiGiaoDich;
    }
}

