package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_chi_tiet_thog_ke#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_chi_tiet_thog_ke extends Fragment {
    TextView txtTongTienFragChiTiet;
    SQLiteDatabase database;
    ViewPager2 ViewPagerThongKeChiTiet;
    TabLayout TabLayoutChiTiet;
    MyPagerAdapter pagerAdapter;
    int Thang;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters


    public fragment_chi_tiet_thog_ke() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static fragment_chi_tiet_thog_ke newInstance(int param1) {
        fragment_chi_tiet_thog_ke fragment = new fragment_chi_tiet_thog_ke();
        Bundle args = new Bundle();
        args.putString("thang", String.valueOf(param1));

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        View view = inflater.inflate(R.layout.fragment_chi_tiet_thog_ke, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.deleteDatabase(getActivity());
        database = databaseHelper.getWritableDatabase();
        if (args != null) {
            String getThang = args.getString("thang");
            Thang = Integer.parseInt(getThang);

        }
        AddControl(view);
        pagerAdapter = new MyPagerAdapter(this);
        ViewPagerThongKeChiTiet.setAdapter(pagerAdapter);

        // Liên kết TabLayout với ViewPager2
        new TabLayoutMediator(TabLayoutChiTiet, ViewPagerThongKeChiTiet,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Chi");
                            break;
                        case 1:
                            tab.setText("Thu");
                            break;
                    }
                }).attach();
        AddEvent();




        return view;
    }
    private void AddControl(View view){
        ViewPagerThongKeChiTiet = view.findViewById(R.id.ViewPagerThongKeChiTiet);

        txtTongTienFragChiTiet = view.findViewById(R.id.txtTongTienFragChiTiet);
        ViewPagerThongKeChiTiet = view.findViewById(R.id.ViewPagerThongKeChiTiet);
        TabLayoutChiTiet = view.findViewById(R.id.TabLayoutChiTiet);

    }
    private void AddEvent(){
        String t = String.valueOf(Thang);
        txtTongTienFragChiTiet.setText(t);
    }
    private class MyPagerAdapter extends FragmentStateAdapter {

        public MyPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new ChiFragmentChiTietThongKe();
                case 1:
                    return new ThuFragmentChiTietThongKe();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return 2; // Số lượng fragment
        }
    }
}

