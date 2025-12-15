
package it.unisa.bibliotecajfx.model.utenti;

import java.io.Serializable;
import java.util.Objects;

/**
 * @file Utente.java
 * @author ALDO_MALINCONICO
 */

import java.io.Serializable;
import java.util.Objects;

public class Utente implements Serializable{
    private String nome;
    private String cognome;
    private String matricola;
    private String mail;

    /**
     * @brief costruttore classe Utente
     * "inserire qui descrizione specifica del metodo"
     * @pre i parametri devono essere validi
     * @post viene creato un nuovo oggetto utente
     * @param[in] nome cognome matricola mail
     * @author ALDO MALINCONICO
     */
    
    public Utente(String nome, String cognome, String matricola, String mail) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.mail = mail;
    }

    /**
     * @brief "Restituisce il nome dell'utente"
     * @return nome utente
     * @author ALDO MALINCONICO
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @brief "Imposta il nome dell'utente"
     * @pre il parametro nome non deve essere null
     * @post il parametro nome viene aggiornato
     * @param[in] nome
     * @author ALDO MALINCONICO
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @brief "Restituisce cognome dell'utente"
     * @return cognome
     * @author ALDO MALINCONICO
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * @brief "Imposta cognome utente"
     * @pre il parametro non deve essere null
     * @post l'attributo cognome viene aggiornato
     * @param[in] cognome
     * @author ALDO MALINCONICO
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @brief "restituisce la matricola dell'utente"
     * @return matricola utente
     * @author ALDO MALINCONICO
     */
    public String getMatricola() {
        return this.matricola;
    }

    /**
     * @brief "Imposta la matricola dell'utente"
     * @pre il parametro deve essere valido
     * @post il parametro matricola viene aggiornato
     * @param[in] matricola
     * @author ALDO MALINCONICO
     */
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    /**
     * @brief "restituisce mail utente"
     * @return mail
     * @author ALDO MALINCONICO
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * @brief "imposta email utente"
     * @pre il parametro mail deve essere valido
     * @post il parametro mail viene aggiornato
     * @param[in] mail
     * @author ALDO MALINCONICO
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @brief "confronta due utenti sulla matricola"
     * "Due utenti sono uguali se hanno la stessa matricola"
     * @param[in] oggetto da confrontare
     * @return true se due utenti hanno la stessa matricola,false altrimenti
     * @author ALDO_MALINCONICO
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  
        if (o == null || getClass() != o.getClass()) return false;

        Utente altro = (Utente) o;
        return this.matricola.equals(altro.matricola);
    }
     @Override
    public int hashCode() {
        return Objects.hash(matricola);
    }
}
