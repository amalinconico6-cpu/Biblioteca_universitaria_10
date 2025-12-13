/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.utenti;

import java.util.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author aless
 */
public class Lista_UtentiTest {
    private Lista_Utenti utenti;
    private Utente utente;
    private String nome="Girolamo";
    private String cognome="SavonarolaJr";
    
    private String Matricola="0612705555";
    private String mail="g.savonarola@studenti.unisa.it";
    
    @BeforeEach
    public void setUp() {
        utenti=new Lista_Utenti();
        utenti.aggiungiUtente(new Utente("Pippo","Pappolo","0162708899","p.pappolo@studenti.unisa.it"));
    }


    /**
     * Test of aggiungiUtente method, of class Lista_Utenti.
     */
    @org.junit.jupiter.api.Test
    public void testAggiungiUtente() {
        assertEquals("Utente aggiunto correttamente", utenti.aggiungiUtente(new Utente("Pippo","Pappolo","0162708898","p.pappolo@studenti.unisa.it")));
        assertEquals("Nome non valido", utenti.aggiungiUtente(new Utente(null,"SavonarolaJr","0612705555","g.savonarola@studenti.unisa.it")));
        assertEquals("Cognome non valido", utenti.aggiungiUtente(new Utente("Girolamo",null,"0612705555","g.savonarola@studenti.unisa.it")));
        assertEquals("Matricola non valida", utenti.aggiungiUtente(new Utente("Girolamo","SavonarolaJr",null,"g.savonarola@studenti.unisa.it")));
        assertEquals("Email non valida", utenti.aggiungiUtente(new Utente("Girolamo","SavonarolaJr","0612705555",null)));
        assertEquals("Esiste già un utente con questa matricola", utenti.aggiungiUtente(new Utente("Pippo","Pappolo","0162708899","p.pappolo@studenti.unisa.it")));
    }

    /**
     * Test of rimuoviUtente method, of class Lista_Utenti.
     */
    @org.junit.jupiter.api.Test
    public void testRimuoviUtente() {
        assertEquals("Utente non valido",utenti.rimuoviUtente(null));
        assertEquals("Utente non presente in lista",utenti.rimuoviUtente(new Utente("Pippo","Pappolo","0162708888","p.pappolo@studenti.unisa.it")));
        assertEquals("Utente rimosso correttamente",utenti.rimuoviUtente(new Utente("Pippo","Pappolo","0162708899","p.pappolo@studenti.unisa.it")));
    }

    /**
     * Test of modificaUtente method, of class Lista_Utenti.
     */
    @org.junit.jupiter.api.Test
    public void testModificaUtente() {
        assertEquals(null,utenti.modificaUtente(null));
        assertEquals(new Utente("Pippo","Pappolo","0162708888","p.pappolo@studenti.unisa.it"),utenti.modificaUtente(new Utente("Pippo","Pappolo","0162708888","p.pappolo@studenti.unisa.it")));
        assertEquals("Utente rimosso correttamente",utenti.modificaUtente(new Utente("Pippo","Pappolo","0162708899","p.pappolo@studenti.unisa.it")));
    }

    /**
     * Test of getUtenti method, of class Lista_Utenti.
     */
    @org.junit.jupiter.api.Test
    public void testGetUtenti() {
        List<Utente> utentissimi=new ArrayList<>();
        utentissimi.add(new Utente("Pippo","Pappolo","0162708888","p.pappolo@studenti.unisa.it"));
        assertEquals(utentissimi,utenti.getUtenti());
    }

    /**
     * Test of setUtenti method, of class Lista_Utenti.
     */
    @org.junit.jupiter.api.Test
    public void testSetUtenti() {
        
    }

    /**
     * Test of cercaPerMatricola method, of class Lista_Utenti.
     */
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
    public void testCheckMatricola() {
        System.out.println("checkMatricola");
        String matricola = "";
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
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
    public void testLetturaUtenti() throws Exception {
        System.out.println("letturaUtenti");
        String nomefile = "";
        Lista_Utenti instance = new Lista_Utenti();
        instance.letturaUtenti(nomefile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
