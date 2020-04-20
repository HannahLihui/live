package com.nebula.mooc.webserver.pojo;

public class DeviceLogic {
    private Integer  Device_id;
    private String Device_name;
    private String Device_type;
    private String Device_source;
    private String Device_status;
    private String Device_ip;
    private String Device_eqlocation;
    private String Contact_name;
    private String Contact_phone;
    private String Registrant_name;


    public String getRegistrant_name() {
        return Registrant_name;
    }

    public void setRegistrant_name(String Registrant_name) {
        this.Registrant_name = Registrant_name;
    }

    public Integer getDevice_id() {
        return Device_id;
    }

    public void setDevice_id(Integer Device_id) {
        this.Device_id = Device_id;
    }

    public String getDevice_name() {
        return Device_name;
    }

    public void setDevice_name(String Device_name) {
        this.Device_name = Device_name;
    }
    public String getDevice_type() {
        return Device_type;
    }

    public void setDevice_type(String Device_type) {
        this.Device_type = Device_type;
    }
    public String getDevice_source() {
        return Device_source;
    }

    public void setDevice_source(String Device_source) {
        this.Device_source = Device_source;
    }
    public String getDevice_status() {
        return Device_status;
    }

    public void setDevice_status(String Device_status) {
        this.Device_status = Device_status;
    }
    public String getDevice_ip() {
        return Device_ip;
    }

    public void setDevice_ip(String Device_ip) {
        this.Device_ip = Device_ip;
    }
    public String getDevice_eqlocation() {
        return Device_eqlocation;
    }

    public void setDevice_eqlocation(String Device_eqlocation) {
        this.Device_eqlocation = Device_eqlocation;
    }
    public String getContact_name() {
        return Contact_name;
    }

    public void setContact_name(String Contact_name) {
        this.Contact_name = Contact_name;
    }
    public String getContact_phone() {
        return Contact_phone;
    }

    public void setContact_phone(String Contact_phone) {
        this.Contact_phone = Contact_phone;
    }


    @Override
    public String toString() {
        return "DeviceLogic{" +
                "Device_id=" + Device_id +
                ", Device_name='" + Device_name + '\'' +
                ", Device_type=" + Device_type +
                ", Device_source=" + Device_source +
                ", Device_status=" + Device_status +
                ", Device_ip=" + Device_ip +
                ", Device_eqlocation=" + Device_eqlocation +
                ", Contact_name=" + Contact_name +
                ", Contact_phone=" + Contact_phone +
                ", Registrant_name='" + Registrant_name + '\'' +
                '}';
    }
}
