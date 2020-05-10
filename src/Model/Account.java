/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author VLT
 */
public class Account {

    private String username;

    private String password;

    private String fullname;

    private Date dateCreated;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Account(String username, String password, String fullname, Date dateCreated) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.dateCreated = dateCreated;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", password=" + password + ", fullname=" + fullname + ", dateCreated=" + dateCreated + '}';
    }

}
