package connection;

import java.sql.*;

public class DBConnector {

    private static  final String URL = "jdbc:mysql://localhost:3306/schooldb";
    private static  final String USER = "root";
    private static  final String PASS = "u_s057385EEEE";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASS);
    }

}
