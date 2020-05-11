/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import Model.Producer;
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
public class ProducerServiceImplTest {

    public ProducerServiceImplTest() {
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
     * Test of getListProducer method, of class ProducerServiceImpl.
     */
    @Test
    public void testGetListProducer() {
        System.out.println("getListProducer");
        ProducerServiceImpl instance = new ProducerServiceImpl();
        List<Producer> result = instance.getListProducer();
        assertEquals(result.get(0).getId(), "A001                          ");
        assertEquals(result.get(1).getId(), "J001                          ");

    }

    /**
     * Test of findProducerById method, of class ProducerServiceImpl.
     */
    @Test
    public void testFindProducerById() {
        System.out.println("findProducerById");
        String id = "A001";
        ProducerServiceImpl instance = new ProducerServiceImpl();
        boolean expResult = false;
        boolean result = instance.findProducerById(id);
        assertEquals(expResult, result);

    }
    
     /**
     * Test of findProducerById method, of class ProducerServiceImpl.
     */
    @Test
    public void testFindProducerByIdFail() {
        System.out.println("findProducerById");
        String id = "A0111101";
        ProducerServiceImpl instance = new ProducerServiceImpl();
        boolean expResult = true;
        boolean result = instance.findProducerById(id);
        assertEquals(expResult, result);

    }

    /**
     * Test of addProducer method, of class ProducerServiceImpl.
     */
    @Test
    public void testAddProducer() {
        System.out.println("addProducer");
        Producer producer = new Producer("xzx", "Phim x", "zbc", "03123123312", "azzz@gmail.com");
        ProducerServiceImpl instance = new ProducerServiceImpl();
        instance.addProducer(producer);

    }

}
