import vn.nlu.cnpm.model.KhachHang;
import vn.nlu.cnpm.model.QuanLiKhachHang;
import vn.nlu.cnpm.until.MD5Library;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DoRegister")

public class DoRegister extends HttpServlet {


    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        //Làm chức năng đăng ký với active = 0
        // Nhập dữ liệu
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        String pass_con = request.getParameter("password_confirm");
        //  end

        QuanLiKhachHang ql = new QuanLiKhachHang();
        KhachHang kh = new KhachHang(fullname, email, "a", "123", username, MD5Library.md5(pass));
        String resultMessage = "Đã có lỗi xảy ra";
        try {
            if (ql.checkMail(kh.getEmail()) && ql.checkAcc(kh.getUserName())) {
                kh.save();
                resultMessage = "Mail xác thực đã gửi tới email " + kh.getEmail();
            } else if (!ql.checkAcc(kh.getUserName())) {
                resultMessage = "Username đã tồn tại!";
            } else {
                resultMessage = "Email đã tồn tại!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("Message", resultMessage);
        request.getRequestDispatcher("dang-ky.jsp").forward(request, response);

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    }
}