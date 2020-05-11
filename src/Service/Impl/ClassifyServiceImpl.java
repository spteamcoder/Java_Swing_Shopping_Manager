/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import ConfigDB.ConnectDB;
import Model.Classify;
import Model.Classify;
import Service.ClassifyService;
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
public class ClassifyServiceImpl implements ClassifyService {

    private static List<Classify> list;
    private ConnectDB cmdb = new ConnectDB();

    @Override
    public List<Classify> getListClassify() {
        list = new ArrayList<>();
        Connection connection = cmdb.getConnect();

        String query = "SELECT * FROM Classify";
        ResultSet result = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                String id = result.getString(1);
                String name = result.getString(2);

                list.add(new Classify(id, name));
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
    public boolean findClasstifyById(String id) {
        boolean kq = true;
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        ResultSet result = null;
        String sqlCheck = "SELECT * FROM Classify";
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
    public void insertClasstify(Classify c) {
        Connection conn = cmdb.getConnect();
        PreparedStatement pst = null;
        String sqlInsert = "INSERT INTO Classify (ID,Classify) VALUES(?,?)";
        try {
            pst = conn.prepareStatement(sqlInsert);
            pst.setString(1, c.getId());
            pst.setString(2, c.getClassify());
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
