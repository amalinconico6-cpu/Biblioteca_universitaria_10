/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca;

import java.util.List;
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
public class LibroTest {
    
    public LibroTest() {
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
     * Test of getTitolo method, of class Libro.
     */
    @Test
    public void testGetTitolo() {
        System.out.println("getTitolo");
        Libro instance = null;
        String expResult = "";
        String result = instance.getTitolo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitolo method, of class Libro.
     */
    @Test
    public void testSetTitolo() {
        System.out.println("setTitolo");
        String titolo = "";
        Libro instance = null;
        instance.setTitolo(titolo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAutori method, of class Libro.
     */
    @Test
    public void testGetAutori() {
        System.out.println("getAutori");
        Libro instance = null;
        List<Autore> expResult = null;
        List<Autore> result = instance.getAutori();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAutori method, of class Libro.
     */
    @Test
    public void testSetAutori() {
        System.out.println("setAutori");
        List<Autore> autori = null;
        Libro instance = null;
        instance.setAutori(autori);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificaAutore method, of class Libro.
     */
    @Test
    public void testModificaAutore() {
        System.out.println("modificaAutore");
        Autore autore = null;
        Libro instance = null;
        instance.modificaAutore(autore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getISBN method, of class Libro.
     */
    @Test
    public void testGetISBN() {
        System.out.println("getISBN");
        Libro instance = null;
        String expResult = "";
        String result = instance.getISBN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setISBN method, of class Libro.
     */
    @Test
    public void testSetISBN() {
        System.out.println("setISBN");
        String ISBN = "";
        Libro instance = null;
        instance.setISBN(ISBN);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnno method, of class Libro.
     */
    @Test
    public void testGetAnno() {
        System.out.println("getAnno");
        Libro instance = null;
        int expResult = 0;
        int result = instance.getAnno();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAnno method, of class Libro.
     */
    @Test
    public void testSetAnno() {
        System.out.println("setAnno");
        int anno = 0;
        Libro instance = null;
        instance.setAnno(anno);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCopie method, of class Libro.
     */
    @Test
    public void testGetCopie() {
        System.out.println("getCopie");
        Libro instance = null;
        int expResult = 0;
        int result = instance.getCopie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCopie method, of class Libro.
     */
    @Test
    public void testSetCopie() {
        System.out.println("setCopie");
        int copie = 0;
        Libro instance = null;
        instance.setCopie(copie);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiungiAutore method, of class Libro.
     */
    @Test
    public void testAggiungiAutore() {
        System.out.println("aggiungiAutore");
        Autore a = null;
        Libro instance = null;
        instance.aggiungiAutore(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rimuoviAutore method, of class Libro.
     */
    @Test
    public void testRimuoviAutore() {
        System.out.println("rimuoviAutore");
        Autore a = null;
        Libro instance = null;
        instance.rimuoviAutore(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisponibile method, of class Libro.
     */
    @Test
    public void testGetDisponibile() {
        System.out.println("getDisponibile");
        Libro instance = null;
        boolean expResult = false;
        boolean result = instance.getDisponibile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDisponibile method, of class Libro.
     */
    @Test
    public void testSetDisponibile() {
        System.out.println("setDisponibile");
        boolean disponibile = false;
        Libro instance = null;
        instance.setDisponibile(disponibile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ordinaAutori method, of class Libro.
     */
    @Test
    public void testOrdinaAutori() {
        System.out.println("ordinaAutori");
        Libro instance = null;
        instance.ordinaAutori();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Libro.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Libro instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
