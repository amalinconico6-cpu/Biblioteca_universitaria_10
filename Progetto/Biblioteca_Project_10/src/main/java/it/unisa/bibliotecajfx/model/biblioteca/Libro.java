package it.unisa.bibliotecajfx.model.biblioteca;

import it.unisa.bibliotecajfx.model.biblioteca.Autore;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;


/**
 * @file Llibro
 * @brief classe per la gestione del libro
 * "fornisce i metodi per la gestione del singolo libro"
 * @author Francesco Sabia
 * @version 1.0
 */

public class Libro implements Serializable {

    private String titolo;
    private List<Autore> autori=new ArrayList<>();
    private String ISBN;
    private int anno;
    private boolean disponibile;
    private int copie;

    public static final Comparator<Libro> TITOLO = Comparator.comparing(Libro::getTitolo);
    public static final Comparator<Libro> ISBN_COMPARATOR = Comparator.comparing(Libro::getISBN);
    public static final Comparator<Libro> PRIMO_AUTORE =Comparator.comparing(libro -> libro.getAutori().get(0).getCognome());

/**
 * @brief "construttore di libro"
 * "costruttore della classe libro "
 * @pre gli attributi passati non devono essere null
 * @post viene istanziato un nuovo ogetto libro
 * @param[in] Titolo,Lista di autori,ISBN,annocopie e dipinibilita
 * @param[out] ogetto di tipo Libro
 * @author Francesco Sabia
 */
    public Libro(String titolo, List<Autore> autori, String ISBN, int anno, boolean disponibile, int copie) {
        this.titolo = titolo;
        this.autori = new ArrayList<>(autori);
        this.ISBN = ISBN;
        this.anno = anno;
        this.disponibile = disponibile;
        this.copie = copie;
    }
/**
 * @brief "getter del titolo"
 * @return il titolo del libro
 * @author Francesco Sabia
 */
    public String getTitolo() {
        return titolo;
    }
/**
 * @brief "setter del titolo"
 * @author Francesco Sabia
 */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
/**
 * @brief "getter della lista degli autori"
 * @return la lista degli autori
 * @author Francesco Sabia
 */
    public List<Autore> getAutori() {
        return autori;
    }
/**
 * @brief "setter degli autori"
 * @param[in] una lista di autori
 * @author Francesco Sabia
 */
    public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }
/**
 * @brief "modifica dei un autere presente nella lista"
 * "permette la modifica di un autore sostituendo le informazioni di un autore passato con l'altro passato"
 * @pre devono essere passati 2 autori non null
 * @post viene effetuata la modifica
 * @param[in] 2 ogetti di tipo Autore
 * @author Francesco Sabia
 */
    public void modificaAutore(Autore autore,Autore autore_m) {
        for(Autore a:this.autori ){
            if(a.equals(autore)){
                a.setCognome(autore_m.getCognome());
                a.setNome(autore_m.getNome());
            }
        }
    }
/**
 * @brief "getter del ISBN"
 * @return L'ISBN
 * @author Francesco Sabia
 */
    public String getISBN() {
        return ISBN;
    }
/**
 * @brief "setter ISBN"
 * @pre ISBN passato != null
 * @author Francesco Sabia
 */
     public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
/**
 * @brief "getter Anno"
 * @return l'anno 
 * @author Francesco Sabia
 */
    public int getAnno() {
        return anno;
    }
/**
 * @brief "setter Anno"
 * @pre l'anno passato!= null
 * @author Francesco Sabia
 */
    public void setAnno(int anno) {
        this.anno = anno;
    }
/**
 * @brief "getter Copie"
 * "inserire qui descrizione specifica del metodo"
 * @return l'attributo copie
 * @author Francesco Sabia
 */
    public int getCopie() {
        return copie;
    }
/**
 * @brief "setter copie"
 * @pre le copie passate !=null
 * @author Francesco Sabia
 */
    public void setCopie(int copie) {
        this.copie=copie;
    }
/**
 * @brief "aggiunta autore"
 * "permette l'aggiunta di un nuovo autore alla lista di autori gia presente"
 * @pre Autore passato != null
 * @post viene aggiunto un autore
 * @param[in] Autore a
 * @author Francesco Sabia
 */
    public void aggiungiAutore(Autore a) {
        if(!(this.autori.contains(a))){
            this.autori.add(a);
            this.ordinaAutori();
        }
    }
/**
 * @brief "rimozione autore"
 * "permette la rimozione di un autore dalla lista di autori gia presente"
 * @pre Autore passato != null
 * @post viene rimosso l'Autore un autore
 * @param[in] Autore a
 * @author Francesco Sabia
 */
    public void rimuoviAutore(Autore a) {
        if(this.autori.contains(a)){
            this.autori.remove(a);
            this.ordinaAutori();
        }
    }
/**
 * @brief "getter disponibile"
 * @return l'attributo disponibile
 * @author Francesco Sabia
 */
    public boolean getDisponibile() {
        return disponibile;
    }
/**
 * @brief "setter disponibile"
 * @pre l'attributo passat!=null
 * @author Francesco Sabia
 */
    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }
/**
 * @brief "ordina gli autori presenti nella lista"
 * "ordina la lista di autori base all'ordine stabilito nella classe Autore"
 * @post la lista viene ordinata
 * @author Francesco Sabia
 */
    public void ordinaAutori() {
        autori.sort(Autore.getCOGNOME());
    }
/**
 * @brief "metodo equls"
 * "override del metodo equals di object prevede la gestione di obj nullo"
 * @post effetua il confronto tra l'obj passato e l'ogetto invocante
 * @param[in] un ogetto object
 * @return un boolean
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
        final Libro other = (Libro) obj;
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        return true;
    }
}
