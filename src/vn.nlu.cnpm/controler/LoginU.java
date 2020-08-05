package vn.nlu.cnpm.controler;


import vn.nlu.cnpm.model.KhachHang;
import vn.nlu.cnpm.model.QuanLiKhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

@WebServlet("/LoginU")
public class LoginU extends HttpServlet {
    int count = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("usern");
        String password = request.getParameter("passw");
        String action = request.getParameter("action");
        String captcha = request.getParameter("captcha");
        String recaptcha = request.getParameter("re-captcha");
        KhachHang user;
        HttpSession ss = request.getSession();
        String err = null;
        Random rd = new Random();
        try {
            if (action.equals("login")) {
                QuanLiKhachHang q = new QuanLiKhachHang();
                user = q.checkLogin(username, password);
                if (user.getPass() != null) {
                    if (recaptcha == null) {
                        ss.setAttribute("user", user);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else if (captcha.equals(recaptcha)) {
                        ss.setAttribute("user", user);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else if (!captcha.equals(recaptcha)) {
                        err = "" + rd.nextInt(100000);
                        ss.setAttribute("err", err);
                        request.getRequestDispatcher("dang-nhap.jsp").forward(request, response);
                    }
                } else {
                    count++;
                    System.out.println(count);
                    if (count < 3) {
                        err = "Tên đăng nhập sai hoặc mật khẩu không đúng";
                        ss.setAttribute("err", err);
                    } else {
                        err = "" + rd.nextInt(100000);
                        ss.setAttribute("err", err);
                    }
                    request.getRequestDispatcher("dang-nhap.jsp").forward(request, response);
                }
            } else if (action.equals("logout")) {
                HttpSession hs = request.getSession();
                hs.removeAttribute("user");
                hs.removeAttribute("err");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }
}
