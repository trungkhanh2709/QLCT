package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ThongKe extends AppCompatActivity {
    SQLiteDatabase database;
    TextView txtTongSoTien;
    BarChart Chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_thong_ke);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        database = databaseHelper.getWritableDatabase();
        AddControl();

//        BigDecimal tongSoTien = getTongSoTien(database);
//        txtTongSoTien.setText(tongSoTien.toString());
//
//
//
//        List<BarEntry> entries = new ArrayList<>();
//        for (int i=0; i<= 12; i++){
////            entries.add( new BarEntry(i,new float[]))
//        }
//        entries.add(new BarEntry(0, new float[]{-4, 4}));  // Giá trị âm và dương
//
//        SetChart(entries);

    }
    private BigDecimal getSoTienThu(SQLiteDatabase database){
        String querry = "SELECT * FROM giaodich WHERE strftime('%Y', ngaygiaodich) = '2023' and mahangmuc =1 ";
        Cursor cursor = database.rawQuery(querry,null);
        BigDecimal thuTien = BigDecimal.ZERO;
        if (cursor.moveToFirst()){
            double sum = cursor.getDouble(0);
            thuTien = BigDecimal.valueOf(sum);
        }
        cursor.close();
        return thuTien;
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
    private void SetChart(List<BarEntry> entries){

        BarDataSet dataSet = new BarDataSet(entries, "Data Label");
        dataSet.setColors(new int[]{Color.parseColor("#FF7474"),  Color.parseColor("#38C976")}); // Màu đỏ cho cột dưới, màu xanh lá cho cột trên

        BarData barData = new BarData(dataSet);

        XAxis xAxis = Chart.getXAxis();
        xAxis.setValueFormatter(new XAxisValueFormatter());

        YAxis leftAxis = Chart.getAxisLeft();
        YAxis rightAxis = Chart.getAxisRight();

        leftAxis.setAxisMinimum(-10);
        leftAxis.setAxisMaximum(10);
        leftAxis.setDrawGridLines(false);
        rightAxis.setEnabled(false);

        Chart.getLegend().setEnabled(false);
        Chart.getDescription().setEnabled(false);
        Chart.setScaleEnabled(false);
        Chart.setTouchEnabled(false);

        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        Chart.getXAxis().setGranularity(1f);
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
    private void AddEvent(){

    }
    private void AddControl(){
        txtTongSoTien = (TextView) findViewById(R.id.txtTongSoTien);
        Chart = findViewById(R.id.barChart);

    }
}