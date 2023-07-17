package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameMain;
    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfrag);

        AddControl();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.chartItem){

                    loadFragment(new FragmentChart());
                    return true;
                }
                else {
                    Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        // Mặc định hiển thị fragment Home khi khởi động ứng dụng
//        showFragment(new HomeFragment());
    }
    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameMain, fragment);
        fragmentTransaction.commit();
    }
    protected  void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameMain,fragment);
        ft.commit();
    }
    public void onBackPressed() {
        // Kiểm tra fragment hiện tại trong back stack
        FragmentManager fragmentManager = getSupportFragmentManager();
        int backStackCount = fragmentManager.getBackStackEntryCount();
        if (backStackCount > 0) {
            // Nếu có fragment trong back stack, quay lại fragment trước đó
            fragmentManager.popBackStack();
        } else {
            // Nếu không có fragment trong back stack, gọi phương thức onBackPressed() của Activity để thoát ứng dụng
            super.onBackPressed();
        }
    }
    private void AddControl(){
        frameMain= (FrameLayout) findViewById(R.id.frameMain);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottomNavigationView);
    }
}