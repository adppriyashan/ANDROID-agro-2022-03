package com.example.agro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    TextView tx;
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo=findViewById(R.id.imageView);
        tx=findViewById(R.id.textView);
        lottieAnimationView=findViewById(R.id.lottie);

        logo.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        tx.animate().translationY(-800).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(500).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,login.class);
                startActivity(i);
                finish();
            }

        },6000);
    }
}