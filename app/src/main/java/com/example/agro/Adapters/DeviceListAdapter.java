package com.example.agro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agro.DeviceHistory;
import com.example.agro.Models.Device;
import com.example.agro.R;

import java.util.ArrayList;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder>{

    ArrayList<Device> dataList;
    Context context;

    public DeviceListAdapter(Context context,ArrayList<Device> dataList) {
        this.context=context;
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView deviceRecordTitle,deviceRecordSubTitle,deviceRecordStatus;
        public ConstraintLayout deviceRecordStatusColor,deviceRecord;
        public ViewHolder(View view) {
            super(view);
            deviceRecordTitle =  view.findViewById(R.id.deviceRecordTitle);
            deviceRecordSubTitle =  view.findViewById(R.id.deviceRecordSubTitle);
            deviceRecordStatus =  view.findViewById(R.id.deviceRecordStatus);
            deviceRecordStatusColor =  view.findViewById(R.id.deviceRecordStasusColor);
            deviceRecord = view.findViewById(R.id.deviceRecordLayour);
        }
    }

    @NonNull
    @Override
    public DeviceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_device_record, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceListAdapter.ViewHolder holder, int position) {
        Device device=dataList.get(position);
        holder.deviceRecordTitle.setText("Device "+(position+1));
        holder.deviceRecordSubTitle.setText(device.code);
        holder.deviceRecordStatus.setText((device.status==1)?"ACTIVE":"INACTIVE");
        holder.deviceRecordStatusColor.setBackground((device.status==1)? context.getDrawable(R.drawable.green_status)  :context.getDrawable(R.drawable.red_status));
        holder.deviceRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DeviceHistory.class).putExtra("device", device.id ));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
