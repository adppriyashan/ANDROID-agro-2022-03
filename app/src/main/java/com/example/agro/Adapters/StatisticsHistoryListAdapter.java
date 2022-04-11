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
import com.example.agro.Models.Statistic;
import com.example.agro.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StatisticsHistoryListAdapter extends RecyclerView.Adapter<StatisticsHistoryListAdapter.ViewHolder> {

    ArrayList<Statistic> dataList;
    Context context;

    public StatisticsHistoryListAdapter(Context context, ArrayList<Statistic> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView deviceHistoryDate,deviceHistoryTemp,deviceHistoryHumidity,deviceHistoryMoisture,deviceHistoryRelay,deviceHistoryFalme,deviceHistoryGas;

        public ViewHolder(View view) {
            super(view);
            deviceHistoryDate = view.findViewById(R.id.deviceHistoryDate);
            deviceHistoryTemp = view.findViewById(R.id.deviceHistoryTemp);
            deviceHistoryHumidity = view.findViewById(R.id.deviceHistoryHumidity);
            deviceHistoryMoisture = view.findViewById(R.id.deviceHistoryMoisture);
            deviceHistoryRelay = view.findViewById(R.id.deviceHistoryRelay);
            deviceHistoryFalme = view.findViewById(R.id.deviceHistoryFalme);
            deviceHistoryGas = view.findViewById(R.id.deviceHistoryGas);
        }
    }

    @NonNull
    @Override
    public StatisticsHistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_device_history_record, parent, false);
        StatisticsHistoryListAdapter.ViewHolder viewHolder = new StatisticsHistoryListAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsHistoryListAdapter.ViewHolder holder, int position) {
        Statistic statistic = dataList.get(position);

        holder.deviceHistoryDate.setText(new SimpleDateFormat("yyyy/mm/dd").format(new Date(Long.parseLong(statistic.getDate()))));
        holder.deviceHistoryTemp.setText(statistic.getTs()+"");
        holder.deviceHistoryHumidity.setText(statistic.getHs()+"%");
        holder.deviceHistoryMoisture.setText(statistic.getSms()+"%");
        holder.deviceHistoryRelay.setText(getStatus(statistic.getRs()));
        holder.deviceHistoryRelay.setTextColor(context.getColor(getStatusColor(statistic.getRs())));
        holder.deviceHistoryFalme.setText(getStatus(statistic.getFs()));
        holder.deviceHistoryFalme.setTextColor(context.getColor(getStatusColor(statistic.getFs())));
        holder.deviceHistoryGas.setText(getStatus(statistic.getGss()));
        holder.deviceHistoryGas.setTextColor(context.getColor(getStatusColor(statistic.getGss())));
    }

    private String getStatus(int status){
        return (status==1)?"ON":"OFF";
    }

    private int getStatusColor(int status){
        return (status==1)?R.color.green :R.color.red;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
