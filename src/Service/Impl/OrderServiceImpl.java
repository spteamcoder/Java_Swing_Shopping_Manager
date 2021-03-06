/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import ConfigDB.ConnectDB;
import Model.Order;
import Service.OrderService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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

    @Override
    public boolean findOrderById(String id) {
        boolean kq = true;
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        ResultSet rs = null;
        String sqlCheck = "SELECT * FROM Orders";
        try {
            pst = conn.prepareStatement(sqlCheck);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (id.equals(rs.getString("ID").toString().trim())) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ignore) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return kq;
    }

    @Override
    public void insertOder(Order order) {
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        String sqlInsert = "INSERT INTO Orders (ID,Customer,Address,Phone,Product,Amount,Price,WarrantyPeriod,intoMoney,Date,PaymentMethods) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sqlInsert);

            pst.setString(1, order.getId());
            pst.setString(2, order.getCustomerName());
            pst.setString(3, order.getAddress());
            pst.setString(4, order.getPhone());
            pst.setString(5, order.getProduct());
            pst.setInt(6, order.getAmount());
            pst.setString(7, order.getPrice());
            pst.setString(8, order.getWarrantyPeriod());
            pst.setString(9, order.getIntoMoney());
            pst.setDate(10, order.getDate());
            pst.setString(11, order.getMethods());
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ignore) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    @Override
    public void updateOrder(String id, Order order) {
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        String sqlChange = "UPDATE Orders SET ID=?,Customer=?,Address=?,Phone=?,Product=?,Amount=?,Price=?,WarrantyPeriod=?,intoMoney=?,Date=?,PaymentMethods=? WHERE ID='" + id.trim() + "'";
        try {
            pst = conn.prepareStatement(sqlChange);

            pst.setString(1, order.getId());
            pst.setString(2, order.getCustomerName());
            pst.setString(3, order.getAddress());
            pst.setString(4, order.getPhone());
            pst.setString(5, order.getProduct());
            pst.setInt(6, order.getAmount());
            pst.setString(7, order.getPrice());
            pst.setString(8, order.getWarrantyPeriod());
            pst.setString(9, order.getIntoMoney());
            pst.setDate(10, order.getDate());
            pst.setString(11, order.getMethods());
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ignore) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

}
