package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.math.BigDecimal;

public class ThongKe extends AppCompatActivity {
    SQLiteDatabase database;
    TextView txtTongSoTien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_thong_ke);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();
        BigDecimal tongSoTien = getTongSoTien(database);
        AddControl();
        txtTongSoTien.setText(tongSoTien.toString());
    }
    private BigDecimal getTongSoTien(SQLiteDatabase database){
        String querry = "SELECT SUM(Sotien) FROM GiaoDich";
        Cursor cursor = database.rawQuery(querry, null);
        BigDecimal tongSoTien = BigDecimal.ZERO;
        if (cursor.moveToFirst()){
            double sum = cursor.getDouble(0);
            tongSoTien = BigDecimal.valueOf(sum);
        }
        cursor.close();
        return tongSoTien;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Đóng kết nối với cơ sở dữ liệu khi không cần sử dụng nữa
        if (database != null) {
            database.close();
        }
    }
    private void AddEvent(){

    }
    private void AddControl(){
        txtTongSoTien = (TextView) findViewById(R.id.txtTongSoTien);
    }
}