import vn.nlu.cnpm.until.DBConnection;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
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


@WebServlet("/doForgotPass")
public class doForgotPass extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletContext context = getServletContext();
            host = context.getInitParameter("host");
            port = context.getInitParameter("port");
            user = context.getInitParameter("user");
            pass = context.getInitParameter("pass");

            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            //4.lấy email người dùng nhập
            String email = request.getParameter("email") == null ? "" : request.getParameter("email");
            //tạo mã xác thực
            int maxacthuc = (int) Math.floor(((Math.random() * 899999) + 10000000));
            //kiểm tra email đã được đăng ký chưa
            String sqlcheckExitsMail = "SELECT \"user\".email FROM \"user\" WHERE email=?";
            String errmail = "";
            String regexMail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern paMail = Pattern.compile(regexMail);
            boolean checkMail = false;
            PreparedStatement psMail = DBConnection.getPreparedStatement(sqlcheckExitsMail);
            psMail.setString(1, email);
            ResultSet rsMail = psMail.executeQuery();
            boolean exitEmail = rsMail.next();
            psMail.setString(1, email);
            //người dùng nhập sai email
            if (paMail.matcher(email).matches() == false) {
                errmail = "Email của bạn không hợp lê";
            } else if (exitEmail == false) {
                errmail = "Email của bạn không tồn tại trong hệ thống chúng tôi";
            } else {
                errmail = "Chúng tôi đã gửi mail cho bạn";
                checkMail = true;
            }
            if (checkMail == true) {//email đã được đăng ký trong tài khoản
                //kiểm tra email có trong database forgotpass chưa
                String sqlCheckForgot = "SELECT quenmatkhau.email FROM quenmatkhau WHERE email=?";
                PreparedStatement psCheckForgot = DBConnection.getPreparedStatement(sqlCheckForgot);
                psCheckForgot.setString(1, email);
                ResultSet rsCheckForgot = psCheckForgot.executeQuery();
                boolean checkForgot = rsCheckForgot.next();
                if (checkForgot == false) {//email không có trong database
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    //thêm email vào database với mã xác thực,ngày được tạo
                    String sqlUpdateforgot = "INSERT INTO quenmatkhau(maxacthuc,email,ngay) VALUES(?,?,?)";
                    PreparedStatement psUpdateforgot = DBConnection.getPreparedStatement(sqlUpdateforgot);
                    psUpdateforgot.setInt(1, maxacthuc);
                    psUpdateforgot.setString(2, email);
                    psUpdateforgot.setDate(3, date);
                    psUpdateforgot.executeUpdate();
                    String mess = "/ChangePass.jsp" + System.lineSeparator() + "Mã xác thực của bạn là" + maxacthuc;
                    //gửi mã xác thực về mail người dùng đã nhập
                    SendMail.sendEmail(host, port, user, pass, email, mess);

                }
                if (checkForgot == true) {//email người dùng đã có trong database forgotpass
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    //update lại thông tin email đó
                    String sqlUpdateforgot = "UPDATE project.quenmatkhau SET quenmatkhau.maxacthuc=?, quenmatkhau.ngay=? WHERE quenmatkhau.email=?";
                    PreparedStatement psUpdateforgot = DBConnection.getPreparedStatement(sqlUpdateforgot);
                    psUpdateforgot.setInt(1, maxacthuc);
                    psUpdateforgot.setDate(2, date);
                    psUpdateforgot.setString(3, email);
                    psUpdateforgot.executeUpdate();
                    String mess = "/ChangePass.jsp" + System.lineSeparator() + "Mã xác thực của bạn là    " + maxacthuc;
                    //gửi mã xác thực về email người dùng nhập
                    SendMail.sendEmail(host, port, user, pass, email, mess);
                }
                response.sendRedirect("/ChangePass.jsp");

            }else {//người dùng nhập sai email trả về lỗi
                request.setAttribute("email", email);
                request.setAttribute("errmail", errmail);
                request.getRequestDispatcher("/lost-password.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}