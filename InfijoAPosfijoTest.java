/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package project;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author enzoluna
 */
public class InfijoAPosfijoTest {
    
    public InfijoAPosfijoTest() {
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
     * Test of conviertePosfijo method, of class InfijoAPosfijo.
     */
    @Test
    public void testConviertePosfijo() {
        System.out.println("conviertePosfijo");
        ArrayList<String> infijo = new ArrayList<String>();
        infijo.add("3");
        infijo.add("*");
        infijo.add("5");
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("3");
        expResult.add("5");
        expResult.add("*");
        ArrayList<String> result = InfijoAPosfijo.conviertePosfijo(infijo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of evaluaPosfijo method, of class InfijoAPosfijo.
     */
    @Test
    public void testEvaluaPosfijo() {
        System.out.println("evaluaPosfijo");
        ArrayList<String> posfijo = new ArrayList<String>();
        posfijo.add("3");
        posfijo.add("5");
        posfijo.add("*");
        double expResult = 15.0;
        double result = InfijoAPosfijo.evaluaPosfijo(posfijo);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of clearOut method, of class InfijoAPosfijo.
     */
    @Test
    public void testClearOut() {
        System.out.println("clearOut");
        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<String> expResult = new ArrayList<String>();
        ArrayList<String> result = InfijoAPosfijo.clearOut(arr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of main method, of class InfijoAPosfijo.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        InfijoAPosfijo.main(args);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
