import vn.nlu.cnpm.until.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/active")
public class active extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String key = request.getParameter("key");
        String avtive = "UPDATE \"user\" SET \"user\".active=1 WHERE \"user\".email=? and \"user\".key=?";
        try {
            PreparedStatement ps = DBConnection.getPreparedStatement(avtive);
            ps.setString(1, email);
            ps.setString(2, key);
            ps.executeUpdate();
            response.sendRedirect("active.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            response.sendRedirect("index.jsp");
//            response.getWriter().println(email+" - "+key);
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}