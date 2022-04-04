package com.example.agro;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agro.Models.Device;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Date;
import java.util.Map;

public class Dashboard extends AppCompatActivity {

    private ConstraintLayout dashboardQrCodeLayout,dashboardDevicesLayout,dashboardStatisticsLayout,dashboardScheduleLayout;
    IntentIntegrator intentIntegrator;
    ImageView dashboardImage;
    TextView dashboardName,dashboardTime,dashboardDate;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initProcess();
    }

    private void initProcess() {
        dashboardName=findViewById(R.id.dashboardName);
        dashboardName.setText("Halo, "+((CustomUtils.userData.name.length()<10)?CustomUtils.userData.name:CustomUtils.userData.name.substring(0,10)+".."));

        dashboardImage=findViewById(R.id.dashboardImage);
        dashboardTime=findViewById(R.id.dashboardTime);
        dashboardTime.setText(java.text.DateFormat.getDateTimeInstance().format(new Date()));

        mDatabase = FirebaseDatabase.getInstance().getReference();
        dashboardQrCodeLayout=findViewById(R.id.dashboardQrCodeLayout);
        dashboardQrCodeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        Dashboard.this, Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {

                    startPairingProcess();

                }else {
                    requestPermissionLauncher.launch(
                            Manifest.permission.CAMERA );
                }
            }
        });

        dashboardDevicesLayout=findViewById(R.id.dashboardDevicesLayout);

        dashboardDevicesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Devices.class));
            }
        });

        dashboardStatisticsLayout=findViewById(R.id.dashboardStatisticsLayout);
        dashboardStatisticsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,DeviceStatistics.class));
            }
        });

        dashboardStatisticsLayout=findViewById(R.id.dashboardStatisticsLayout);
        dashboardScheduleLayout=findViewById(R.id.dashboardScheduleLayout);
    }

    private void startPairingProcess() {
        intentIntegrator = new IntentIntegrator(Dashboard.this);
        intentIntegrator.setPrompt("Focus QR section to pair new device");
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.initiateScan();
        intentIntegrator.setBeepEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Pairing Terminated", Toast.LENGTH_SHORT).show();
            } else {
                String result=intentResult.getContents();
                if(result.length()>4){
                    if(result.substring(0,4).equals("agro")){
                        mDatabase.child("devices").orderByChild("code").equalTo(result).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    Toast.makeText(Dashboard.this, "Device already paired with another user.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Device device=new Device(result,CustomUtils.loggedUser.getUid(),2);
                                    String newDeviceKey=mDatabase.child("devices").push().getKey();
                                    mDatabase.child("devices").child(newDeviceKey).setValue(device);
                                    mDatabase.child("users").child(device.user).child("devices").child(newDeviceKey).setValue(device);
                                    Toast.makeText(Dashboard.this, "Device successfully paired.", Toast.LENGTH_SHORT).show();
                                    System.gc();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }else{
                        Toast.makeText(this, "Invalid QR Code", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Invalid QR Code", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    startPairingProcess();
                }
    });
}