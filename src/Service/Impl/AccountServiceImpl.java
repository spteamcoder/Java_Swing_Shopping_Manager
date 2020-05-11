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
import javax.swing.table.TableModel;

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

        String query = "SELECT * FROM Accounts where Username != 'Admin'";
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
                String role = result.getString(5);
                list.add(new Account(username, password, fullname, date, role));
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
    public void addAccount(Account account) {
        Connection connection = cmdb.getConnect();
        PreparedStatement pst = null;
        String sqlInsert = "INSERT INTO Accounts (UserName,PassWord,FullName,DateCreated,Role) VALUES(?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sqlInsert);
            pst.setString(1, account.getUsername());
            pst.setString(2, account.getPassword());
            pst.setString(3, account.getFullname());
            pst.setDate(4, account.getDateCreated());
            pst.setString(5, account.getRole());
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    @Override
    public void editAccout(String oldUsername, Account account) {
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();

        String sqlUpdate = "UPDATE Accounts SET UserName=?,PassWord=?,FullName=?,DateCreated=?,Role=? WHERE UserName='" + oldUsername + "'";

        try {
            pst = conn.prepareStatement(sqlUpdate);
            pst.setString(1, account.getUsername());
            pst.setString(2, account.getPassword());
            pst.setString(3, account.getFullname());
            pst.setDate(4, account.getDateCreated());
            pst.setString(5, account.getRole());
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
    public boolean findAccountByUsername(String username) {
        boolean kq = true;
        String sqlCheck = "SELECT * FROM Accounts";
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        ResultSet result = null;

        try {
            pst = conn.prepareStatement(sqlCheck);
            result = pst.executeQuery();
            while (result.next()) {
                if (username.equals(result.getString("Username").toString().trim())) {
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
    public void changePassword(String username, String password) {
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        String sqlChange = "UPDATE Accounts SET PassWord=? WHERE UserName=N'" + username + "'";
        try {
            pst = conn.prepareStatement(sqlChange);
            pst.setString(1, password);
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
    public boolean getAccountByUsernameAndPassword(String username, String password) {
        boolean kq = false;
        PreparedStatement pst = null;
        Connection conn = cmdb.getConnect();
        String sql = "SELECT * FROM Accounts WHERE UserName=? AND PassWord=?";
        ResultSet result = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            result = pst.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ignore) {
                }
            } else if (pst != null) {
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

}
