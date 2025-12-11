/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.utenti;

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
public class Lista_UtentiTest {
    
    public Lista_UtentiTest() {
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
     * Test of aggiungiUtente method, of class Lista_Utenti.
     */
    @Test
    public void testAggiungiUtente() {
        System.out.println("aggiungiUtente");
        Utente utente = null;
        Lista_Utenti instance = new Lista_Utenti();
        String expResult = "";
        String result = instance.aggiungiUtente(utente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rimuoviUtente method, of class Lista_Utenti.
     */
    @Test
    public void testRimuoviUtente() {
        System.out.println("rimuoviUtente");
        Utente utente = null;
        Lista_Utenti instance = new Lista_Utenti();
        String expResult = "";
        String result = instance.rimuoviUtente(utente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificaUtente method, of class Lista_Utenti.
     */
    @Test
    public void testModificaUtente() {
        System.out.println("modificaUtente");
        Utente utente = null;
        Lista_Utenti instance = new Lista_Utenti();
        Utente expResult = null;
        Utente result = instance.modificaUtente(utente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUtenti method, of class Lista_Utenti.
     */
    @Test
    public void testGetUtenti() {
        System.out.println("getUtenti");
        Lista_Utenti instance = new Lista_Utenti();
        List<Utente> expResult = null;
        List<Utente> result = instance.getUtenti();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUtenti method, of class Lista_Utenti.
     */
    @Test
    public void testSetUtenti() {
        System.out.println("setUtenti");
        List<Utente> utenti = null;
        Lista_Utenti instance = new Lista_Utenti();
        instance.setUtenti(utenti);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPerMatricola method, of class Lista_Utenti.
     */
    @Test
    public void testCercaPerMatricola() {
        System.out.println("cercaPerMatricola");
        String str = "";
        Lista_Utenti instance = new Lista_Utenti();
        List<Utente> expResult = null;
        List<Utente> result = instance.cercaPerMatricola(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMatricola method, of class Lista_Utenti.
     */
    @Test
    public void testCheckMatricola() {
        System.out.println("checkMatricola");
        int matricola = 0;
        Lista_Utenti instance = new Lista_Utenti();
        boolean expResult = false;
        boolean result = instance.checkMatricola(matricola);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPerNomeCognome method, of class Lista_Utenti.
     */
    @Test
    public void testCercaPerNomeCognome() {
        System.out.println("cercaPerNomeCognome");
        String str = "";
        Lista_Utenti instance = new Lista_Utenti();
        List<Utente> expResult = null;
        List<Utente> result = instance.cercaPerNomeCognome(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvataggioUtenti method, of class Lista_Utenti.
     */
    @Test
    public void testSalvataggioUtenti() throws Exception {
        System.out.println("salvataggioUtenti");
        String nomefile = "";
        Lista_Utenti instance = new Lista_Utenti();
        instance.salvataggioUtenti(nomefile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of letturaUtenti method, of class Lista_Utenti.
     */
    @Test
    public void testLetturaUtenti() throws Exception {
        System.out.println("letturaUtenti");
        String nomefile = "";
        Lista_Utenti instance = new Lista_Utenti();
        instance.letturaUtenti(nomefile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
