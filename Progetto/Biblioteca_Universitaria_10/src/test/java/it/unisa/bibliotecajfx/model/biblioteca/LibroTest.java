/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.bibliotecajfx.model.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author ALDO_MALINCONICO
 */
public class LibroTest {
    private Autore autore1;
    private Autore autore2;
    private Autore autore3;
    private Libro libro;
    
    @BeforeEach
    public void SetUp() {
    autore1 = new Autore("Mario", "Rossi"); //Rossi
    autore2 = new Autore("Laura", "Bianchi"); //Bianchi
    autore3 = new Autore("Tiziana", "Verdi"); //Verdi
    List<Autore> autori = new ArrayList<>();
    autori.add(autore1);
    libro = new Libro("Prova di Prova", autori, "1234567890123", 2049, true, 5);
    }
    
    @Test
    void TestCostruttoreEGetters() {
    assertEquals("Prova di Prova", libro.getTitolo());
    assertEquals("1234567890123", libro.getISBN());
    assertEquals(2049, libro.getAnno());
    assertTrue(libro.getDisponibile());
    assertEquals(5, libro.getCopie());
    assertEquals(1, libro.getAutori().size());
    assertTrue(libro.getAutori().contains(autore1));
    }

    @Test
    void TestSetTitolo() {
    libro.setTitolo("Nuovo Titolo Prova");
    assertEquals("Nuovo Titolo Prova", libro.getTitolo());
    }

    @Test
    void TestSetAutori() {
    List<Autore> nuovaLista = Arrays.asList(autore1, autore2);
    libro.setAutori(nuovaLista);
    assertEquals(2, libro.getAutori().size());
    assertTrue(libro.getAutori().contains(autore1));
    assertTrue(libro.getAutori().contains(autore2));
    }

    @Test
    void TestAggiungiAutoreNuovoAutoreInserito() {
    int sizePre = libro.getAutori().size();
    libro.aggiungiAutore(autore2);
    assertEquals(sizePre + 1, libro.getAutori().size());
    assertTrue(libro.getAutori().contains(autore2));
    }

    @Test
    void TestAggiungiAutoreDuplicatoNonAggiunto() {
    libro.aggiungiAutore(autore1); 
    int sizeDopo = libro.getAutori().size();
    assertEquals(1, sizeDopo);
    }

    @Test
    void TestRimuoviAutorePresente() {
    libro.aggiungiAutore(autore2);
    libro.rimuoviAutore(autore2);
    assertFalse(libro.getAutori().contains(autore2));
    }

     @Test
    void TestRimuoviAutoreNonPresente() {
    int sizePrima = libro.getAutori().size();
    libro.rimuoviAutore(autore2);
    assertEquals(sizePrima, libro.getAutori().size());
    }

    @Test
    void TestModificaAutore() {
    Autore autoreModificato = new Autore("Samuele", "Tortora");
    libro.modificaAutore(autore1, autoreModificato);
    assertEquals("Samuele", autore1.getNome());
    assertEquals("Tortora", autore1.getCognome());
    }

    @Test
    void TestOrdinaAutori_PerCognome() {
    List<Autore> lista = new ArrayList<>();
    lista.add(autore1); // Rossi
    lista.add(autore2); // Bianchi
    lista.add(autore3); // Verdi
    libro.setAutori(lista);
    libro.ordinaAutori();
    List<Autore> ordinati = libro.getAutori();
    assertEquals("Bianchi", ordinati.get(0).getCognome());
    assertEquals("Rossi", ordinati.get(1).getCognome());
    assertEquals("Verdi", ordinati.get(2).getCognome());
    }

    @Test
    void TestEquals_StessoOggetto() {
    assertTrue(libro.equals(libro));
    }

     @Test
    void TestEquals_Null() {
    assertFalse(libro.equals(null));
    }

    @Test
    void TestEquals_AltroTipo() {
    assertFalse(libro.equals("non un libro"));
    }
    
    @Test
    void TestEquals_StessoISBN() {
    List<Autore> autori = new ArrayList<>();
    autori.add(autore1);       
    Libro altro = new Libro("Altro titolo", autori, "1234567890123", 2021, false, 1);
    assertTrue(libro.equals(altro));
    assertTrue(altro.equals(libro));
    }

    @Test
    void TestEquals_ISBNDiverso() {
    List<Autore> autori = new ArrayList<>();
    autori.add(autore1);
    Libro altro = new Libro("Altro titolo", autori, "0000000000000", 2021, false, 1);
    assertFalse(libro.equals(altro));
    assertFalse(altro.equals(libro));
    }
}