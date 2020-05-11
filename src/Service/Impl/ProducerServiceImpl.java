/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import ConfigDB.ConnectDB;
import Model.Producer;
import Model.Producer;
import Service.ProducerService;
import java.sql.Connection;
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
public class ProducerServiceImpl implements ProducerService {

    private static List<Producer> list;
    private ConnectDB cmdb = new ConnectDB();

    @Override
    public List<Producer> getListProducer() {
        list = new ArrayList<>();
        Connection connection = cmdb.getConnect();

        String query = "SELECT * FROM Producer";
        ResultSet result = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                String id = result.getString(1);
                String name = result.getString(2);
                String address = result.getString(3);
                String phone = result.getString(4);
                String email = result.getString(5);

                list.add(new Producer(id, name, address, phone, email));
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
    public boolean findProducerById(String id) {
        boolean kq = true;
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        ResultSet result = null;
        String sqlCheck = "SELECT * FROM Producer";
        try {
            pst = conn.prepareStatement(sqlCheck);
            result = pst.executeQuery();
            while (result.next()) {
                if (id.equals(result.getString("ID").toString().trim())) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
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
    public void addProducer(Producer producer) {
        Connection conn = cmdb.getConnect();
        PreparedStatement pst = null;
        String sqlInsert = "INSERT INTO Producer (ID,ProducerName,Address,Phone,Email) VALUES(?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sqlInsert);
            pst.setString(1, producer.getId());
            pst.setString(2, producer.getName());
            pst.setString(3, producer.getAddress());
            pst.setString(4, producer.getPhone());
            pst.setString(5, producer.getEmail());
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
