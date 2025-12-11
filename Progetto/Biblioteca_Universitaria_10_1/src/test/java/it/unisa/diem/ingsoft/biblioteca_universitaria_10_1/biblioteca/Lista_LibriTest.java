/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca;

import java.util.Comparator;
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
public class Lista_LibriTest {
    
    public Lista_LibriTest() {
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
     * Test of ricercaLibro method, of class Lista_Libri.
     */
    @Test
    public void testRicercaLibro() {
        System.out.println("ricercaLibro");
        Lista_Libri instance = new Lista_Libri();
        Libro expResult = null;
        Libro result = instance.ricercaLibro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiungiLibro method, of class Lista_Libri.
     */
    @Test
    public void testAggiungiLibro() {
        System.out.println("aggiungiLibro");
        Libro libro = null;
        Lista_Libri instance = new Lista_Libri();
        instance.aggiungiLibro(libro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rimuoviLibro method, of class Lista_Libri.
     */
    @Test
    public void testRimuoviLibro() {
        System.out.println("rimuoviLibro");
        Libro libro = null;
        Lista_Libri instance = new Lista_Libri();
        instance.rimuoviLibro(libro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificaLibro method, of class Lista_Libri.
     */
    @Test
    public void testModificaLibro() {
        System.out.println("modificaLibro");
        Libro libro = null;
        Lista_Libri instance = new Lista_Libri();
        instance.modificaLibro(libro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPerTitolo method, of class Lista_Libri.
     */
    @Test
    public void testCercaPerTitolo() {
        System.out.println("cercaPerTitolo");
        String str = "";
        Lista_Libri instance = new Lista_Libri();
        List<Libro> expResult = null;
        List<Libro> result = instance.cercaPerTitolo(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPerISBN method, of class Lista_Libri.
     */
    @Test
    public void testCercaPerISBN() {
        System.out.println("cercaPerISBN");
        String str = "";
        Lista_Libri instance = new Lista_Libri();
        List<Libro> expResult = null;
        List<Libro> result = instance.cercaPerISBN(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPerAutore method, of class Lista_Libri.
     */
    @Test
    public void testCercaPerAutore() {
        System.out.println("cercaPerAutore");
        String str = "";
        Lista_Libri instance = new Lista_Libri();
        List<Autore> expResult = null;
        List<Autore> result = instance.cercaPerAutore(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of autoreTrovato method, of class Lista_Libri.
     */
    @Test
    public void testAutoreTrovato() {
        System.out.println("autoreTrovato");
        List<Autore> autori = null;
        String str = "";
        Lista_Libri instance = new Lista_Libri();
        boolean expResult = false;
        boolean result = instance.autoreTrovato(autori, str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ordina method, of class Lista_Libri.
     */
    @Test
    public void testOrdina() {
        System.out.println("ordina");
        Comparator<Libro> cmp = null;
        Lista_Libri instance = new Lista_Libri();
        instance.ordina(cmp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkISBN method, of class Lista_Libri.
     */
    @Test
    public void testCheckISBN() {
        System.out.println("checkISBN");
        String ISBN = "";
        Lista_Libri instance = new Lista_Libri();
        boolean expResult = false;
        boolean result = instance.checkISBN(ISBN);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvataggioLibri method, of class Lista_Libri.
     */
    @Test
    public void testSalvataggioLibri() {
        System.out.println("salvataggioLibri");
        String nomefile = "";
        Lista_Libri instance = new Lista_Libri();
        instance.salvataggioLibri(nomefile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of letturaLibri method, of class Lista_Libri.
     */
    @Test
    public void testLetturaLibri() {
        System.out.println("letturaLibri");
        String nomefile = "";
        Lista_Libri instance = new Lista_Libri();
        Lista_Libri expResult = null;
        Lista_Libri result = instance.letturaLibri(nomefile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
