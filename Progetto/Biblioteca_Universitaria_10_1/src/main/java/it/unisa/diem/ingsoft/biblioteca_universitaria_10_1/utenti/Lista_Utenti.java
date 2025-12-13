/**
 * @file Lista_Utenti.java
 * @author ALDO MALINCONICO
 */

package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.utenti;
import it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca.*;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class Lista_Utenti {

    private List<Utente> utenti = new ArrayList<>();

    /**
     * @brief "Aggiunge un nuovo utente alla lista"
     * "Se utente è null torna null, se utente NON è null allora chiama 
     * checkUtente per verificare se esiste un utente con la stessa matricola, 
     * se NON esiste lo aggiunge"
     * @pre i campi devono essere non NULL
     * @post se i controlli vengono superati e se la matricola è univoca
     * l'utente viene aggiunto alla lista
     * @param[in] utente
     * @author ALDO MALINCONICO
     */
    
    public String aggiungiUtente(Utente utente) {
        if (utente == null) {
            return "Utente non valido";
        }

        String matricola = String.valueOf(utente.getMatricola());
        String nome = utente.getNome();
        String cognome = utente.getCognome();
        String email = utente.getMail();

        if (matricola == null) {
            return "Matricolda non valida";
        }
        if (nome == null || nome.trim().isEmpty()) {
            return "Nome non valido";
        }
        if (cognome == null || cognome.trim().isEmpty()) {
            return "Cognome non valido";
        }
        if (email == null || email.trim().isEmpty()) {
            return "Email non valida";
        }
        if (checkMatricola(matricola)) {
            return "Esiste già un utente con questa matricola";
        }
        utenti.add(utente);
        return "Utente aggiunto correttamente";
    }
    /**
     * @brief "Rimuove un utente dalla lista"
     * @pre l'utente deve essere non null per esserea aggiunto
     * @post se l'utente è presente nella lista viene rimosso
     * @param[in] utente da rimuovere
     * @author ALDO MALINCONICO
     */
  public String rimuoviUtente(Utente utente) {
    if (utente == null) {
        return "Utente non valido";
    }
    boolean rimosso = utenti.remove(utente); 
    if (!rimosso) {
        return "Utente non presente in lista";
    }
    return "Utente rimosso correttamente";
}

    /**
     * @brief "Modifica i dati di un utente presente in lista"
     * "L’utente viene individuato tramite equals(). Se trovato, viene sostituito 
     * con l’utente passato come parametro."
     * @pre l'utente non deve essere NULL
     * @post se l'utente è presente i suoi dati vengono modificati
     * @param[in] istanza utente da modificare
     * @return utente aggiornato se la modifica va a buon fine
     * @author ALDO MALINCONICO
     */
    public Utente modificaUtente(Utente utente) {
        if (utente == null) return null;

        int index = utenti.indexOf(utente); 
    /*indexOf() cerca l'utente nella lista usando equals() per confrontare
    matricola*/
        if (index != -1) {
            utenti.set(index, utente);
            return utente;
        }
        return null;
    }

    /**
     * @brief "Restituisce la lista utenti"
     * @return lista utenti 
     * @author ALDO MALINCONICO
     */
    public List<Utente> getUtenti() {
        return utenti;
    }
    
    /**
     * @brief "Imposta la lista utenti"
     * Se la lista passata è NULL, viene creata una nuova lista
     * Altrimenti la lista interna viene sostituita con quella fornita
     * @param[in] lista utenti da gestire
     * @author ALDO MALINCONICO
     */
    public void setUtenti(List<Utente> utenti) {
    if (utenti == null) {
    this.utenti = new ArrayList<>();
    } else {
    this.utenti = utenti;
    }
    }

    /**
     * @brief "Ricerca utente per matricola"
     * "Creo la lista dei risultati (all'inizio vuota),
     * trim mi serve perchè se l'utente digita degli spazi mi "danneggia" la
     * stringa, con trim "ritaglio" la stringa in quello che mi serve"
     * @return lista utenti trovati (anche vuota)
     * @param str
     * @author ALDO MALINCONICO
     */
    
public List<Utente> cercaPerMatricola(String str) {
    List<Utente> risultato = new ArrayList<>();
    if (str == null) return risultato;
    String trimmed = str.trim();
    if (trimmed.isEmpty()) return risultato;
    for (Utente u : utenti) {
        if (String.valueOf(u.getMatricola()).equals(trimmed)) {
            risultato.add(u);
        }
    }
    return risultato;
}
/**
 * @brief "Controlla se esiste un utente con la matricola indicata"
 * @pre La matricola può essere valida, NULL o vuota
 * @post nessuna modifica alla lista
 * @author ALDO MALINCONICO
*/
public boolean checkMatricola(String matricola) {
        if (matricola == null || matricola.trim().isEmpty()) return false;
String trimmed = matricola.trim();
        for (Utente u : utenti) {
            if (u != null && String.valueOf(u.getMatricola()).equals(trimmed)) {
                return true;
            }
        }
        return false;
    }


/**
 * @brief "Cerca utenti in base a nome o cognome"
 * "Nella ricerca dei nomi converto tutto in minuscolo per avere una ricerca 
 * case-insensitive per garantire che l’utente ottenga match 
 * indipendentemente da come sono state inserite le maiuscole nel file"
 * @pre la stringa può essere valida o NULL
 * @post nessuna modifica alla lista
 * @param[in] stringa di ricerca (nome o cognome)
 * @return lista utenti che soddisfano la ricerca
 * @author ALDO MALINCONICO
 */
public List<Utente> cercaPerNomeCognome(String str) {
    List<Utente> risultato = new ArrayList<>();
    if (str == null) {
        return risultato;
    }
    
    String trimmed = str.trim();
    
    if (trimmed.isEmpty()) {
        return risultato; 
    }
    
    String query = trimmed.toLowerCase();
    
    for (Utente u : utenti) {
        String nome;
        String cognome;
        //Gestisco il nome: se getNome() non è null, lo porto in minuscolo;
        //altrimenti assegno una stringa vuota.
        if (u.getNome() != null) {
            String nomeOriginale = u.getNome();      // nome così come è memorizzato nell'oggetto
            String nomeMinuscolo = nomeOriginale.toLowerCase(); // lo normalizzo in minuscolo
            nome = nomeMinuscolo;              // assegno alla variabile finale
        } else {
            nome = ""; // nessun nome disponibile ? uso stringa vuota
        }
        if (u.getCognome() != null) {
            String cognomeOriginale = u.getCognome();     
            String cognomeMinuscolo = cognomeOriginale.toLowerCase(); 
            cognome = cognomeMinuscolo;                    
        } else {
            cognome = ""; // 
        }
        
         if (nome.contains(query) || cognome.contains(query)) {
                risultato.add(u);
            }
    }
    return risultato;
}

    /**
     * @brief Salvataggio utenti su file
     * @author ALDO MALINCONICO
     */
    public void salvataggioUtenti(String nomefile)throws IOException{
        try(ObjectOutputStream o=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomefile)))){
            o.writeObject(utenti);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * @brief Lettura utenti da file
     * @author ALDO MALINCONICO
     * @return nuova lista utenti caricata dal file
     */
    public void letturaUtenti(String nomefile)throws IOException{
        try(ObjectInputStream o=new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomefile)))){
            List<Utente> readed= (List<Utente>)o.readObject(); 
            this.utenti=readed;
        }catch(IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
