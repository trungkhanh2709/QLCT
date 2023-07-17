package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChiFragmentChiTietThongKe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChiFragmentChiTietThongKe extends Fragment {
    PieChart pieChart;
    SQLiteDatabase database;
    TextView textView;
    List<String> tenLoaiHangMucList = new ArrayList<>();
    int thang;
    String user = "user1";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChiFragmentChiTietThongKe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     *
     * @return A new instance of fragment ChiFragmentChiTietThongKe.
     */
    // TODO: Rename and change types and number of parameters
    public static ChiFragmentChiTietThongKe newInstance(int param1) {
        ChiFragmentChiTietThongKe fragment = new ChiFragmentChiTietThongKe();
        Bundle args = new Bundle();
        args.putString("thang", String.valueOf(param1));

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        
    }
    private void getTenLoaiHangMucChi(int thang, String user) {

        String month = (thang < 10) ? "0" + thang : String.valueOf(thang);
        String query = "SELECT DISTINCT lhm.TenLoaiHangMuc FROM GiaoDich gd, HangMuc hm, LoaiHangMuc lhm, LoaiGiaoDich lgd , Account ac , vi vi where ac.UserName = '"+ user+ "' and strftime('%m', ngaygiaodich) = '"+month+"' and lgd.MaLoaiGiaoDich =1 AND gd.MaHangMuc = hm.MaHangMuc AND hm.LoaiHangMuc = lhm.MaLoaiHangMuc AND lgd.MaLoaiGiaoDich = lhm.MaLoaiGiaoDich and vi.AccountID = ac.AccountID";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String tenLoaiHangMuc = cursor.getString(cursor.getColumnIndex("TenLoaiHangMuc"));
                tenLoaiHangMucList.add(tenLoaiHangMuc);
            } while (cursor.moveToNext());
        }

        cursor.close();
    }
    private BigDecimal getSoTienTungHanMucChi(String TenLoaiHangMuc, int thang,String user){
        BigDecimal TongSoTien = BigDecimal.ZERO;
        String month = (thang < 10) ? "0" + thang : String.valueOf(thang);
        String querry = "select sum(Sotien) from GiaoDich gd, HangMuc hm, LoaiHangMuc lhm, LoaiGiaoDich lgd , Account ac, vi vi " +
                "where lhm.TenLoaiHangMuc='"+TenLoaiHangMuc+"' and ac.UserName = '"+ user+ "'and strftime('%m', ngaygiaodich) = '"+month+"' " +
                "and lgd.MaLoaiGiaoDich =1 and gd.ViGiaoDich = vi.MaVi and vi.AccountID = ac.AccountID and gd.MaHangMuc = hm.MaHangMuc " +
                "and hm.LoaiHangMuc = lhm.MaLoaiHangMuc and lgd.MaLoaiGiaoDich = lhm.MaLoaiGiaoDich  GROUP BY lhm.TenLoaiHangMuc";
        Cursor cursor = database.rawQuery(querry, null);
        if (cursor.moveToFirst()) {
            double sum = cursor.getDouble(0);
            TongSoTien = BigDecimal.valueOf(sum);

        }
        cursor.close();
        return TongSoTien;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
       databaseHelper.deleteDatabase(getActivity());
        database = databaseHelper.getWritableDatabase();
        View view = inflater.inflate(R.layout.fragment_chi_chi_tiet_thong_ke, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            thang = Integer.parseInt(bundle.getString("thang"));
            thang+=1;
        }


        pieChart = view.findViewById(R.id.pieChart);
        textView = view.findViewById(R.id.tongSoTienDaChiChiTietThongKe);

        getTenLoaiHangMucChi(thang,user);
        textView.setText(tenLoaiHangMucList.toString());



        BigDecimal TongSoTien = BigDecimal.ZERO;

        for (int i = 0; i < tenLoaiHangMucList.size(); i++) {
            String tenLoaiHangMuc = tenLoaiHangMucList.get(i);
            TongSoTien = getSoTienTungHanMucChi(tenLoaiHangMuc,thang,user);
        }
        textView.setText(TongSoTien.toString());


        CreateCircleChart();
        return view;

    }
    private BigDecimal getTongSoTien(int thang, String user){
        BigDecimal TongSoTien = BigDecimal.ZERO;
        String month = (thang < 10) ? "0" + thang : String.valueOf(thang);
        String querry = "select sum(Sotien) from GiaoDich gd, HangMuc hm, LoaiHangMuc lhm, LoaiGiaoDich lgd , Account ac, Vi vi " +
                "where ac.UserName = '"+user+"'and strftime('%m', ngaygiaodich) =  '"+month+"' and lgd.MaLoaiGiaoDich =1 and gd.ViGiaoDich = vi.MaVi  and " +
                "  gd.MaHangMuc = hm.MaHangMuc and hm.LoaiHangMuc = lhm.MaLoaiHangMuc and lgd.MaLoaiGiaoDich = lhm.MaLoaiGiaoDich ";
        Cursor cursor = database.rawQuery(querry, null);
        if (cursor.moveToFirst()) {
            double sum = cursor.getDouble(0);
            TongSoTien = BigDecimal.valueOf(sum);

        }
        cursor.close();
        return TongSoTien;
    }

    private void CreateCircleChart(){
        List<PieEntry> entries = new ArrayList<>();
        BigDecimal TongSoTienCuaTungHangMuc = BigDecimal.ZERO;
        BigDecimal TongSoTien = BigDecimal.ZERO;

        for (int i = 0; i < tenLoaiHangMucList.size(); i++) {
            String tenLoaiHangMuc = tenLoaiHangMucList.get(i);
            TongSoTienCuaTungHangMuc = getSoTienTungHanMucChi(tenLoaiHangMuc,thang,user);
            TongSoTien = getTongSoTien(thang,user);
            BigDecimal percent = TongSoTienCuaTungHangMuc.divide(TongSoTien, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
            entries.add(new PieEntry(percent.floatValue(), tenLoaiHangMuc));
        }


        // Tạo dataSet và cấu hình các thuộc tính
        PieDataSet dataSet = new PieDataSet(entries, "Label");
        dataSet.setColors(Color.parseColor("#3B82F6"), Color.parseColor("#22C55E"), Color.parseColor("#695acd"),
                Color.parseColor("#F97316"), Color.parseColor("#EF4444"), Color.parseColor("#925acd"), Color.parseColor("#FED979"),
                Color.parseColor("#EFC744"), Color.parseColor("#83ac0a"), Color.parseColor("#EE9CB9"), Color.parseColor("#E5F21D"));
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(30f);

        // Tạo PieData và cấu hình các thuộc tính
        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieData.setValueTextSize(12f);

        // Cấu hình biểu đồ tròn
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(10f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);
        pieChart.animateY(1000);
        pieChart.invalidate();

    }




    private void AddControl(View view){
        pieChart = view.findViewById(R.id.pieChart);
    }
}