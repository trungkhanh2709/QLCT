package com.example.myapplication;

public class ClassHangMuc {
    private int MaHangMuc;
    private int LoaiHangMuc;
    private String TenHangMuc;

    public ClassHangMuc(int maHangMuc, int loaiHangMuc, String tenHangMuc) {
        MaHangMuc = maHangMuc;
        LoaiHangMuc = loaiHangMuc;
        TenHangMuc = tenHangMuc;
    }

    public int getMaHangMuc() {
        return MaHangMuc;
    }

    public void setMaHangMuc(int maHangMuc) {
        MaHangMuc = maHangMuc;
    }

    public int getLoaiHangMuc() {
        return LoaiHangMuc;
    }

    public void setLoaiHangMuc(int loaiHangMuc) {
        LoaiHangMuc = loaiHangMuc;
    }

    public String getTenHangMuc() {
        return TenHangMuc;
    }

    public void setTenHangMuc(String tenHangMuc) {
        TenHangMuc = tenHangMuc;
    }
}
