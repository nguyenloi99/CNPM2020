package vn.nlu.cnpm.model;

import vn.nlu.cnpm.until.DBConnection;
import vn.nlu.cnpm.until.MD5Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuanLiKhachHang {
    ArrayList<KhachHang> list;

    public QuanLiKhachHang() {
    }

    public ArrayList<KhachHang> getListKhachHang() throws SQLException, ClassNotFoundException {
        list= new ArrayList<>();
        String checkAcc = "SELECT * FROM \"user\"";
        PreparedStatement psA = DBConnection.getPreparedStatement(checkAcc);
        ResultSet rs = psA.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String hoVaTen = rs.getString(2);
            String email = rs.getString(3);
            String diaChi = "a";
            String sdt = rs.getString(4);
            String userName = rs.getString(5);
            String pass = rs.getString(6);
            KhachHang k = new KhachHang(hoVaTen, email, diaChi, sdt, userName, pass);
            list.add(k);
        }
        return list;
    }

    public boolean checkMail(String email) throws SQLException, ClassNotFoundException {
        for (KhachHang k : getListKhachHang()
        ) {
            if (k.email.equals(email)) {
                return false;
            }

        }
        return true;
    }

    public boolean checkAcc(String acc) throws SQLException, ClassNotFoundException {
        for (KhachHang k : getListKhachHang()) {
            if (k.userName.equalsIgnoreCase(acc)) {
                return false;
            }
        }
        return true;
    }

    public KhachHang checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        KhachHang user = new KhachHang();
        for (KhachHang k : this.getListKhachHang()
        ) {
            if (k.getUserName().equals(username) && k.getPass().equals(MD5Library.md5(password))) {
                return k;
            }
        }
        user.setUserName(username);
        user.setUserName(null);
        return user;
    }

    public boolean themKhachHangMoi(KhachHang kh) throws SQLException, ClassNotFoundException {
        return kh.save();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        QuanLiKhachHang q = new QuanLiKhachHang();
        q.getListKhachHang();
        for (KhachHang k: q.getListKhachHang()
             ) {
            System.out.println(k.userName);
        }
        System.out.println(q.checkLogin("p2078688", "123").pass);
    }

}
