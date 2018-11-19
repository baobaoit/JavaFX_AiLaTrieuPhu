/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoaltp.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BaoBao
 */
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @Column(name = "tai_khoan", nullable = false, length = 500)
    private String taiKhoan;
    @Column(name = "mat_khau", nullable = false, length = 500)
    private String matKhau;
    @Column(name = "ho_ten", nullable = false, length = 500)
    private String hoTen;
    @Column(name = "gioi_tinh", nullable = false, length = 50)
    private String gioiTinh;
    @Column(name = "nam_sinh", nullable = false)
    private int namSinh;
    @Column(name = "que_quan", nullable = false, length = 500)
    private String queQuan;
    
    public NguoiDung() {}
    
    public NguoiDung(String taiKhoan, String matKhau, String hoTen, String gioiTinh, int namSinh, String queQuan) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.queQuan = queQuan;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }
}
