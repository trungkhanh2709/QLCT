package com.example.myapplication;

import java.math.BigDecimal;
import java.util.Date;
public class ClassGiaoDich {
    private int MaGiaoDich;
    private int MaHangMuc;
    private Date NgayGiaoDich;
    private String DienGiai;
    private BigDecimal Sotien;
    private int ViGiaoDich;

    public ClassGiaoDich(int maGiaoDich, int maHangMuc, Date ngayGiaoDich, String dienGiai, BigDecimal sotien, int viGiaoDich) {
        MaGiaoDich = maGiaoDich;
        MaHangMuc = maHangMuc;
        NgayGiaoDich = ngayGiaoDich;
        DienGiai = dienGiai;
        Sotien = sotien;
        ViGiaoDich = viGiaoDich;
    }

    public int getMaGiaoDich() {
        return MaGiaoDich;
    }

    public void setMaGiaoDich(int maGiaoDich) {
        MaGiaoDich = maGiaoDich;
    }

    public int getMaHangMuc() {
        return MaHangMuc;
    }

    public void setMaHangMuc(int maHangMuc) {
        MaHangMuc = maHangMuc;
    }

    public Date getNgayGiaoDich() {
        return NgayGiaoDich;
    }

    public void setNgayGiaoDich(Date ngayGiaoDich) {
        NgayGiaoDich = ngayGiaoDich;
    }

    public String getDienGiai() {
        return DienGiai;
    }

    public void setDienGiai(String dienGiai) {
        DienGiai = dienGiai;
    }

    public BigDecimal getSotien() {
        return Sotien;
    }

    public void setSotien(BigDecimal sotien) {
        Sotien = sotien;
    }

    public int getViGiaoDich() {
        return ViGiaoDich;
    }

    public void setViGiaoDich(int viGiaoDich) {
        ViGiaoDich = viGiaoDich;
    }
}
