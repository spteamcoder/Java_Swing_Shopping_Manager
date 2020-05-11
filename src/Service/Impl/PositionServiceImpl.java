/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import ConfigDB.ConnectDB;
import Model.Position;
import Service.PositionService;
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
public class PositionServiceImpl implements PositionService {

    private static List<Position> list;
    private ConnectDB cmdb = new ConnectDB();

    @Override
    public List<Position> getListPosition() {
        list = new ArrayList<>();
        Connection connection = cmdb.getConnect();

        String query = "SELECT * FROM Position";
        ResultSet result = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                String id = result.getString(1);
                String position = result.getString(2);
                String payroll = result.getString(3);

                list.add(new Position(id, position, payroll));
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
    public boolean findPositionById(String id) {
        boolean kq = true;
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        ResultSet result = null;
        String sqlCheck = "SELECT * FROM Position";
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
    public void insertPosition(Position position) {
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        ResultSet result = null;
        String sqlInsert = "INSERT INTO Position (ID,Position,Payroll) VALUES(?,?,?)";
        try {
            pst = conn.prepareStatement(sqlInsert);
            pst.setString(1, position.getId());
            pst.setString(2, position.getPosition());
            pst.setString(3, position.getPayroll() + " " + "VND");
            pst.executeUpdate();
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
    }

}
