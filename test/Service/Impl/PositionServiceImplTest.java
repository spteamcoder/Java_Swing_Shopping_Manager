/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Impl;

import Model.Position;
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
public class PositionServiceImplTest {

    public PositionServiceImplTest() {
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
     * Test of getListPosition method, of class PositionServiceImpl.
     */
    @Test
    public void testGetListPosition() {
        System.out.println("getListPosition");
        PositionServiceImpl instance = new PositionServiceImpl();
        List<Position> result = instance.getListPosition();
        assertEquals(result.get(0).getId(), "BH01                ");
        assertEquals(result.get(1).getId(), "BV01                ");

    }

    /**
     * Test of findPositionById method, of class PositionServiceImpl.
     */
    @Test
    public void testFindPositionById() {
        System.out.println("findPositionById");
        String id = "xxx";
        PositionServiceImpl instance = new PositionServiceImpl();
        boolean expResult = true;
        boolean result = instance.findPositionById(id);
        assertEquals(expResult, result);

    }
    
     /**
     * Test of findPositionById method, of class PositionServiceImpl.
     */
    @Test
    public void testFindPositionByIdFail() {
        System.out.println("findPositionById");
        String id = "BH01";
        PositionServiceImpl instance = new PositionServiceImpl();
        boolean expResult = false;
        boolean result = instance.findPositionById(id);
        assertEquals(expResult, result);

    }

    /**
     * Test of insertPosition method, of class PositionServiceImpl.
     */
    @Test
    public void testInsertPosition() {
        System.out.println("insertPosition");
        Position position = new Position("xxxx", "Chức vụ xxx", "4,444,444 VnĐ");
        PositionServiceImpl instance = new PositionServiceImpl();
        instance.insertPosition(position);

    }

}
