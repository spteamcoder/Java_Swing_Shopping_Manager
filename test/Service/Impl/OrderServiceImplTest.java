/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import Model.Order;
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
public class OrderServiceImplTest {

    public OrderServiceImplTest() {
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
     * Test of getListOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testGetListOrder() {
        System.out.println("getListOrder");
        OrderServiceImpl instance = new OrderServiceImpl();
        List<Order> result = instance.getListOrder();
        assertEquals(result.get(0).getId(), "DH001     ");
        assertEquals(result.get(1).getId(), "DH002     ");

    }

    /**
     * Test of findOrderById method, of class OrderServiceImpl.
     */
    @Test
    public void testFindOrderById() {
        System.out.println("findOrderById");
        String id = "DH001";
        OrderServiceImpl instance = new OrderServiceImpl();
        boolean expResult = false;
        boolean result = instance.findOrderById(id);
        assertEquals(expResult, result);
        
    }
    
    
    /**
     * Test of findOrderById method, of class OrderServiceImpl.
     */
    @Test
    public void testFindOrderByIdFail() {
        System.out.println("findOrderById");
        String id = "DH0011111";
        OrderServiceImpl instance = new OrderServiceImpl();
        boolean expResult = true;
        boolean result = instance.findOrderById(id);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of insertOder method, of class OrderServiceImpl.
     */
    @Test
    public void testInsertOder() {
        System.out.println("insertOder");
        Order order = new Order();
        order.setId("12311");
        OrderServiceImpl instance = new OrderServiceImpl();
        instance.insertOder(order);
    }

    /**
     * Test of updateOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testUpdateOrder() {
        System.out.println("updateOrder");
        String id = "DH005     ";
        Order order = new Order();
        order.setId("DH005     ");
        OrderServiceImpl instance = new OrderServiceImpl();
        instance.updateOrder(id, order);
        
    }

}
