package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.apache.commons.collections4.Get;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ThongKe extends AppCompatActivity {
    SQLiteDatabase database;
    TextView txtThu,txtChi,txtTongTien;
    BarChart Chart;
    RecyclerView reThongKe;
    ArrayList<ClassGiaoDich> classGiaoDichList;
    Custom_Adapter_Giao_Dich adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_thong_ke);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.deleteDatabase(this);
        database = databaseHelper.getWritableDatabase();
        AddControl();

//        BigDecimal tongSoTien = getTongSoTien(database);
//        txtTongSoTien.setText(tongSoTien.toString());


        GetMoney();
//        showListPerMonth(database);
        showListPerMonth(database);
    }
    private void AddControl(){
        txtChi = (TextView) findViewById(R.id.txtChi);
        txtTongTien = (TextView) findViewById(R.id.txtTongTien);
        txtThu = (TextView) findViewById(R.id.txtThu);
        Chart = findViewById(R.id.barChart);
        reThongKe = findViewById(R.id.reThongKe);

    }
    private BigDecimal GetThu(int i){
        BigDecimal thuTien = BigDecimal.ZERO;
            String month = (i < 10) ? "0" + i : String.valueOf(i);
            String posNum = "SELECT sum(sotien) FROM giaodich WHERE strftime('%m', ngaygiaodich) = '" + month + "' and MaHangMuc=1";

            Cursor cursor = database.rawQuery(posNum, null);
            if (cursor.moveToFirst()) {
                double sum = cursor.getDouble(0);
                thuTien = BigDecimal.valueOf(sum);
            }
            cursor.close();

        return thuTien;
    }
    private BigDecimal GetChi(int i){
        BigDecimal chiTien = BigDecimal.ZERO;
            String month = (i < 10) ? "0" + i : String.valueOf(i);
            String neNum = "SELECT sum(sotien) FROM giaodich WHERE strftime('%m', ngaygiaodich) = '" + month + "' and MaHangMuc=2";

            Cursor cursor = database.rawQuery(neNum, null);
            if (cursor.moveToFirst()) {
                double sum = cursor.getDouble(0);
                chiTien = BigDecimal.valueOf(sum);
                chiTien.negate();
            }
            cursor.close();
        return chiTien;
    }

    private void GetMoney(){


        BigDecimal chiTien = BigDecimal.ZERO;
        BigDecimal thuTien = BigDecimal.ZERO;
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            chiTien= GetChi(i).negate();
            thuTien = GetThu(i);
            BigDecimal[] values = new BigDecimal[]{thuTien, chiTien};
            float[] floatValues = new float[values.length];
            for (int u = 0; u < values.length; u++) {
                floatValues[u] = values[u].floatValue();
            }
            entries.add(new BarEntry(i-1, floatValues));
        }

        setChart(entries);

    }
    private BigDecimal getTongSoTien(SQLiteDatabase database){

        /////Cái này chưa chắc đúng


        String querry = "SELECT SUM(Sotien) FROM GiaoDich where mahangmuc = 1";
        Cursor cursor = database.rawQuery(querry, null);
        BigDecimal tongSoTien = BigDecimal.ZERO;
        if (cursor.moveToFirst()){
            double sum = cursor.getDouble(0);
            tongSoTien = BigDecimal.valueOf(sum);
        }
        cursor.close();
        return tongSoTien;
    }
    private void setChart(List<BarEntry> entries){
// Tạo dataset

        BarDataSet dataSet = new BarDataSet(entries, "Data Label");
        dataSet.setColors(new int[]{ Color.parseColor("#38C976"),Color.parseColor("#FF7474")});
        dataSet.setValueTextColor(Color.WHITE);


        // Tạo dữ liệu biểu đồ
        BarData barData = new BarData(dataSet);

        // Cấu hình trục X
        XAxis xAxis = Chart.getXAxis();
        xAxis.setValueFormatter(new XAxisValueFormatter());

        // Cấu hình trục Y bên trái
        YAxis leftAxis = Chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.WHITE);

        // Tắt trục Y bên phải
//        Chart.getAxisRight().setEnabled(false);

        // Tắt chú giải và mô tả
        Chart.getLegend().setEnabled(false);
        Chart.getDescription().setEnabled(false);

        // Tắt khả năng tự điều chỉnh tỷ lệ biểu đồ và tương tác
        Chart.setScaleEnabled(false);
        Chart.setTouchEnabled(false);

        // Cấu hình độ tỉ mỉ và vị trí của trục X
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // Đặt màu chữ cho trục X và trục Y
        Chart.getXAxis().setTextColor(Color.WHITE);
        Chart.getAxisLeft().setTextColor(Color.WHITE);
        Chart.getAxisRight().setTextColor(Color.WHITE);

        // Áp dụng dữ liệu biểu đồ và làm mới
        Chart.setData(barData);
        Chart.invalidate();
    }
    private class XAxisValueFormatter extends ValueFormatter {
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            int intValue = (int) value;
            if (intValue >= 0 && intValue < 12) {
                return String.valueOf(intValue + 1);
            } else {
                return "";
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đóng kết nối với cơ sở dữ liệu khi không cần sử dụng nữa
        if (database != null) {
            database.close();
        }
    }
    private void showListPerMonth(SQLiteDatabase database){
        classGiaoDichList = new ArrayList<>();

        BigDecimal tienThu = BigDecimal.ZERO;
        BigDecimal tienChi= BigDecimal.ZERO;
        BigDecimal tienConLai = BigDecimal.ZERO;
        for (int i = 1; i<=12;i++){
            tienThu=GetThu(i);
            tienChi = GetChi(i);
            tienConLai= tienThu.subtract(tienChi);
            classGiaoDichList.add(new ClassGiaoDich(tienThu,tienChi,tienConLai, i));

        }
        adapter = new Custom_Adapter_Giao_Dich(this, classGiaoDichList);

        // Cấu hình RecyclerView
        reThongKe.setLayoutManager(new LinearLayoutManager(this));
        reThongKe.setAdapter(adapter);

    }
}

