package com.example.agro.Models;

public class Device {
    public String id;
    public String code;
    public String user;
    public int status;

    public Device() {
    }

    public Device(String code,String user,int status) {
        this.code = code;
        this.user=user;
        this.status=status;
    }

    public Device(String id, String code,String user,int status) {
        this.id = id;
        this.code = code;
        this.user=user;
        this.status=status;
    }
}
