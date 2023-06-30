package com.example.myapplication;

import java.math.BigDecimal;
import java.util.Date;

public class ClassGiaoDich {
    int maGiaoDich;
    int loaiGiaoDich;
    Date ngayGiaoDich;
    String dienGiai;
    //không có kiểu money trong Java nên t để bigdecimal đỡ vậy
    BigDecimal soTien;
    int viGiaoDich;
    int maHangMuc;

    public ClassGiaoDich(int maGiaoDich, int loaiGiaoDich, Date ngayGiaoDich, String dienGiai, BigDecimal soTien, int viGiaoDich, int maHangMuc) {
        this.maGiaoDich = maGiaoDich;
        this.loaiGiaoDich = loaiGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.dienGiai = dienGiai;
        this.soTien = soTien;
        this.viGiaoDich = viGiaoDich;
        this.maHangMuc = maHangMuc;
    }

    public int getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(int maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public int getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(int loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public Date getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(Date ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public int getViGiaoDich() {
        return viGiaoDich;
    }

    public void setViGiaoDich(int viGiaoDich) {
        this.viGiaoDich = viGiaoDich;
    }

    public int getMaHangMuc() {
        return maHangMuc;
    }

    public void setMaHangMuc(int maHangMuc) {
        this.maHangMuc = maHangMuc;
    }

    public ClassGiaoDich() {
    }
}
