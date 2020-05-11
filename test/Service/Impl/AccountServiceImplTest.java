/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import Model.Account;
import java.sql.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author VLT
 */
public class AccountServiceImplTest {

    public AccountServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getListAccounts method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testGetListAccounts() {
        System.out.println("getListAccounts");
        AccountServiceImpl instance = new AccountServiceImpl();
        List<Account> result = instance.getListAccounts();
        assertEquals(result.get(0).getUsername(), "User");
        assertEquals(result.get(1).getUsername(), "tramanh96");

    }

    /**
     * Test of addAccount method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testAddAccount() {
        System.out.println("addAccount");
        Account account = null;
        AccountServiceImpl instance = new AccountServiceImpl();
        instance.addAccount(account);

    }

    /**
     * Test of editAccout method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testEditAccout() {
        System.out.println("editAccout");
        String oldUsername = "test01";
        Account account = new Account("test01", "123", "Nguyen Van Test", new Date(2020, 01, 01), "Manager");
        AccountServiceImpl instance = new AccountServiceImpl();
        instance.editAccout(oldUsername, account);

    }

    /**
     * Test of findAccountByUsername method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testExistAccountByUsername() {
        System.out.println("findAccountByUsername");
        String username = "tramanh96";
        AccountServiceImpl instance = new AccountServiceImpl();
        boolean expResult = false;
        boolean result = instance.findAccountByUsername(username);
        assertEquals(expResult, result);

    }

    /**
     * Test of findAccountByUsername method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testNotFindAccountByUsername() {
        System.out.println("findAccountByUsername");
        String username = "tramanh9116";
        AccountServiceImpl instance = new AccountServiceImpl();
        boolean expResult = true;
        boolean result = instance.findAccountByUsername(username);
        assertEquals(expResult, result);

    }

    /**
     * Test of changePassword method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testChangePassword() {
        System.out.println("changePassword");
        String username = "tramanh96";
        String password = "1234";
        AccountServiceImpl instance = new AccountServiceImpl();
        instance.changePassword(username, password);

    }

    /**
     * Test of getAccountByUsernameAndPassword method, of class
     * AccountServiceImpl.
     */
    @org.junit.Test
    public void testGetAccountByUsernameAndPasswordSuccess() {
        System.out.println("getAccountByUsernameAndPassword");
        String username = "admin";
        String password = "123";
        AccountServiceImpl instance = new AccountServiceImpl();
        boolean expResult = true;
        boolean result = instance.getAccountByUsernameAndPassword(username, password);
        assertEquals(expResult, result);

    }

    /**
     * Test of getAccountByUsernameAndPassword method, of class
     * AccountServiceImpl.
     */
    @org.junit.Test
    public void testGetAccountByUsernameAndPasswordFail() {
        System.out.println("getAccountByUsernameAndPassword");
        String username = "admin";
        String password = "1231111";
        AccountServiceImpl instance = new AccountServiceImpl();
        boolean expResult = false;
        boolean result = instance.getAccountByUsernameAndPassword(username, password);
        assertEquals(expResult, result);

    }

    // Delete data in sql so not test
    /**
     * Test of deleteAccount method, of class AccountServiceImpl.
     */
//    @org.junit.Test
//    public void testDeleteAccount() {
//        System.out.println("deleteAccount");
//        String username = "nhungoc";
//        AccountServiceImpl instance = new AccountServiceImpl();
//        instance.deleteAccount(username);
//        
//    }
}
