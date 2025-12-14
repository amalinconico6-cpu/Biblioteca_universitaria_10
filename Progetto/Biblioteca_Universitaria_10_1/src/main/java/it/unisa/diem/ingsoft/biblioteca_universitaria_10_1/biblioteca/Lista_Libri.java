  /**
 * @file Lista_libri
 * @brief classe per la gestione della lista dei libri
 * "fornisce tutti i metodi per la gestione della lista dei libri"
 * @author Francesco sabia
 * @version 1.0
 */


package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lista_Libri {
    private List<Libro> libri=new ArrayList<>();

/**
 * @brief "getter Libro"
 * @retrun lista libri
 * @author Francesco Sabia
 */
    
    public List<Libro> getLibri(){
        return libri;
    }

    public void aggiungiLibro(Libro libro) {
        if(this.libri.contains(libro)){
            for(Libro l: this.libri){
                if(l.equals(libro)){
                    l.setCopie(l.getCopie()+1);
                }
            }
        }    
        else{
            this.libri.add(libro);
        }
    }
/**
  * @brief "rimuovi libro"
 * "permette la rimozione di un nuovo libro dalla lista di libri gia presente"
 * @pre Libro passato != null
 * @post viene rimosso un libro
 * @param[in] Libro libro
 * @author Francesco Sabia
 */
    public void rimuoviLibro(Libro libro){
        if((this.libri.contains(libro))){
            this.libri.remove(libro);
        }
    }
/**
 * @brief "modifica dei dati di un libro"
 * "metodo per la gestione delle modifiche di uno qualsiasi dei campi del libro"
 * @pre i 2 Libri passati non devono essere null
 * @post la lista libri viene aggiornata
 * @param[in] 2 ogetti di tipo Libro
 * @param[out] la la lista dei libri viene aggiornata
 * @author Francesco Sabia
 */
    public void modificaLibro(Libro libro, Libro l_modificato){
        for(Libro l: this.libri){
            if(l.equals(libro)){
                    l=l_modificato;
            }
        }
    }
    
    /**
 * @brief "permette di modificare solo il numero di copie presenti nella lista"
 * "inserire qui descrizione specifica del metodo"
 * @pre il numero non deve essere 0 e l'ISBN deve essere valido
 * @post la lista viene aggiornata
 * @param[in] ISBN  e n 
 * @author Francesco Sabia
 */
    public void modificaCopie(String ISBN, int n){
        for (Libro l : this.libri) {
            if (l.getISBN().equals(ISBN)) {
                int risultato = l.getCopie() + n;
                if (risultato >= 0) {
                    l.setCopie(risultato);
                }
            }
        }
    }
/**
 * @brief "cerca un libro per titolo"
 * "permette la ricerca per titolo passato sotto forma di stringa"
 * @pre Il titolo deve essere valido
 * @param[in] una stringa contenente il titolo
 * @param[out] la lista con solo i libri con quel titolo
 * @return la lista con solo i libri con quel titolo
 * @author Francesco Sabia
 */
    public List<Libro> cercaPerTitolo(String str){
        List<Libro> temp_list=new ArrayList<>();
        for(Libro l: this.libri){
            if(l.getTitolo().equalsIgnoreCase(str)){
                temp_list.add(l);
            }
        }

        return temp_list;
    }
/**
 * @brief "cerca un libro per ISBN"
 * "permette la ricerca per ISBN passato sotto forma di stringa"
 * @pre ISBN deve essere valido
 * @param[in] una stringa contenente ISBN
 * @param[out] la lista con solo i libri con quel ISBN
 * @return la lista con solo i libri con quel ISBN
 * @author Francesco Sabia 
 */
    public List<Libro> cercaPerISBN(String str){
        List<Libro> temp_list=new ArrayList<>();
        for(Libro l: this.libri){
            if(l.getISBN().equals(str)){
                temp_list.add(l);
            }
        }
        return temp_list;
    }
/**
  * @brief "cerca un libro per Autore"
 * "permette la ricerca per Autore passato sotto forma di stringa"
 * @pre l'Autore deve essere valido
 * @param[in] una stringa contenente l'autore
 * @param[out] la lista con solo i libri con quel autore presente nella lista di autori
 * @return la lista con solo i libri con quel autore presente nella lista di autori
 * @author Francesco Sabia
 */
    public List<Libro> cercaPerAutore(String str){
        List<Libro> temp_list=new ArrayList<>();
        for(Libro l: this.libri){
            for(Autore a : l.getAutori())
                if(a.getCognome().equalsIgnoreCase(str)||a.getNome().equalsIgnoreCase(str))
                    temp_list.add(l);
            }
            return temp_list;
    }
/**
 * @brief "controlla l'esistenza dell'ISBN"
 * @pre ISBN deve essere valido
 * @param[in] Una Stringa contente l'ISBN
 * @param[out] un boolean
 * @return un boolean
 * @author Francesco Sabia
 */
  public boolean checkISBN(String ISBN){
        for(Libro l: this.libri){
            if(l.getISBN().equals(ISBN))
                return true;
        }
        return false;
    }
  /**
 * @brief "controlla il numero di copie dell'ISBN"
 * @pre ISBN deve essere valido
 * @param[in] Una Stringa contente l'ISBN
 * @param[out] un boolean
 * @return un boolean
 * @author Francesco Sabia
 */
  public boolean checkCopie(String ISBN){
        for(Libro l: this.libri){
            if(l.getISBN().equals(ISBN)){
                if(l.getCopie()>=1)
                   return true;
            }
        }
        return false;
    }
 /**
 * @brief "ordina i libri presenti nella lista"
 * "ordina la lista di libri in base a uno degli ordini stabiliti nella classe Libro"
 * @post la lista viene ordinata
 * @author Francesco Sabia
 */
    public void ordina(Comparator<Libro> cmp){
        libri.sort(cmp);
    }

/**
 * @brief "Salvataggio su file esterno"
 * "Permette il salvataggio dell' intera lista su un file esterno"
 * @pre La lista di libri deve contenere almeno un elemento
 * @post il file viene aggiornato
 * @param[in] una stringa con il nome del file
 * @author Francesco Sabia
 */
    public void salvataggioLibri(String nomefile)throws IOException{
        FileOutputStream fileOut = new FileOutputStream(nomefile);
        try(ObjectOutputStream objin=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomefile)))){
            objin.writeObject(this.libri); 
        }catch(IOException e){
            e.printStackTrace();
        }
    }
/**
 * @brief "Lettura da file"
 * "crea una lista di libri effetuando una copia dal file esterno"
 * @pre il file deve esistere e non deve essere vuoto
 * @post viene creata una lista di libri 
 * @param[in] una stringa contente  il nome del file
 * @param[out] la lista dei libri
 * @author Francesco Sabia
 */
    public void letturaLibri(String nomefile)throws IOException{
        try(ObjectInputStream objout=new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomefile)))){
            List<Libro> lista_e= (List<Libro>)objout.readObject(); 
            this.libri=lista_e;
        }catch(IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
