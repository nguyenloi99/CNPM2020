import vn.nlu.cnpm.until.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try {
            //lấy thông tin người dùng nhập vào gồm email,pass,pass nhập lại,mã xác thực
            String email = request.getParameter("email") == null ? "" : request.getParameter("email").trim();
            String pass = request.getParameter("pass") == null ? "" : request.getParameter("pass").trim();
            String repass = request.getParameter("repass") == null ? "" : request.getParameter("repass").trim();
            String maxacthuc = request.getParameter("maxacthuc") == null ? "" : request.getParameter("maxacthuc").trim();

            String errmatkhau = "";
            String errmatkhaucon = "";
            String errmail = "";
            String errmaxacnhan = "";

            boolean checkMatKhau = false;
            boolean checkMatKhauCon = false;
            boolean checkMail = false;
            boolean checkMaxacnhan = false;
            //truy xuất database
            String sql = "SELECT quenmatkhau.maxacthuc FROM quenmatkhau WHERE maxacthuc =?";
            PreparedStatement s = DBConnection.getPreparedStatement(sql);
            s.setString(1,maxacthuc);
            ResultSet rs = s.executeQuery();
            boolean exit = rs.next();

            String regexPass = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
            String regexMail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern paPass = Pattern.compile(regexPass);
            Pattern paMail = Pattern.compile(regexMail);
            paPass.matcher(pass).matches();
            paMail.matcher(email).matches();
            //kiểm tra pas có hợp lệ không
            if (pass.length() < 8) {
                errmatkhau = "Mật khẩu phải hơn 8 ký tự";
            } else if (paPass.matcher(pass).matches() == false) {
                errmatkhau = "Phải có ít nhật 1 ký tự thường,hoa,số,ký tự đặc biệt";
            } else {
                errmatkhau = "";
                checkMatKhau = true;
            }
            //kiểm tra pass có trùng khớp với pass nhập lại không
            if (pass.equals(repass) == false) {
                errmatkhaucon = "Mật khẩu không trùng khớp";
            } else {
                errmatkhaucon = "";
                checkMatKhauCon = true;
            }
            //kiểm tra email có hợp lệ không
            if (paMail.matcher(email).matches() == false) {
                errmail = "Email của bạn không hợp lê";
            } else {
                errmail = "";
                checkMail = true;
            }
            //kiểm tra mã xác thực có đúng không
            if (exit==false){
                errmaxacnhan="Mã xác thực sai";
            }else {
                errmaxacnhan="";
                checkMaxacnhan=true;
            }
            request.setAttribute("errmatkhau", errmatkhau);
            request.setAttribute("errmatkhaucon", errmatkhaucon);
            request.setAttribute("errmail", errmail);
            request.setAttribute("errmaxacnhan", errmaxacnhan);
            request.setAttribute("email",email);
            //nếu tất cả thông tin nhập vào đều hợp lệ
            if (checkMail==true&&checkMatKhau==true&&checkMatKhauCon==true&&checkMaxacnhan==true){
                //thì lưu pass mới vào database
                String query = "UPDATE \"user\" SET \"user\".pass=? WHERE \"user\".email=?";
                PreparedStatement ps = DBConnection.getPreparedStatement(query);
                ps.setString(1,pass);
                ps.setString(2,email);
                ps.executeUpdate();
                //hiển thị trang đăng nhập
                response.sendRedirect("dang-nhap.jsp");
            }else {//nếu thông tin nhập vào không hợp lệ
                //hiển thị thông báo lỗi
                request.getRequestDispatcher("/ChangePass.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}