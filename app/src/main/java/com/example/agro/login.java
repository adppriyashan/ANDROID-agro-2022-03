package com.example.agro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    float v=0;
    private FragmentStateAdapter pagerAdapter;
    LoginAdapter LoginAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.viewPager);

        LoginAdapter=new LoginAdapter(getSupportFragmentManager(),getLifecycle());

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapter=new LoginAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(LoginAdapter.Strings.get(position));
            }
        }).attach();{

        }
    }

}