package it.unisa.bibliotecajfx.model.prestiti;
import java.io.Serializable;

/**
 * @file Prestito.java
 * @brief Gestisce i prestiti
 * "rappresenta il contenuto di un singolo prestito nella lista prestiti"
 * @author ALESSANDRO VISCIANO
 */

import java.time.LocalDate;
import java.util.Comparator;

public class Prestito implements Serializable {

    private String ISBN;
    private String Matricola;
    private LocalDate dataScadenza;
    private final int ID;
    private static int counter=0;
    public static final Comparator<Prestito> SCADENZA = Comparator.comparing(Prestito::getDataScadenza);
/**
* @brief costruttore classe Prestito
* "Serve per creare un oggetto prestito inizializzando i suoi attributi"
* @pre i parametri devono essere validi e non null
* @post viene creato un nuovo oggetto prestito
* @param[in] String ISBN,String Matricola,LocalDate dataScadenza
* @author ALESSANDRO VISCIANO
*/
    public Prestito(String ISBN,String Matricola,LocalDate dataScadenza){
        this.ISBN=ISBN;
        this.Matricola=Matricola;
        this.dataScadenza=dataScadenza;
        this.ID=counter++;
    }
/**
 * @brief "get del Comparatore"
 * "restituisce il comparatore per il corretto ordinamento della lista prestiti"
 * @post Viene restituito il comparatore 
 * @return Comparator<Prestito> Scadenza
 * @author ALESSANDRO VISCIANO
 */
    public Comparator<Prestito> getComp(){
        return this.SCADENZA;
    }
/**
 * @brief "get dell'ISBN"
 * "restituisce il valore dell'attributo ISBN"
 * @post Viene restituito l'ISBN
 * @return String ISBN
 * @author ALESSANDRO VISCIANO
 */
    public String getISBN(){
        return this.ISBN;
    }
/**
 * @brief "set dell'ISBN"
 * "Sostituisce l'ISBN dell'oggetto con l'ISBN passato per parametro"
 * @pre L'ISBN deve rispettare il formato desiderato
 * @post L'ISBN viene modificato
 * @param[in] String ISBN
 * @author ALESSANDRO VISCIANO
 */
    public void setISBN(String ISBN){
        if(ISBN!=null)
            this.ISBN=ISBN;
    }
/**
 * @brief "get della Matricola"
 * "restituisce la Matricola associata al prestito"
 * @post viene restituita la Matricola
 * @return String Matricola
 * @author ALESSANDRO VISCIANO
 */
   public String getMatricola(){
        return this.Matricola;
    }
/**
 * @brief "set della Matricola"
 * "Sostituisce la Matricola dell'oggetto con la Matricola passata come parametro"
 * @pre la matricola rispetta il formato desiderato
 * @post viene modificata la Matricola
 * @param[in] String Matricola
 * @author ALESSANDRO VISCIANO
 */
   public void setMatricola(String Matricola){
        this.Matricola=Matricola;
    }
/**
 * @brief "get dell'ID"
 * "Viene restituito l'ID dell'oggetto"
 * @post Viene restituito l'ID
 * @return int ID
 * @author ALESSANDRO VISCIANO
 */
    public int getID(){
       return this.ID;
    }
/**
 * @brief "get della DataScadenza"
 * "Viene restituita la Data di Scadenza del prestito"
 * @post Viene restitito dataScadenza
 * @return  LocalDate dataScadenza
 * @author ALESSANDRO VISCIANO
 */
    public LocalDate getDataScadenza(){
        return this.dataScadenza;
    }
/**
 * @brief "equals"
 * "serve a definire quando 2 prestiti sono uguali nella lista (in base all'ID)"
 * @param[in] Object o
 * @return boolean
 * @author ALESSANDRO VISCIANO
 */
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || this.getClass()!=o.getClass()) return false;
        Prestito p=(Prestito) o;
        return this.getID()==p.getID();
    }
/**
 * @brief "set di DataScadenza"
 * "Sostituisce dataScadenza dell'oggetto con dataScadenza passato come parametro"
 * @pre dataScadenza rispetta il formato desiderato
 * @post viene modificato dataScadenza
 * @param[in] LocalDate dataScadenza 
 * @author ALESSANDRO VISCIANO
 */
    public void setDataScadenza(LocalDate dataScadenza){
        this.dataScadenza=dataScadenza;
    }
}
