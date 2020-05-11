/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service.Impl;

import Model.Account;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
        List<Account> expResult = null;
        List<Account> result = instance.getListAccounts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editAccout method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testEditAccout() {
        System.out.println("editAccout");
        String oldUsername = "";
        Account account = null;
        AccountServiceImpl instance = new AccountServiceImpl();
        instance.editAccout(oldUsername, account);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountByUsername method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testFindAccountByUsername() {
        System.out.println("findAccountByUsername");
        String username = "";
        AccountServiceImpl instance = new AccountServiceImpl();
        boolean expResult = false;
        boolean result = instance.findAccountByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePassword method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testChangePassword() {
        System.out.println("changePassword");
        String username = "";
        String password = "";
        AccountServiceImpl instance = new AccountServiceImpl();
        instance.changePassword(username, password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountByUsernameAndPassword method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testGetAccountByUsernameAndPassword() {
        System.out.println("getAccountByUsernameAndPassword");
        String username = "";
        String password = "";
        AccountServiceImpl instance = new AccountServiceImpl();
        boolean expResult = false;
        boolean result = instance.getAccountByUsernameAndPassword(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAccount method, of class AccountServiceImpl.
     */
    @org.junit.Test
    public void testDeleteAccount() {
        System.out.println("deleteAccount");
        String username = "";
        AccountServiceImpl instance = new AccountServiceImpl();
        instance.deleteAccount(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
