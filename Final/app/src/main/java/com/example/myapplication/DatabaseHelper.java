package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.DoubleBuffer;

public class DatabaseHelper extends SQLiteOpenHelper {

        static final String DATABASE_NAME = "Misa_Database1.db";
        static final int DATABASE_VERSION = 1;
        public  DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Loai_Vi (MaLoaiVi INTEGER PRIMARY KEY AUTOINCREMENT, TenLoaiVi TEXT)");
            db.execSQL("CREATE TABLE Vi (MaVi INTEGER PRIMARY KEY AUTOINCREMENT, MaLoaiVi INTEGER REFERENCES Loai_Vi(MaLoaiVi), TenVi TEXT, TienTrongVi REAL)");
            db.execSQL("CREATE TABLE Account (AccountID INTEGER PRIMARY KEY AUTOINCREMENT, UserName TEXT, Email TEXT, Password TEXT, TenNguoiDung TEXT, Avatar TEXT, MaVi INTEGER REFERENCES Vi(MaVi))");
            db.execSQL("CREATE TABLE LoaiHangMuc (MaLoaiHangMuc INTEGER PRIMARY KEY AUTOINCREMENT, TenLoaiHangMuc TEXT)");
            db.execSQL("CREATE TABLE HangMuc (MaHangMuc INTEGER PRIMARY KEY AUTOINCREMENT, MaLoaiGiaoDich INTEGER REFERENCES LoaiHangMuc(MaLoaiHangMuc), TenHangMuc TEXT)");
            db.execSQL("CREATE TABLE LoaiGiaoDich (MaLoaiGiaoDich INTEGER PRIMARY KEY AUTOINCREMENT, TenLoaiGiaoDich TEXT)");
            db.execSQL("CREATE TABLE GiaoDich (MaGiaoDich INTEGER  PRIMARY KEY AUTOINCREMENT, LoaiGiaoDich INTEGER REFERENCES LoaiGiaoDich(MaLoaiGiaoDich), NgayGiaoDich DATETIME, DienGiai TEXT, Sotien REAL, ViGiaoDich INTEGER REFERENCES Vi(MaVi), MaHangMuc INTEGER REFERENCES HangMuc(MaHangMuc))");
            insertSampleData(db);
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

    private void insertSampleData(SQLiteDatabase db) {
            SQLiteDatabase database = db;
        // Chèn dữ liệu mẫu vào bảng LoaiHangMuc
        String querry = "INSERT INTO LoaiHangMuc ( TenLoaiHangMuc) VALUES ( 'Loại hạng mục 1')";
        database.execSQL(querry);
         querry = "INSERT INTO LoaiHangMuc ( TenLoaiHangMuc) VALUES ('Loại hạng mục 2')";
        database.execSQL(querry);
         querry = "INSERT INTO HangMuc ( MaLoaiGiaoDich, TenHangMuc) VALUES ( 1, 'Hạng mục 1')";
        database.execSQL(querry);
         querry = "INSERT INTO HangMuc ( MaLoaiGiaoDich, TenHangMuc) VALUES ( 2, 'Hạng mục 2')";
        database.execSQL(querry);

         querry = "INSERT INTO Account ( UserName, Email, Password, TenNguoiDung, Avatar, MaVi) VALUES ( 'user1', 'user1@example.com', 'password1', 'Người dùng 1', 'avatar1.png', 1)";
        database.execSQL(querry);
         querry = "INSERT INTO Account ( UserName, Email, Password, TenNguoiDung, Avatar, MaVi) VALUES ('user2', 'user2@example.com', 'password2', 'Người dùng 2', 'avatar2.png', 2)";
        database.execSQL(querry);
        querry = "INSERT INTO Loai_Vi ( TenLoaiVi) VALUES ( 'Loại ví 1')";
        database.execSQL(querry);
        querry = "INSERT INTO Loai_Vi ( TenLoaiVi) VALUES ( 'Loại ví 2')";
        database.execSQL(querry);
         querry = "INSERT INTO Vi ( MaLoaiVi, TenVi, TienTrongVi) VALUES ( 1, 'Ví 1', 1000.0)";
        database.execSQL(querry);
        querry = "INSERT INTO Vi ( MaLoaiVi, TenVi, TienTrongVi) VALUES ( 2, 'Ví 2', 2000.0)";
        database.execSQL(querry);

        querry = "INSERT INTO LoaiGiaoDich ( TenLoaiGiaoDich) VALUES ('Loại giao dịch 1')";
        database.execSQL(querry);
        querry = "INSERT INTO LoaiGiaoDich ( TenLoaiGiaoDich) VALUES ( 'Loại giao dịch 2')";
        database.execSQL(querry);
        querry = "INSERT INTO GiaoDich ( LoaiGiaoDich, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich, MaHangMuc) VALUES ( 1, '2023-07-01', 'Giao dịch 1', 100.0, 1, 1)";
        database.execSQL(querry);
        querry = "INSERT INTO GiaoDich ( LoaiGiaoDich, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich, MaHangMuc) VALUES ( 2, '2023-07-02', 'Giao dịch 2', 200.0, 2, 2)";
        database.execSQL(querry);

    }

}

