/**
 * @file 
 * @brief
 * "Inserire qui descrizione specifica della classe"
 * @author ALDO MALINCONICO
 * @date
 * @version
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
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @author ALDO MALINCONICO
     */
     public String aggiungiUtente(Utente utente) {
        if (utente == null) {
            return "Utente non valido";
        }

        int matricola = utente.getMatricola();
        String nome = utente.getNome();
        String cognome = utente.getCognome();
        String email = utente.getMail();

        if (matricola <= 0) {
            return "Matricola non valida";
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
     * @pre
     * @post
     * @param[in]
     * @param[out]
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
     * @brief "Aggiorna un utente già presente nella lista"
     * "inserire qui descrizione specifica del metodo"
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @author ALDO MALINCONICO
     * @return 
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
     * @brief "inserire qui descrizione breve"
     * @param[in]
     * @param[out]
     * @author ALDO MALINCONICO
     * @return 
     */
    public List<Utente> getUtenti() {
        return utenti;
    }

    /**
     * @brief "Ritorna l'intera lista degli utenti"
     * "Se la lista passata è null, creo una nuova lista vuota,
     * altrimenti assegno direttamente la lista ricevuta
     * @param[in]
     * @param[out]
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
     * @brief Ricerca per matricola
     * "Creo la lista dei risultati (all'inizio vuota),
     * trim mi serve perchè se l'utente digita degli spazi mi "danneggia" la
     * stringa, con trim "ritaglio" la stringa in quello che mi serve"
     * @return lista utenti trovati
     * @param str
     * @author ALDO MALINCONICO
     */
    
public List<Utente> cercaPerMatricola(String str) {
    List<Utente> risultato = new ArrayList<>();
    if (str == null) {
        return risultato;
    }
    String trimmed = str.trim();
    if (trimmed.isEmpty()) {
        return risultato;
    }
    try {
        int matricola = Integer.parseInt(trimmed);
        for (Utente u : utenti) {
            if (u.getMatricola() == matricola) {
                risultato.add(u);
            }
        }
    } catch (NumberFormatException e) {
    }
    return risultato;
}
/**
 * @brief "Controlla se esiste un utente con la matricola indicata"
 * @author ALDO MALINCONICO
*/
public boolean checkMatricola(int matricola) {
    if (matricola <= 0) return false;
    for (Utente u : utenti) {
        if (u != null && u.getMatricola() == matricola) {
            return true; 
        }
    }
    return false; 
}

/**
 * @brief "inserire qui descrizione breve"
 * "Nella ricerca dei nomi converto tutto in minuscolo per avere una ricerca 
 * case-insensitive per garantire che l’utente ottenga match 
 * indipendentemente da come sono state inserite le maiuscole nel file"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
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
            nome = ""; // nessun nome disponibile → uso stringa vuota
        }
        if (u.getCognome() != null) {
            String cognomeOriginale = u.getCognome();     
            String cognomeMinuscolo = cognomeOriginale.toLowerCase(); 
            cognome = cognomeMinuscolo;                    
        } else {
            cognome = ""; // 
        }
        //Verifico se la query è contenuta nel nome o nel cognome
        boolean matchSuNome = nome.contains(query);
        boolean matchSuCognome = cognome.contains(query);
        //Se la query è presente in almeno uno dei due, aggiungo l'utente ai risultati
        if (matchSuNome || matchSuCognome) {
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
