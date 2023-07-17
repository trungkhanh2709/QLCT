package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

<<<<<<< Updated upstream:Final/app/src/main/java/com/example/myapplication/ThongKe.java
=======
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

>>>>>>> Stashed changes:Final/app/src/main/java/com/example/myapplication/FragmentChart.java
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


public class FragmentChart extends Fragment {

    SQLiteDatabase database;
    TextView txtTongSoTien;
    BarChart Chart;
<<<<<<< Updated upstream:Final/app/src/main/java/com/example/myapplication/ThongKe.java
=======
    RecyclerView reThongKe;
    ArrayList<ClassGiaoDich> classGiaoDichList;
    Custom_Adapter_Giao_Dich adapter;
String user = "user1";
    public FragmentChart() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static fragment_chi_tiet_thog_ke newInstance(int position) {
        fragment_chi_tiet_thog_ke fragment = new fragment_chi_tiet_thog_ke();
        Bundle args = new Bundle();
        args.putString("Thang", String.valueOf(position));

        fragment.setArguments(args);
        return fragment;
    }

>>>>>>> Stashed changes:Final/app/src/main/java/com/example/myapplication/FragmentChart.java
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

<<<<<<< Updated upstream:Final/app/src/main/java/com/example/myapplication/ThongKe.java
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
=======

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.deleteDatabase(getActivity());
        database = databaseHelper.getWritableDatabase();
        AddControl(view);
        showListPerMonth(database);
        AddEvent();
        GetMoney();



        // Inflate the layout for this fragment
        return view;
    }
    private void AddControl(View view){
        txtChi = view.findViewById(R.id.txtItemMoneyPerMonthChi);
        txtTongTien = view.findViewById(R.id.txttxtItemMoneyPerMonthTongTien);
        txtThu = view.findViewById(R.id.txttxtItemMoneyPerMonthThu);
        Chart = view.findViewById(R.id.barChart);
        reThongKe = view.findViewById(R.id.reThongKe);

    }
    private BigDecimal GetThu(int i, String user){
        BigDecimal thuTien = BigDecimal.ZERO;
        String month = (i < 10) ? "0" + i : String.valueOf(i);
        String posNum = "SELECT sum(sotien) FROM GiaoDich gd, HangMuc hm, LoaiHangMuc lhm,  LoaiGiaoDich lgd, account ac, vi vi" +
                " WHERE strftime('%m', ngaygiaodich) = '" + month + "' and lgd.MaLoaiGiaoDich =2 and ac.UserName = '" + user + "' " +
                "and gd.MaHangMuc = hm.MaHangMuc and hm.LoaiHangMuc = lhm.MaLoaiHangMuc and gd.ViGiaoDich = vi.MaVi  and  lgd.MaLoaiGiaoDich = lhm.MaLoaiGiaoDich  " +
                "and vi.AccountID = ac.AccountID";

        Cursor cursor = database.rawQuery(posNum, null);
        if (cursor.moveToFirst()) {
>>>>>>> Stashed changes:Final/app/src/main/java/com/example/myapplication/FragmentChart.java
            double sum = cursor.getDouble(0);
            thuTien = BigDecimal.valueOf(sum);
        }
        cursor.close();
<<<<<<< Updated upstream:Final/app/src/main/java/com/example/myapplication/ThongKe.java
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
=======

        return thuTien;
    }
    private BigDecimal GetChi(int i,String user){
        BigDecimal chiTien = BigDecimal.ZERO;
        String month = (i < 10) ? "0" + i : String.valueOf(i);
        String neNum = "SELECT sum(sotien) FROM GiaoDich gd, HangMuc hm, LoaiHangMuc lhm,  LoaiGiaoDich lgd, account ac, vi vi WHERE strftime('%m', ngaygiaodich) = '" + month + "' and lgd.MaLoaiGiaoDich =1 and ac.UserName = '" + user + "' and gd.MaHangMuc = hm.MaHangMuc and hm.LoaiHangMuc = lhm.MaLoaiHangMuc and  lgd.MaLoaiGiaoDich = lhm.MaLoaiGiaoDich and gd.ViGiaoDich = vi.MaVi  and vi.AccountID = ac.AccountID";

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
        for (int i = 0; i <= 11; i++) {
            chiTien= GetChi(i+1,user).negate();
            thuTien = GetThu(i+1,user);
            BigDecimal[] values = new BigDecimal[]{thuTien, chiTien};
            float[] floatValues = new float[values.length];
            for (int u = 0; u < values.length; u++) {
                floatValues[u] = values[u].floatValue();
            }
            entries.add(new BarEntry(i, floatValues));
        }

        setChart(entries);

    }

    public void setChart(List<BarEntry> entries){
// Tạo dataset
>>>>>>> Stashed changes:Final/app/src/main/java/com/example/myapplication/FragmentChart.java

        BarDataSet dataSet = new BarDataSet(entries, "Data Label");
        dataSet.setColors(new int[]{Color.parseColor("#FF7474"),  Color.parseColor("#38C976")}); // Màu đỏ cho cột dưới, màu xanh lá cho cột trên

        BarData barData = new BarData(dataSet);

        XAxis xAxis = Chart.getXAxis();
        xAxis.setValueFormatter(new FragmentChart.XAxisValueFormatter());

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
    public class XAxisValueFormatter extends ValueFormatter {
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
    public void onDestroy() {
        super.onDestroy();

        // Đóng kết nối với cơ sở dữ liệu khi không cần sử dụng nữa
        if (database != null) {
            database.close();
        }
    }
<<<<<<< Updated upstream:Final/app/src/main/java/com/example/myapplication/ThongKe.java
    private void AddEvent(){

    }
    private void AddControl(){
        txtTongSoTien = (TextView) findViewById(R.id.txtTongSoTien);
        Chart = findViewById(R.id.barChart);

=======
    private void showListPerMonth(SQLiteDatabase database){
        classGiaoDichList = new ArrayList<>();

        BigDecimal tienThu = BigDecimal.ZERO;
        BigDecimal tienChi= BigDecimal.ZERO;
        BigDecimal tienConLai = BigDecimal.ZERO;
        for (int i = 1; i<=12;i++){
            tienThu=GetThu(i,user);
            tienChi = GetChi(i,user);
            tienConLai= tienThu.subtract(tienChi);
            classGiaoDichList.add(new ClassGiaoDich(tienThu,tienChi,tienConLai, i));

        }
        adapter = new Custom_Adapter_Giao_Dich(getActivity(), classGiaoDichList);

        // Cấu hình RecyclerView
        reThongKe.setLayoutManager(new LinearLayoutManager(getActivity()));
        reThongKe.setAdapter(adapter);

    }
    private void AddEvent(){
        adapter.setOnItemClickListener(new Custom_Adapter_Giao_Dich.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ChiFragmentChiTietThongKe fragmentB = ChiFragmentChiTietThongKe.newInstance(position);
                // Tiến hành thay thế Fragment A bằng Fragment B
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameMain, fragmentB)
                        .commit();

            }
        });
>>>>>>> Stashed changes:Final/app/src/main/java/com/example/myapplication/FragmentChart.java
    }
}