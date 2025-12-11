/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.prestiti;

import java.time.LocalDate;
import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aldomalinconico
 */
public class PrestitoTest {
    
    public PrestitoTest() {
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
     * Test of getComp method, of class Prestito.
     */
    @Test
    public void testGetComp() {
        System.out.println("getComp");
        Prestito instance = null;
        Comparator<Prestito> expResult = null;
        Comparator<Prestito> result = instance.getComp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getISBN method, of class Prestito.
     */
    @Test
    public void testGetISBN() {
        System.out.println("getISBN");
        Prestito instance = null;
        String expResult = "";
        String result = instance.getISBN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setISBN method, of class Prestito.
     */
    @Test
    public void testSetISBN() {
        System.out.println("setISBN");
        String ISBN = "";
        Prestito instance = null;
        instance.setISBN(ISBN);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatricola method, of class Prestito.
     */
    @Test
    public void testGetMatricola() {
        System.out.println("getMatricola");
        Prestito instance = null;
        int expResult = 0;
        int result = instance.getMatricola();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMatricola method, of class Prestito.
     */
    @Test
    public void testSetMatricola() {
        System.out.println("setMatricola");
        int Matricola = 0;
        Prestito instance = null;
        instance.setMatricola(Matricola);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Prestito.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Prestito instance = null;
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataScadenza method, of class Prestito.
     */
    @Test
    public void testGetDataScadenza() {
        System.out.println("getDataScadenza");
        Prestito instance = null;
        LocalDate expResult = null;
        LocalDate result = instance.getDataScadenza();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Prestito.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Prestito instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataScadenza method, of class Prestito.
     */
    @Test
    public void testSetDataScadenza() {
        System.out.println("setDataScadenza");
        LocalDate dataScadenza = null;
        Prestito instance = null;
        instance.setDataScadenza(dataScadenza);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
