package com.example.myapplication;

import java.math.BigDecimal;

public class ClassVi {
    int maVi,maLoaiVi;
    String tenVi;
    //không có kiểu money nên sài đỡ bigdecimal đi
    BigDecimal tienTrongVi;

    public ClassVi() {
    }

    public ClassVi(int maVi, int maLoaiVi, String tenVi, BigDecimal tienTrongVi) {
        this.maVi = maVi;
        this.maLoaiVi = maLoaiVi;
        this.tenVi = tenVi;
        this.tienTrongVi = tienTrongVi;
    }

    public int getMaVi() {
        return maVi;
    }

    public void setMaVi(int maVi) {
        this.maVi = maVi;
    }

    public int getMaLoaiVi() {
        return maLoaiVi;
    }

    public void setMaLoaiVi(int maLoaiVi) {
        this.maLoaiVi = maLoaiVi;
    }

    public String getTenVi() {
        return tenVi;
    }

    public void setTenVi(String tenVi) {
        this.tenVi = tenVi;
    }

    public BigDecimal getTienTrongVi() {
        return tienTrongVi;
    }

    public void setTienTrongVi(BigDecimal tienTrongVi) {
        this.tienTrongVi = tienTrongVi;
    }
}
