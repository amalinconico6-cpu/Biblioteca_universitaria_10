/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.prestiti.Lista_Prestiti;
import it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.prestiti.Prestito;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author francesco sabia
 */
public class Lista_PrestitiTest {
    private Lista_Prestiti listaPrestiti;
    private Prestito p1, p2,p3,p4,pr;
    private final String MATRICOLA = "0612709788";
    private final String MATRICOLA_RIT = "0612709789";
    
    @BeforeEach
    public void setUp() {
        listaPrestiti = new Lista_Prestiti();
        p1 = new Prestito("1234567890123",MATRICOLA,LocalDate.of(2025, 12, 25));
        p2 = new Prestito("1011121314151",MATRICOLA,LocalDate.of(2025, 12, 13));
        p3 = new Prestito("1234567890124",MATRICOLA,LocalDate.of(2025, 12, 14));
        p4 = new Prestito("1234567890125",MATRICOLA,LocalDate.of(2025, 12, 15));
        pr = new Prestito("6171819202122",MATRICOLA_RIT,LocalDate.of(2025,12,1));
    }
    @Test
    public void testAggiongiPrestito(){
        listaPrestiti.aggiungiPrestito(p1);
        assertEquals(1, listaPrestiti.getPrestiti().size());
        assertTrue(listaPrestiti.getPrestiti().contains(p1)); 
    }
    @Test
    public void testAggiongiPrestitoLimite(){
        listaPrestiti.aggiungiPrestito(p2);
        listaPrestiti.aggiungiPrestito(p3);
        assertEquals(3, listaPrestiti.getPrestiti().size());
        assertFalse(listaPrestiti.aggiungiPrestito(p4));
        assertEquals(3, listaPrestiti.getPrestiti().size());
    }
    @Test
    public void testAggiongiPrestitoOrdinato(){
        List<Prestito> tmp_lista = listaPrestiti.getPrestiti();
        assertEquals(p2, tmp_lista.get(0));
        assertEquals(p1, tmp_lista.get(1));
        assertEquals(p3, tmp_lista.get(2));
    }
    @Test
    public void testRimuoviPrestito() {
        assertTrue(listaPrestiti.rimuoviPrestito(p1));
        assertTrue(listaPrestiti.getPrestiti().isEmpty());
    }
    @Test
    public void testAggiornaRitardi() {
    listaPrestiti.aggiungiPrestito(p1);
    listaPrestiti.aggiungiPrestito(pr);
    List<Prestito> ritardi = listaPrestiti.aggiornaRitardi();
    assertEquals(1, ritardi.size());
    assertEquals(pr, ritardi.get(0));
    }
    @Test
    public void testCercaPerISBN() {
        listaPrestiti.aggiungiPrestito(p1);
        List<Prestito> risultati = listaPrestiti.cercaPerISBN("1234567890123");
        assertEquals(1, risultati.size());
        assertEquals(p1, risultati.get(0));
    }
     @Test
    public void testCercaPerMatricola() {
        listaPrestiti.aggiungiPrestito(p1);
        List<Prestito> risultati = listaPrestiti.cercaPrestitoPerMatricola(MATRICOLA);
        assertEquals(1, risultati.size());
        assertEquals(p1, risultati.get(0));
    }
    @Test
    public void testModificaPrestito() {
        listaPrestiti.aggiungiPrestito(p1);
        Prestito pn = new Prestito("1234567890123", MATRICOLA,LocalDate.of(2025,12,31));
        assertTrue(listaPrestiti.ModificaPrestito(p1, pn));
        assertEquals(pn.getDataScadenza(), p1.getDataScadenza());
    }
    @Test
    public void testModificaPrestitodaFallire(){
        Prestito pt1 = new Prestito("7894561237894123", MATRICOLA_RIT, LocalDate.now());
        Prestito pt2 = new Prestito("1239638524567898", MATRICOLA_RIT, LocalDate.now());
        Prestito pt3 = new Prestito("1239637897411238", MATRICOLA_RIT, LocalDate.now());
        listaPrestiti.aggiungiPrestito(pt1);
        listaPrestiti.aggiungiPrestito(pt2);
        listaPrestiti.aggiungiPrestito(pt3);
        listaPrestiti.aggiungiPrestito(p1);
        Prestito temp_p = new Prestito("1234567890123", MATRICOLA_RIT, LocalDate.now());
        assertFalse(listaPrestiti.ModificaPrestito(p1,temp_p));
    }
}

