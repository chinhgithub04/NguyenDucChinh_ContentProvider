package com.example.nguyenducchinh_contentprovider;

public class TinNhan {
    private String tenNguoiGui;
    private String noiDung;
    private String thoiGian;

    public TinNhan(String tenNguoiGui, String noiDung, String thoiGian) {
        this.tenNguoiGui = tenNguoiGui;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
    }

    public String getTenNguoiGui() {
        return tenNguoiGui;
    }


    public String getNoiDung() {
        return noiDung;
    }

    public String getThoiGian() {
        return thoiGian;
    }

}
