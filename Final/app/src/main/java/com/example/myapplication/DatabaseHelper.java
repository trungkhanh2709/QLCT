package com.example.myapplication;

import static java.security.AccessController.getContext;

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
    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE Loai_Vi (MaLoaiVi INTEGER PRIMARY KEY AUTOINCREMENT, TenLoaiVi NVARCHAR)");
            db.execSQL("CREATE TABLE Vi (MaVi INTEGER PRIMARY KEY AUTOINCREMENT, MaLoaiVi INTEGER REFERENCES Loai_Vi(MaLoaiVi), TenVi NVARCHAR,TienTrongVi REAL, AccountID INTEGER REFERENCES Account(AccountID))");



            db.execSQL("CREATE TABLE Account (AccountID INTEGER PRIMARY KEY AUTOINCREMENT, UserName VARCHAR, Email VARCHAR, Password VARCHAR, TenNguoiDung NVARCHAR, Avatar TEXT)");
            db.execSQL("CREATE TABLE LoaiGiaoDich (MaLoaiGiaoDich INTEGER PRIMARY KEY AUTOINCREMENT, TenLoaiGiaoDich NVARCHAR)");
            db.execSQL("CREATE TABLE LoaiHangMuc (MaLoaiHangMuc INTEGER PRIMARY KEY AUTOINCREMENT, TenLoaiHangMuc NVARCHAR,MaLoaiGiaoDich INTEGER REFERENCES LoaiGiaoDich(MaLoaiGiaoDich))");
            db.execSQL("CREATE TABLE HangMuc (MaHangMuc INTEGER PRIMARY KEY AUTOINCREMENT, LoaiHangMuc INTEGER REFERENCES LoaiHangMuc(MaLoaiHangMuc), TenHangMuc NVARCHAR)");
            db.execSQL("CREATE TABLE GiaoDich (MaGiaoDich INTEGER  PRIMARY KEY AUTOINCREMENT, MaHangMuc INTEGER REFERENCES HangMuc(MaHangMuc), NgayGiaoDich DATETIME, DienGiai NVARCHAR, Sotien REAL, ViGiaoDich INTEGER REFERENCES Vi(MaVi))");
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



        // Tạo dữ liệu mẫu cho bảng Loai_Vi
        db.execSQL("INSERT INTO Loai_Vi (TenLoaiVi) VALUES ('Tiền mặt')");
        db.execSQL("INSERT INTO Loai_Vi (TenLoaiVi) VALUES ('Thẻ ngân hàng')");

// Tạo dữ liệu mẫu cho bảng Vi
        db.execSQL("INSERT INTO Vi (MaLoaiVi, TenVi, TienTrongVi,AccountID) VALUES (1, 'Ví 1', 1000.0,1)");
        db.execSQL("INSERT INTO Vi (MaLoaiVi, TenVi, TienTrongVi,AccountID) VALUES (2, 'Ví 2', 2000.0,2)");

// Tạo dữ liệu mẫu cho bảng Account
        db.execSQL("INSERT INTO Account (UserName,Password, TenNguoiDung, Avatar) VALUES ('user1', 'password1', 'Người dùng 1', 'avatar1.png')");
        db.execSQL("INSERT INTO Account (UserName,Password, TenNguoiDung, Avatar) VALUES ('user2',  'password2', 'Người dùng 2', 'avatar2.png')");

// Tạo dữ liệu mẫu cho bảng LoaiGiaoDich
        // Tạo dữ liệu mẫu cho bảng LoaiGiaoDich
        db.execSQL("INSERT INTO LoaiGiaoDich (TenLoaiGiaoDich) VALUES ('Chi')");
        db.execSQL("INSERT INTO LoaiGiaoDich (TenLoaiGiaoDich) VALUES ('Thu')");

// Tạo dữ liệu mẫu cho bảng LoaiHangMuc
        db.execSQL("INSERT INTO LoaiHangMuc (TenLoaiHangMuc, MaLoaiGiaoDich) VALUES ('Ăn uống', 1)");
        db.execSQL("INSERT INTO LoaiHangMuc (TenLoaiHangMuc, MaLoaiGiaoDich) VALUES ('Mua sắm', 1)");
        db.execSQL("INSERT INTO LoaiHangMuc (TenLoaiHangMuc, MaLoaiGiaoDich) VALUES ('Lương, thưởng', 2)");

// Tạo dữ liệu mẫu cho bảng HangMuc
        db.execSQL("INSERT INTO HangMuc (LoaiHangMuc, TenHangMuc) VALUES (1, 'Ăn sáng')");
        db.execSQL("INSERT INTO HangMuc (LoaiHangMuc, TenHangMuc) VALUES (1, 'Ăn trưa')");
        db.execSQL("INSERT INTO HangMuc (LoaiHangMuc, TenHangMuc) VALUES (1, 'Ăn tối')");
        db.execSQL("INSERT INTO HangMuc (LoaiHangMuc, TenHangMuc) VALUES (2, 'Quần áo')");
        db.execSQL("INSERT INTO HangMuc (LoaiHangMuc, TenHangMuc) VALUES (2, 'Giày dép')");
        db.execSQL("INSERT INTO HangMuc (LoaiHangMuc, TenHangMuc) VALUES (3, 'Lãnh lương')");
        db.execSQL("INSERT INTO HangMuc (LoaiHangMuc, TenHangMuc) VALUES (3, 'Được tặng')");

// Tạo dữ liệu mẫu cho bảng GiaoDich
<<<<<<< Updated upstream
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (1, '2023-07-01', 'Giao dịch 1', 100.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (2, '2023-07-02', 'Giao dịch 2', 200.0, 2)");

    }
=======
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (1, '2023-07-01', 'Giao dịch 1', 1000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (2, '2023-06-02', 'Giao dịch 2', 2000.0, 2)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (1, '2023-05-03', 'Giao dịch 3', 3000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-04-04', 'Giao dịch 4', 4000.0, 2)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (6, '2023-01-05', 'Giao dịch 5', 5000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-03-06', 'Giao dịch 6', 6000.0, 2)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (5, '2023-02-07', 'Giao dịch 7', 7000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (5, '2023-07-08', 'Giao dịch 8', 8000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (5, '2023-08-09', 'Giao dịch 9', 9000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (6, '2023-09-10', 'Giao dịch 10', 10000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-10-11', 'Giao dịch 11', 11000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (6, '2023-11-12', 'Giao dịch 12', 12000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (5, '2023-07-13', 'Giao dịch 13', 13000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (6, '2023-12-14', 'Giao dịch 14', 14000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-04-15', 'Giao dịch 15', 15000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (5, '2023-03-16', 'Giao dịch 16', 16000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (1, '2023-02-17', 'Giao dịch 17', 17000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (5, '2023-01-18', 'Giao dịch 18', 18000.0, 2)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-12-05', 'Giao dịch 19', 5000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (6, '2023-11-06', 'Giao dịch 20', 6000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-05-06', 'Giao dịch 21', 6000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (1, '2023-05-06', 'Giao dịch 21', 5000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (5, '2023-04-06', 'Giao dịch 21', 10000.0, 1)");



        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-01-19', 'Giao dịch 22', 2200.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-02-20', 'Giao dịch 23', 2300.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (3, '2023-03-21', 'Giao dịch 24', 2400.0, 2)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (4, '2023-04-22', 'Giao dịch 25', 25000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (4, '2023-05-23', 'Giao dịch 26', 26000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (4, '2023-06-24', 'Giao dịch 27', 27000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-07-25', 'Giao dịch 28', 28000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-08-26', 'Giao dịch 29', 29000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (7, '2023-09-27', 'Giao dịch 30', 30000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (6, '2023-10-28', 'Giao dịch 31', 31000.0, 1)");
        db.execSQL("INSERT INTO GiaoDich (MaHangMuc, NgayGiaoDich, DienGiai, Sotien, ViGiaoDich) VALUES (6, '2023-11-29', 'Giao dịch 32', 32000.0, 2)");
        }
>>>>>>> Stashed changes

}

