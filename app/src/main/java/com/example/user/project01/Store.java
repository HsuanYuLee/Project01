package com.example.user.project01;

/**
 * Created by USER on 2017/12/21.
 */

public class Store
{
    private int id;
    private String name;
    private String phone;
    private String address;
    private String time;
    private byte[] image;

    public Store( String name, String phone, String address, String time, byte[] image)
    {
        this(0,name, phone, address, time, image);
    }

    public Store(int id, String name, String phone, String address, String time, byte[] image)
    {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.time = time;
        this.image = image;
    }

    public void setId(int id) {this.id = id;}
    public int getId(){return id;}

    public void setName(String name) {this.name = name;}
    public String getName(){return name;}

    public void setPhone(String phone) {this.phone = phone;}
    public String getPhone(){return phone;}

    public void setAddress(String address) {this.address = address;}
    public String getAddress(){return address;}

    public void setTime(String time) {this.time = time;}
    public String gettime(){return time;}

    public byte[] getImage(){return image;}
    public void setImage(byte[] image) {this.image = image;}

}
