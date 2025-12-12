/**
 * @file Autore.java
 * @brief classe per identificare un autore
 * "Questa classe viene usata per identificare un autore. Comprende i metodi get e set e la equals"
 * @author Francesco Sabia
 * @version 1.0
 */
//verifica push git

package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca;

import java.util.Comparator;
import java.util.Objects;


public class Autore {

    private String nome;
    private String cognome;

    public static Comparator<Autore> COGNOME;

/**
 * @brief "Costruttore delle classe"
 * "costruttore di classe"
 * @pre il passaggio degli attributi di classe
 * @post un autere
 * @param[in] nome,cognome
 * @param[out] Autore
 * @author Francesco Sabia
 */
    public Autore(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }
/**
 * @brief "getter del nome"
 * "getter del attributo nome"
 * @pre 
 * @post 
 * @param[in]
 * @param[out] 
 * @return attributo nome
 * @author Francesco Sabia
 */
    public String getNome() {
        return nome;
    }
/**
 * @brief "setter del nome"
 * "setter del nome"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author Francesco Sabia
 */
    public void setNome(String nome) {
        this.nome = nome;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author Francesco Sabia
 */
    public String getCognome() {
        return cognome;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author Francesco Sabia
 */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author Francesco Sabia
 */
    public static Comparator<Autore> getCOGNOME() {
        return COGNOME;
    }
    
 /**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author Francesco Sabia
 */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autore other = (Autore) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        
        return true;
    }
}
