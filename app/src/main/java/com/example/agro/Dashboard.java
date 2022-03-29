package com.example.agro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {

    private TabLayout dashboardTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initProcess();
    }

    private void initProcess() {
        dashboardTabLayout=findViewById(R.id.dashboard_tab_layout);
    }
}