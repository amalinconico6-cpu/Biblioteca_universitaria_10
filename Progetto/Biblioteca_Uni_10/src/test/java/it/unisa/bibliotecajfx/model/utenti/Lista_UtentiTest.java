/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.bibliotecajfx.model.utenti;

import java.util.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ALESSANDRO VISCIANO
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
    @Test
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
    @Test
    public void testRimuoviUtente() {
        assertEquals("Utente non valido",utenti.rimuoviUtente(null));
        assertEquals("Utente non presente in lista",utenti.rimuoviUtente(new Utente("Pippo","Pappolo","0162708888","p.pappolo@studenti.unisa.it")));
        assertEquals("Utente rimosso correttamente",utenti.rimuoviUtente(new Utente("Pippo","Pappolo","0162708899","p.pappolo@studenti.unisa.it")));
    }

    /**
     * Test of modificaUtente method, of class Lista_Utenti.
     */
    @Test
    public void testModificaUtente() {
        assertEquals(null,utenti.modificaUtente(null));
        assertEquals(new Utente("Ciccio","Pappolo","0162708899","p.pappolo@studenti.unisa.it"),utenti.modificaUtente(new Utente("Cicco","Pappolo","0162708899","p.pappolo@studenti.unisa.it")));
        assertEquals(null,utenti.modificaUtente(new Utente("Pippo","Pappolo","0162708890","p.pappolo@studenti.unisa.it")));
    }

    /**
     * Test of getUtenti method, of class Lista_Utenti.
     */
    @Test
    public void testGetUtenti() {
        List<Utente> utentissimi=new ArrayList<>();
        utentissimi.add(new Utente("Pippo","Pappolo","0162708899","p.pappolo@studenti.unisa.it"));
        assertEquals(utentissimi,utenti.getUtenti());
    }
    /**
     * Test of cercaPerMatricola method, of class Lista_Utenti.
     */
    @Test
    public void testCercaPerMatricola() {
        List<Utente> utentissimi=new ArrayList<>();
        utentissimi.add(new Utente("Pippo","Pappolo","0162708899","p.pappolo@studenti.unisa.it"));
        assertEquals(utentissimi,utenti.cercaPerMatricola("0162708899"));
        List<Utente> utentissimi2=new ArrayList<>();
        assertEquals(utentissimi2,utenti.cercaPerMatricola(null));
    }

    /**
     * Test of checkMatricola method, of class Lista_Utenti.
     */
    @Test
    public void testCheckMatricola() {
        assertEquals(false,utenti.checkMatricola(null));
        assertEquals(false,utenti.checkMatricola(""));
        assertEquals(true,utenti.checkMatricola("0162708899"));
    }

    /**
     * Test of cercaPerNomeCognome method, of class Lista_Utenti.
     */
    @Test
    public void testCercaPerNomeCognome() {
        List<Utente> utentissimi=new ArrayList<>();
        utentissimi.add(new Utente("Pippo","Pappolo","0162708899","p.pappolo@studenti.unisa.it"));
        List<Utente> utentissimi2=new ArrayList<>();
        assertEquals(utentissimi,utenti.cercaPerNomeCognome("Pippo"));
        assertEquals(utentissimi,utenti.cercaPerNomeCognome("Pappolo"));
        assertEquals(utentissimi2,utenti.cercaPerNomeCognome(null));
        assertEquals(utentissimi2,utenti.cercaPerNomeCognome("Colgate"));
        assertEquals(utentissimi2,utenti.cercaPerNomeCognome("Pippo Pappolo"));
    }
}
