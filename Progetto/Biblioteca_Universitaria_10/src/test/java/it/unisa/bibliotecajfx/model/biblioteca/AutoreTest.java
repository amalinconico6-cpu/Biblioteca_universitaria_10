/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.bibliotecajfx.model.biblioteca;

import java.util.Comparator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ALDO_MALINCONICO
 */
public class AutoreTest {

    @Test
    public void TestCostruttoreEGetters(){
        Autore a = new Autore("Alessandro", "Visciano");
        assertEquals("Alessandro", a.getNome());
        assertEquals("Visciano", a.getCognome());
    }
    
    @Test
    public void TestSetNome(){
        Autore autore = new Autore ("Giulia","Palladino");
        autore.setNome("Raffaele");
        assertEquals("Raffaele", autore.getNome());
        assertEquals("Palladino", autore.getCognome());
    }
    
    @Test
    public void TestSetCognome() {
        Autore a = new Autore("Martina", "Turi");
        a.setCognome("Turetta");
        assertEquals("Turetta", a.getCognome());
        assertEquals("Martina", a.getNome());
    }
    
    @Test
    public void TestEqualsConNull() {
        Autore a = new Autore("Vincenzo", "Pascariaello");
        assertFalse(a.equals(null));
    }
    
    @Test
    public void TestEqualsConClasseDiversa() {
    Autore a = new Autore("Mario", "Merola");
    String altro = "DEFINETLY NOT AN AUTHOR";

    assertFalse(a.equals(altro));
    }
    
    @Test
    public void TestEqualsStessiValori() {
    Autore a1 = new Autore("Pasquale", "Petraglia");
    Autore a2 = new Autore("Pasquale", "Petraglia");
    assertTrue(a1.equals(a2));
    assertTrue(a2.equals(a1));
    }

    @Test
    public void TestEqualsNomeDiverso() {
    Autore a1 = new Autore("Angelo", "Mastandrea");
    Autore a2 = new Autore("Anna","Mastandrea");
    assertFalse(a1.equals(a2));
    }
    
    @Test
    public void TestEqualsCognomeDiverso() {
    Autore a1 = new Autore("Simone", "Pellecchia");
    Autore a2 = new Autore("Simone", "Spellecchia");
    assertFalse(a1.equals(a2));
    }

    @Test
    public void TestEqualsEntrambiDiversi() {
    Autore a1 = new Autore("Francesco", "Sabia");
    Autore a2 = new Autore("Bejja", "Sabbia");
    assertFalse(a1.equals(a2));
    }
    
    @Test
    public void TestEqualsStessoRiferimento() {
    Autore a = new Autore("Laura", "Nigro");
    assertTrue(a.equals(a));   
    }
}
  