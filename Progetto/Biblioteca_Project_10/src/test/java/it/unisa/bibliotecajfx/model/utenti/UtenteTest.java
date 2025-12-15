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
public class UtenteTest {
    private Utente utente;
    
    @BeforeEach
    public void setUp() {
        utente=new Utente("Pippo","Pappolo","0612708899","p.pappolo@studenti.unisa.it");
    }
    /**
     * Test of getNome method, of class Utente.
     */
    @Test
    public void testGetNome() {
        String nome="Pippo";
        assertEquals(nome,utente.getNome());    
    }

    /**
     * Test of setNome method, of class Utente.
     */
    @Test
    public void testSetNome() {
        String nome="Pippa";
        utente.setNome(nome);
        assertEquals(nome,utente.getNome());
    }

    /**
     * Test of getCognome method, of class Utente.
     */
    @Test
    public void testGetCognome() {
        String cognome="Pappolo";
        assertEquals(cognome,utente.getCognome());  
    }

    /**
     * Test of setCognome method, of class Utente.
     */
    @Test
    public void testSetCognome() {
        String cognome="Pappola";
        utente.setCognome(cognome);
        assertEquals(cognome,utente.getCognome());
    }

    /**
     * Test of getMatricola method, of class Utente.
     */
    @Test
    public void testGetMatricola() {
       String matricola="0612708899";
       assertEquals(matricola,utente.getMatricola());
    }

    /**
     * Test of setMatricola method, of class Utente.
     */
    @Test
    public void testSetMatricola() {
        String matricola="0612705559";
        utente.setMatricola(matricola);
        assertEquals(matricola,utente.getMatricola());
    }

    /**
     * Test of getMail method, of class Utente.
     */
    @Test
    public void testGetMail() {
       String mail="p.pappolo@studenti.unisa.it";
       assertEquals(mail,utente.getMail());
    }

    /**
     * Test of setMail method, of class Utente.
     */
    @Test
    public void testSetMail() {
       String mail="t.pappolo@studenti.unisa.it";
       utente.setMail(mail);
       assertEquals(mail,utente.getMail());
    }

    /**
     * Test of equals method, of class Utente.
     */
    @Test
    public void testEquals() {
        Utente u=new Utente("p","pa","06177","ursula.vonderlein@gmail.eu");
        assertTrue(utente.equals(utente));
        assertFalse(utente.equals(null));
        assertFalse(utente.equals(u));
    }
    
}
