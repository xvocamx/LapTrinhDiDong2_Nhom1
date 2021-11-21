package com.example.khachhangarea_realfood.model;

import com.example.khachhangarea_realfood.TrangThai.TrangThaiCuaHang;

public class CuaHang {
    String IDCuaHang;
    String TenCuaHang;
    String ChuSoHuu;
    String ThongTinChiTiet;
    String SoCMND;
    String SoDienThoai;
    String Avatar;
    String WallPaper;
    Float Rating;
    String Email;
    TrangThaiCuaHang trangThaiCuaHang;
    String DiaChi;

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public CuaHang(String IDCuaHang, String tenCuaHang, String chuSoHuu, String thongTinChiTiet, String soCMND, String soDienThoai, String avatar, String wallPaper, Float rating, String email, TrangThaiCuaHang trangThaiCuaHang, String diaChi) {
        this.IDCuaHang = IDCuaHang;
        TenCuaHang = tenCuaHang;
        ChuSoHuu = chuSoHuu;
        ThongTinChiTiet = thongTinChiTiet;
        SoCMND = soCMND;
        SoDienThoai = soDienThoai;
        Avatar = avatar;
        WallPaper = wallPaper;
        Rating = rating;
        Email = email;
        this.trangThaiCuaHang = trangThaiCuaHang;
        DiaChi = diaChi;
    }

    public CuaHang() {
    }



    public String getIDCuaHang() {
        return IDCuaHang;
    }

    public void setIDCuaHang(String IDCuaHang) {
        this.IDCuaHang = IDCuaHang;
    }

    public String getTenCuaHang() {
        return TenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        TenCuaHang = tenCuaHang;
    }

    public String getChuSoHuu() {
        return ChuSoHuu;
    }

    public void setChuSoHuu(String chuSoHuu) {
        ChuSoHuu = chuSoHuu;
    }

    public String getThongTinChiTiet() {
        return ThongTinChiTiet;
    }

    public void setThongTinChiTiet(String thongTinChiTiet) {
        ThongTinChiTiet = thongTinChiTiet;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(String soCMND) {
        SoCMND = soCMND;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getWallPaper() {
        return WallPaper;
    }

    public void setWallPaper(String wallPaper) {
        WallPaper = wallPaper;
    }

    public Float getRating() {
        return Rating;
    }

    public void setRating(Float rating) {
        Rating = rating;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public TrangThaiCuaHang getTrangThaiCuaHang() {
        return trangThaiCuaHang;
    }

    public void setTrangThaiCuaHang(TrangThaiCuaHang trangThaiCuaHang) {
        this.trangThaiCuaHang = trangThaiCuaHang;
    }
}