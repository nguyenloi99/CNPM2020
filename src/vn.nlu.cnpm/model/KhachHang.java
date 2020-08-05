package vn.nlu.cnpm.model;

import vn.nlu.cnpm.until.DBConnection;
import vn.nlu.cnpm.until.sendMail;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KhachHang {
    int id;
    String hoVaTen;
    String email;
    String diaChi;
    String sdt;
    String userName;
    String pass;
    public KhachHang(){

    }

    public KhachHang(String hoVaTen, String email, String diaChi, String sdt, String userName, String pass) {
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.userName = userName;
        this.pass = pass;
    }

    public boolean save() throws SQLException, ClassNotFoundException {
        int number = (int) Math.floor(((Math.random() * 899999) + 100000));
//        String sql = "Insert into \"user\" values (null,?,?,?,?,?,?,0)";
        String sql = "Insert into \"user\" values (?,?,?,?,?,?,0)";
        PreparedStatement ps = DBConnection.getPreparedStatement(sql);
        ps.setString(1, hoVaTen);
        ps.setString(2, email);
        ps.setString(3, sdt);
        ps.setString(4, userName);
        ps.setString(5, pass);
        ps.setInt(6, number);
        ps.executeUpdate();
        sendMail.sendMailRegister(email, number+"");
        return true;
    }
    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
