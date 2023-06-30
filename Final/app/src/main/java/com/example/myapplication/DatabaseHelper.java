package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

        static final String DATABASE_NAME = "Misa_Database.db";
        static final int DATABASE_VERSION = 1;
        public  DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Loai_Vi (MaLoaiVi INTEGER PRIMARY KEY, TenLoaiVi TEXT)");
            db.execSQL("CREATE TABLE Vi (MaVi INTEGER PRIMARY KEY, MaLoaiVi INTEGER REFERENCES Loai_Vi(MaLoaiVi), TenVi TEXT, TienTrongVi REAL)");
            db.execSQL("CREATE TABLE Account (AccountID INTEGER PRIMARY KEY, UserName TEXT, Email TEXT, Password TEXT, TenNguoiDung TEXT, Avatar TEXT, MaVi INTEGER REFERENCES Vi(MaVi))");
            db.execSQL("CREATE TABLE LoaiHangMuc (MaLoaiHangMuc INTEGER PRIMARY KEY, TenLoaiHangMuc TEXT)");
            db.execSQL("CREATE TABLE HangMuc (MaHangMuc INTEGER PRIMARY KEY, MaLoaiGiaoDich INTEGER REFERENCES LoaiHangMuc(MaLoaiHangMuc), TenHangMuc TEXT)");
            db.execSQL("CREATE TABLE LoaiGiaoDich (MaLoaiGiaoDich INTEGER PRIMARY KEY, TenLoaiGiaoDich TEXT)");
            db.execSQL("CREATE TABLE GiaoDich (MaGiaoDich INTEGER PRIMARY KEY AUTOINCREMENT, LoaiGiaoDich INTEGER REFERENCES LoaiGiaoDich(MaLoaiGiaoDich), NgayGiaoDich DATETIME, DienGiai TEXT, Sotien REAL, ViGiaoDich INTEGER REFERENCES Vi(MaVi), MaHangMuc INTEGER REFERENCES HangMuc(MaHangMuc))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS GiaoDich");
            db.execSQL("DROP TABLE IF EXISTS LoaiGiaoDich");
            db.execSQL("DROP TABLE IF EXISTS HangMuc");
            db.execSQL("DROP TABLE IF EXISTS LoaiHangMuc");
            db.execSQL("DROP TABLE IF EXISTS Account");
            db.execSQL("DROP TABLE IF EXISTS Vi");
            db.execSQL("DROP TABLE IF EXISTS Loai_Vi");
            onCreate(db);
        }
    }

