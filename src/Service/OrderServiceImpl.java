/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import ConfigDB.ConnectDB;
import Model.Order;
import Model.Order;
import Service.Impl.OrderService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VLT
 */
public class OrderServiceImpl implements OrderService {
    
    private static List<Order> list;
    private ConnectDB cmdb = new ConnectDB();
    
    @Override
    public List<Order> getListOrder() {
        list = new ArrayList<>();
        Connection connection = cmdb.getConnect();
        
        String query = "SELECT * FROM Orders";
        ResultSet result = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                String id = result.getString(1);
                String customerName = result.getString(2);
                String address = result.getString(3);
                String phone = result.getString(4);
                String product = result.getString(5);
                int amount = result.getInt(6);
                String price = result.getString(7);
                String period = result.getString(8);
                String intoMoney = result.getString(9);
                Date date = result.getDate(10);
                String methods = result.getString(11);
                
                list.add(new Order(id, customerName, address, phone, product, amount, price, period, intoMoney, date, methods));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return list;
    }
    
}
