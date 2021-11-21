package com.example.khachhangarea_realfood.model;

import android.widget.ImageView;

import java.util.Date;

public class BaoCao {
    String IDBaoCao,IDKhachHang,IDCuaHang,lyDo,tieuDe;
    ImageView ivBaoCao;
    Date ngayBaoCao;

    public BaoCao() {
    }

    public BaoCao(String IDBaoCao, String IDKhachHang, String IDCuaHang, String lyDo, String tieuDe, ImageView ivBaoCao, Date ngayBaoCao) {
        this.IDBaoCao = IDBaoCao;
        this.IDKhachHang = IDKhachHang;
        this.IDCuaHang = IDCuaHang;
        this.lyDo = lyDo;
        this.tieuDe = tieuDe;
        this.ivBaoCao = ivBaoCao;
        this.ngayBaoCao = ngayBaoCao;
    }

    public String getIDBaoCao() {
        return IDBaoCao;
    }

    public void setIDBaoCao(String IDBaoCao) {
        this.IDBaoCao = IDBaoCao;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public String getIDCuaHang() {
        return IDCuaHang;
    }

    public void setIDCuaHang(String IDCuaHang) {
        this.IDCuaHang = IDCuaHang;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public ImageView getIvBaoCao() {
        return ivBaoCao;
    }

    public void setIvBaoCao(ImageView ivBaoCao) {
        this.ivBaoCao = ivBaoCao;
    }

    public Date getNgayBaoCao() {
        return ngayBaoCao;
    }

    public void setNgayBaoCao(Date ngayBaoCao) {
        this.ngayBaoCao = ngayBaoCao;
    }
}
