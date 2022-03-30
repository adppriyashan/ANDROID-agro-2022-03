package com.example.agro.Models;

public class Device {
    public String id;
    public String code;
    public String user;

    public Device() {
    }

    public Device(String code,String user) {
        this.code = code;
        this.user=user;
    }

    public Device(String id, String code,String user) {
        this.id = id;
        this.code = code;
        this.user=user;
    }
}
