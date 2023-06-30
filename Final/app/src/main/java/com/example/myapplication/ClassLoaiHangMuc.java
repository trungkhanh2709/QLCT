package com.example.myapplication;

public class ClassLoaiHangMuc {
    int maLoaiHangMuc;
    String tenLoaiHangMuc;

    public ClassLoaiHangMuc(int maLoaiHangMuc, String tenLoaiHangMuc) {
        this.maLoaiHangMuc = maLoaiHangMuc;
        this.tenLoaiHangMuc = tenLoaiHangMuc;
    }

    public ClassLoaiHangMuc() {
    }

    public int getMaLoaiHangMuc() {
        return maLoaiHangMuc;
    }

    public void setMaLoaiHangMuc(int maLoaiHangMuc) {
        this.maLoaiHangMuc = maLoaiHangMuc;
    }

    public String getTenLoaiHangMuc() {
        return tenLoaiHangMuc;
    }

    public void setTenLoaiHangMuc(String tenLoaiHangMuc) {
        this.tenLoaiHangMuc = tenLoaiHangMuc;
    }
}

