package com.example.agro;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class LoginAdapter extends FragmentStateAdapter {
//    private Context context;
//    int totalTabs;
ArrayList<String> Strings;
    public LoginAdapter(FragmentActivity fa) {
        super(fa);
//        this.context=context;
//        this.totalTabs=totalTabs;
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                LogintagFragment logintagFragment=new LogintagFragment();
                System.out.println("ok0");
                return logintagFragment;
            case 1:

                SignuptabFragment signuptabFragment=new SignuptabFragment();
                System.out.println("ok1");
                return signuptabFragment;

            default:
                return null;

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
    public LoginAdapter(FragmentManager fragmentManager, Lifecycle lifecycle){
        super(fragmentManager, lifecycle);
        Strings=new ArrayList<>();
        Strings.add("Login");
        Strings.add("Signup");
    }
}



