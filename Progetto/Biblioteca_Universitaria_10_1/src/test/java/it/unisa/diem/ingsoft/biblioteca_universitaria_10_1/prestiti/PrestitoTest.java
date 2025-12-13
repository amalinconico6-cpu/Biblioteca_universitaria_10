/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.prestiti.Prestito;
import java.time.LocalDate;
import java.util.Comparator;
/**
 *
 * @author francesco sabia
 */
public class PrestitoTest {
    private Prestito prestito;
    private final String ISBN = "1234567891234";
    private final String MATRICOLA = "0612709788";
    private final LocalDate DATA = LocalDate.of(2025, 12, 13);

    @BeforeEach
    public void setUpClass() {
            prestito = new Prestito("1234567891234","0612709788", LocalDate.of(2025, 12, 13));
    }
    @Test
    public void testConstructor() {
            assertEquals(ISBN, prestito.getISBN());
            assertEquals(MATRICOLA, prestito.getMatricola());
            assertEquals(DATA, prestito.getDataScadenza());
    }
    @Test
    public void testGetComparatore(){
        assertEquals(prestito.SCADENZA,prestito.getComp());
    }
    @Test
    public void testGetISBN(){
        assertEquals(ISBN,prestito.getISBN());
    }
    @Test
    public void testSetISBN() {
        String nuovo_isbn="1011121314151";
        prestito.setISBN(nuovo_isbn);
        assertEquals(nuovo_isbn, prestito.getISBN());

    }
    @Test
    public void testGetMatricola(){
        assertEquals(MATRICOLA, prestito.getMatricola());
    }
    @Test
    public void testsetMatricola(){
        String nuovo_mat="0612709789";
        prestito.setMatricola(nuovo_mat);
        assertEquals(nuovo_mat, prestito.getMatricola());
    }
    @Test
    public void testGetId(){
        assertEquals(1, prestito.getID());
    }
    @Test
    public void testGetSacdenza(){
        assertEquals(DATA, prestito.getDataScadenza());
    }
    @Test
    public void testSetScadenza(){
        prestito.setDataScadenza(LocalDate.of(2025, 12, 25));
        assertEquals(LocalDate.of(2025, 12, 25), prestito.getMatricola());
    }
    @Test
    public void testEquals(){
        Prestito Copia_p= new Prestito(ISBN, MATRICOLA, DATA);
        assertTrue(prestito.equals(prestito));
        assertFalse(prestito.equals(null));
        assertFalse(prestito.equals(Copia_p));
    }
}
