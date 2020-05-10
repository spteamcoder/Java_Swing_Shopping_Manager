/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConfigDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hieutt37
 */
public class ConnectDB {

    public Connection getConnect() {
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=CuaHangDienTu?autoReconnect=true&useSSL=false";
        String username = "sa";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            //System.out.println("Connect successfully!");
        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

}
