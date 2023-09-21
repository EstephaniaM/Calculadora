/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package project;

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
public class RevisorSintaxisTest {
    
    public RevisorSintaxisTest() {
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
     * Test of isSyntaxOk method, of class RevisorSintaxis.
     */
    @Test
    public void testIsSyntaxOk() {
        System.out.println("isSyntaxOk");
        RevisorSintaxis instance = new RevisorSintaxis("(())");
        boolean expResult = true;
        boolean result = instance.isSyntaxOk();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of main method, of class RevisorSintaxis.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        RevisorSintaxis.main(args);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
