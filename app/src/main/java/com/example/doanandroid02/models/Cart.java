package com.example.doanandroid02.models;

public class Cart {
    public int idsp;
    public String tensp;
    public long giasp;
    public int soluongsp;
    public String image;

    public Cart(int idsp, String tensp, long giasp, int soluongsp, String image) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluongsp = soluongsp;
        this.image = image;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getNamesp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
}
