/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import ConfigDB.ConnectDB;
import Model.Account;
import Service.AccountService;
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
public class AccountServiceImpl implements AccountService {

    private static List<Account> list;

    private ConnectDB cmdb = new ConnectDB();

    @Override
    public List<Account> getListAccounts() {
        list = new ArrayList<>();
        Connection connection = cmdb.getConnect();

        String query = "SELECT * FROM Accounts";
        ResultSet result = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                String username = result.getString(1);
                String password = result.getString(2);
                String fullname = result.getString(3);
                Date date = result.getDate(4);

                list.add(new Account(username, password, fullname, date));
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
