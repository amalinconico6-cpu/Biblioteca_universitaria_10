/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.prestiti;

import it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca.Lista_Libri;
import it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.utenti.Lista_Utenti;
import java.time.LocalDate;
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
public class Lista_PrestitiTest {
    
    public Lista_PrestitiTest() {
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
     * Test of getPrestiti method, of class Lista_Prestiti.
     */
    @Test
    public void testGetPrestiti() {
        System.out.println("getPrestiti");
        Lista_Prestiti instance = new Lista_Prestiti();
        List<Prestito> expResult = null;
        List<Prestito> result = instance.getPrestiti();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiungiPrestito method, of class Lista_Prestiti.
     */
    @Test
    public void testAggiungiPrestito() {
        System.out.println("aggiungiPrestito");
        Prestito p = null;
        Lista_Utenti utenti = null;
        Lista_Libri libri = null;
        Lista_Prestiti instance = new Lista_Prestiti();
        String expResult = "";
        String result = instance.aggiungiPrestito(p, utenti, libri);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rimuoviPrestito method, of class Lista_Prestiti.
     */
    @Test
    public void testRimuoviPrestito() {
        System.out.println("rimuoviPrestito");
        Prestito p = null;
        Lista_Libri libri = null;
        Lista_Prestiti instance = new Lista_Prestiti();
        String expResult = "";
        String result = instance.rimuoviPrestito(p, libri);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiornaRitardi method, of class Lista_Prestiti.
     */
    @Test
    public void testAggiornaRitardi() {
        System.out.println("aggiornaRitardi");
        Lista_Prestiti instance = new Lista_Prestiti();
        List<Prestito> expResult = null;
        List<Prestito> result = instance.aggiornaRitardi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrestiti method, of class Lista_Prestiti.
     */
    @Test
    public void testSetPrestiti() {
        System.out.println("setPrestiti");
        List<Prestito> prestiti = null;
        Lista_Prestiti instance = new Lista_Prestiti();
        instance.setPrestiti(prestiti);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPerISBN method, of class Lista_Prestiti.
     */
    @Test
    public void testCercaPerISBN() {
        System.out.println("cercaPerISBN");
        String str = "";
        Lista_Prestiti instance = new Lista_Prestiti();
        List<Prestito> expResult = null;
        List<Prestito> result = instance.cercaPerISBN(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPerID method, of class Lista_Prestiti.
     */
    @Test
    public void testCercaPerID() {
        System.out.println("cercaPerID");
        int ID = 0;
        Lista_Prestiti instance = new Lista_Prestiti();
        List<Prestito> expResult = null;
        List<Prestito> result = instance.cercaPerID(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPrestitoPerMatricola method, of class Lista_Prestiti.
     */
    @Test
    public void testCercaPrestitoPerMatricola() {
        System.out.println("cercaPrestitoPerMatricola");
        int Matricola = 0;
        Lista_Prestiti instance = new Lista_Prestiti();
        List<Prestito> expResult = null;
        List<Prestito> result = instance.cercaPrestitoPerMatricola(Matricola);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPerData method, of class Lista_Prestiti.
     */
    @Test
    public void testCercaPerData() {
        System.out.println("cercaPerData");
        LocalDate dataScadenza = null;
        Lista_Prestiti instance = new Lista_Prestiti();
        List<Prestito> expResult = null;
        List<Prestito> result = instance.cercaPerData(dataScadenza);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of OrdinaPerScadenza method, of class Lista_Prestiti.
     */
    @Test
    public void testOrdinaPerScadenza() {
        System.out.println("OrdinaPerScadenza");
        Comparator<Prestito> cmp = null;
        Lista_Prestiti instance = new Lista_Prestiti();
        instance.OrdinaPerScadenza(cmp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvataggioPrestiti method, of class Lista_Prestiti.
     */
    @Test
    public void testSalvataggioPrestiti() throws Exception {
        System.out.println("salvataggioPrestiti");
        String nomefile = "";
        Lista_Prestiti instance = new Lista_Prestiti();
        instance.salvataggioPrestiti(nomefile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of letturaPrestiti method, of class Lista_Prestiti.
     */
    @Test
    public void testLetturaPrestiti() throws Exception {
        System.out.println("letturaPrestiti");
        String nomefile = "";
        Lista_Prestiti instance = new Lista_Prestiti();
        instance.letturaPrestiti(nomefile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUtente method, of class Lista_Prestiti.
     */
    @Test
    public void testCheckUtente() {
        System.out.println("checkUtente");
        Prestito p = null;
        Lista_Prestiti instance = new Lista_Prestiti();
        boolean expResult = false;
        boolean result = instance.checkUtente(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
