package vn.nlu.cnpm.until;

import java.sql.*;

public class DBConnection {
    static Connection con;

    private static final String classForname = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String strConnect = "jdbc:sqlserver://team02.database.windows.net:1433;database=team;user=nguyenloi@team02;password=Thanhloi140399;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";


    public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
        if (con == null || con.isClosed()) {
            Class.forName(classForname);
            con = DriverManager.getConnection(strConnect);
        }
        return con.prepareStatement(sql);
    }

/*
    public static Statement getStatement() throws ClassNotFoundException, SQLException {
        if (con == null || con.isClosed()) {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useUnicode=true&characterEncoding=utf-8", "root", "");
        }
        return con.createStatement();
    }*/

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PreparedStatement s = getPreparedStatement("Select * from \"user\"");
        ResultSet rs = s.executeQuery();
        rs.last();
        System.out.println(rs.getString(2));

    }
}
