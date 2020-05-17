/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConfigDB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hieutt37
 */
public class ConnectDB {

    public Connection getConnect() {
        String dbURL = "jdbc:sqlserver://localhost:1433;database=Managerment";
        String username = "sa";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, username, password);
//            System.out.println("Connect thành công!");
        } catch (Exception ex) {
            System.out.println("Connect thất bại!");
            ex.printStackTrace();
        }
        return conn;
    }
    
    public static final String LOCAL_DIRECTORY = "C:\\Users\\VLT\\Desktop\\SQA\\ShoppingManager\\src\\View\\";

}
