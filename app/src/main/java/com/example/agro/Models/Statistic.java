package com.example.agro.Models;

public class Statistic {
    public String id;
    public long date;
    public double ts,hs,sms;
    public int gss, rs, fs;

    public Statistic(double ts, double hs, double sms, int gss, int rs, int fs,long date) {
        this.ts = ts;
        this.hs = hs;
        this.sms = sms;
        this.gss = gss;
        this.rs = rs;
        this.fs = fs;
        this.date=date;
    }

    public Statistic(String id, double ts, double hs, double sms, int gss, int rs, int fs,long date) {
        this.id = id;
        this.ts = ts;
        this.hs = hs;
        this.sms = sms;
        this.gss = gss;
        this.rs = rs;
        this.fs = fs;
        this.date=date;
    }

    public Statistic() {
    }

    public String getId() {
        return id;
    }

    public double getTs() {
        return ts;
    }

    public double getHs() {
        return hs;
    }

    public double getSms() {
        return sms;
    }

    public int getGss() {
        return gss;
    }

    public int getRs() {
        return rs;
    }

    public int getFs() {
        return fs;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTs(double ts) {
        this.ts = ts;
    }

    public void setHs(double hs) {
        this.hs = hs;
    }

    public void setSms(double sms) {
        this.sms = sms;
    }

    public void setGss(int gss) {
        this.gss = gss;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public void setFs(int fs) {
        this.fs = fs;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
