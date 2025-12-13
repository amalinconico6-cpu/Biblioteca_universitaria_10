package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutoreTest {

    private Autore autore;

    @BeforeEach
    void setUp() {
        autore = new Autore("Mario", "Rossi");
    }

    @Test
    void testGetNome() {
        assertEquals("Mario", autore.getNome());
    }

    @Test
    void testSetNome() {
        autore.setNome("Giuseppe");
        assertEquals("Giuseppe", autore.getNome());
    }

    @Test
    void testGetCognome() {
        assertEquals("Rossi", autore.getCognome());
    }

    @Test
    void testSetCognome() {
        autore.setCognome("Bianchi");
        assertEquals("Bianchi", autore.getCognome());
    }
}
