/**
 * @file Llibro
 * @brief classe per la gestione del libro
 * "fornisce i metodi per la gestione del singolo libro"
 * @author Francesco Sabia
 * @version 1.0
 */


package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Libro {

    private String titolo;
    private List<Autore> autori;
    private String ISBN;
    private int anno;
    private boolean disponibile;
    private int copie;

    public static final Comparator<Libro> TITOLO = null;
    public static final Comparator<Libro> ISBN_COMPARATOR = null;
    public static final Comparator<Libro> PRIMO_AUTORE = null;

/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public Libro(String titolo, List<Autore> autori, String ISBN, int anno, boolean disponibile, int copie) {
        this.titolo = titolo;
        this.autori = autori;
        this.ISBN = ISBN;
        this.anno = anno;
        this.disponibile = disponibile;
        this.copie = copie;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
 */
    public String getTitolo() {
        return titolo;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
 */
    public List<Autore> getAutori() {
        return autori;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void modificaAutore(Autore autore) {
        // vuoto
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
 */
    public String getISBN() {
        return ISBN;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
     public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
 */
    public int getAnno() {
        return anno;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void setAnno(int anno) {
        this.anno = anno;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
 */
    public int getCopie() {
        return copie;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void setCopie(int copie) {
        this.copie=copie;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void aggiungiAutore(Autore a) {
        if(!(this.autori.contains(a))){
            this.autori.add(a);
            this.ordinaAutori();
        }
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void rimuoviAutore(Autore a) {
        if(this.autori.contains(a)){
            this.autori.remove(a);
            this.ordinaAutori();
        }
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
 */
    public boolean getDisponibile() {
        return disponibile;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void ordinaAutori() {
        autori.sort(Autore.getCOGNOME());
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
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
        final Libro other = (Libro) obj;
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        return true;
    }
}
