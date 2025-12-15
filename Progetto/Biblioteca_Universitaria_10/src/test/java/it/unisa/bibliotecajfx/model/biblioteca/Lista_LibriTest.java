/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.bibliotecajfx.model.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ALDO_MALINCONICO
 */

class Lista_LibriTest {

    private Lista_Libri lista;

    @BeforeEach
    public void setUp() {
        lista = new Lista_Libri();
    }

    private Autore autore(String nome, String cognome) {
        return new Autore(nome, cognome);
    }

    private Libro libro(String isbn, String titolo, int copie, Autore... autori) {
        List<Autore> listaAutori = new ArrayList<>(Arrays.asList(autori));
        return new Libro(titolo, listaAutori, isbn, 2020, true, copie);
    }

    @Test
    public void TestGetLibriInizialmenteVuota() {
        assertNotNull(lista.getLibri());
        assertTrue(lista.getLibri().isEmpty());
    }

    @Test
    public void TestAggiungiLibroNuovoLibro() {
        Libro l1 = libro("ISBN1", "Titolo1", 1, autore("Claudio", "Baglioni"));
        lista.aggiungiLibro(l1);

        assertEquals(1, lista.getLibri().size());
        assertTrue(lista.getLibri().contains(l1));
    }

    @Test
    public void TestAggiungiLibroStessoISBN() {
        Libro l1 = libro("ISBN1", "Titolo1", 1, autore("Bruno", "Vittorino"));
        lista.aggiungiLibro(l1);

        // equals di Libro confronta SOLO ISBN, quindi questo è considerato "uguale"
        Libro l1Bis = libro("ISBN1", "TitoloDiverso", 99, autore("Altro", "Autore"));
        lista.aggiungiLibro(l1Bis);

        assertEquals(1, lista.getLibri().size());
        assertEquals(2, lista.getLibri().get(0).getCopie(), "Dovrebbe aumentare copie di 1");
    }

    @Test
    public void TestRimuoviLibroPresente() {
        Libro l1 = libro("ISBN1", "Titolo1", 1);
        lista.aggiungiLibro(l1);

        lista.rimuoviLibro(l1);

        assertTrue(lista.getLibri().isEmpty());
    }

    @Test
    public void TestRimuoviLibroNonPresente() {
        Libro l1 = libro("ISBN1", "Titolo1", 1);
        Libro l2 = libro("ISBN2", "Titolo2", 1);

        lista.aggiungiLibro(l1);
        lista.rimuoviLibro(l2);

        assertEquals(1, lista.getLibri().size());
        assertTrue(lista.getLibri().contains(l1));
    }

    @Test
    public void TestCercaPerTitoloCaseInsensitive() {
        lista.aggiungiLibro(libro("A", "Java", 1));
        lista.aggiungiLibro(libro("B", "Reti", 1));

        List<Libro> trovati = lista.cercaPerTitolo("jAvA");

        assertEquals(1, trovati.size());
        assertEquals("A", trovati.get(0).getISBN());
    }

    @Test
    public void TestCercaPerISBN() {
        lista.aggiungiLibro(libro("AAA", "Titolo1", 1));
        lista.aggiungiLibro(libro("BBB", "Titolo2", 1));

        List<Libro> trovati = lista.cercaPerISBN("BBB");

        assertEquals(1, trovati.size());
        assertEquals("Titolo2", trovati.get(0).getTitolo());
    }

    @Test
    public void TestCercaPerAutoreMatchSuNomeOCognomeCaseInsensitive() {
        Autore verdi = autore("Giuseppe", "Verdi");
        Autore rossi = autore("Mario", "Rossi");

        lista.aggiungiLibro(libro("ISBN1", "Opera", 1, verdi));
        lista.aggiungiLibro(libro("ISBN2", "Altro", 1, rossi));

        List<Libro> perNome = lista.cercaPerAutore("giuseppe");
        assertEquals(1, perNome.size());
        assertEquals("ISBN1", perNome.get(0).getISBN());

        List<Libro> perCognome = lista.cercaPerAutore("rossi");
        assertEquals(1, perCognome.size());
        assertEquals("ISBN2", perCognome.get(0).getISBN());
    }

    @Test
    public void TestCheckISBN() {
        lista.aggiungiLibro(libro("AAA", "Titolo1", 1));
        assertTrue(lista.checkISBN("AAA"));
        assertFalse(lista.checkISBN("BBB"));
    }

    @Test
    public void TestCheckCopie() {
        lista.aggiungiLibro(libro("AAA", "Titolo1", 2));

        assertTrue(lista.checkCopie("AAA"));
        assertFalse(lista.checkCopie("BBB"));
    }

    @Test
    public void TestOrdinaPerTitolo() {
        lista.aggiungiLibro(libro("2", "Beta", 1));
        lista.aggiungiLibro(libro("1", "Alpha", 1));

        lista.ordina(Comparator.comparing(Libro::getTitolo, String.CASE_INSENSITIVE_ORDER));

        assertEquals("Alpha", lista.getLibri().get(0).getTitolo());
        assertEquals("Beta", lista.getLibri().get(1).getTitolo());
    }

    @Test
    public void TestModificaLibro() {
        Libro originale = libro("AAA", "Vecchio", 1);
        lista.aggiungiLibro(originale);

        Libro modificato = libro("AAA", "Nuovo", 5);

        lista.modificaLibro(originale, modificato);

        // Con una modifica corretta, titolo dovrebbe diventare "Nuovo"
        assertEquals("Nuovo", lista.getLibri().get(0).getTitolo());
    }

    @Test
    public void TestModificaCopieConfrontoStringheConUgualeUguale() {
        // new String per aumentare chance che '==' fallisca
        String isbn1 = new String("AAA");
        String isbn2 = new String("AAA");

        Libro l = libro(isbn1, "Titolo", 3);
        lista.aggiungiLibro(l);

        lista.modificaCopie(isbn2, -1);

        // con equals sarebbe 2; col codice attuale spesso resta 3
        assertEquals(2, lista.getLibri().get(0).getCopie());
    }

}
