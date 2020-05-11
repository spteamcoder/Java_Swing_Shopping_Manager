/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import Model.Classify;
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
public class ClassifyServiceImplTest {

    public ClassifyServiceImplTest() {
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
     * Test of getListClassify method, of class ClassifyServiceImpl.
     */
    @Test
    public void testGetListClassify() {
        System.out.println("getListClassify");
        ClassifyServiceImpl instance = new ClassifyServiceImpl();
        List<Classify> result = instance.getListClassify();
        assertEquals(result.get(0).getId(), "BP001     ");
        assertEquals(result.get(1).getId(), "HD01      ");

    }

    /**
     * Test of findClasstifyById method, of class ClassifyServiceImpl.
     */
    @Test
    public void testFindClasstifyById() {
        System.out.println("findClasstifyById");
        String id = "xxxxxxx";
        ClassifyServiceImpl instance = new ClassifyServiceImpl();
        boolean expResult = true;
        boolean result = instance.findClasstifyById(id);
        assertEquals(expResult, result);

    }
    
     /**
     * Test of findClasstifyById method, of class ClassifyServiceImpl.
     */
    @Test
    public void testFindClasstifyByIdSuccess() {
        System.out.println("findClasstifyById");
        String id = "BP001";
        ClassifyServiceImpl instance = new ClassifyServiceImpl();
        boolean expResult = false;
        boolean result = instance.findClasstifyById(id);
        assertEquals(expResult, result);

    }

    /**
     * Test of insertClasstify method, of class ClassifyServiceImpl.
     */
    @Test
    public void testInsertClasstify() {
        System.out.println("insertClasstify");
        Classify c = new Classify("xx01", "Balo xxx");
        
        ClassifyServiceImpl instance = new ClassifyServiceImpl();
        instance.insertClasstify(c);
    }
    
    /**
     * Test of insertClasstify method, of class ClassifyServiceImpl.
     */
    @Test
    public void testInsertClasstifyFalse() {
        System.out.println("insertClasstify");
        Classify c = new Classify("BP001     ", "Balo xxx");
        
        ClassifyServiceImpl instance = new ClassifyServiceImpl();
        instance.insertClasstify(c);

    }

}
