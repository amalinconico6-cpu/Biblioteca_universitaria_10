/**
 * @file 
 * @brief
 * "Inserire qui descrizione specifica della classe"
 * @author ALDO MALINCONICO
 * @date
 * @version
 */

package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.utenti;

import java.io.Serializable;
import java.util.Objects;

public class Utente implements Serializable{
   private static final long serialVersionUID = 1L;
    private String nome;
    private String cognome;
    private String matricola;
    private String mail;

    /**
     * @brief "inserire qui descrizione breve"
     * "inserire qui descrizione specifica del metodo"
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @author ALDO MALINCONICO
     */
    
    public Utente(String nome, String cognome, String matricola, String mail) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.mail = mail;
    }

    /**
     * @brief "inserire qui descrizione breve"
     * "inserire qui descrizione specifica del metodo"
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @return 
     * @author ALDO MALINCONICO
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @brief "inserire qui descrizione breve"
     * "inserire qui descrizione specifica del metodo"
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @author ALDO MALINCONICO
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
     * @author ALDO MALINCONICO
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * @brief "inserire qui descrizione breve"
     * "inserire qui descrizione specifica del metodo"
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @author ALDO MALINCONICO
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
     * @author ALDO MALINCONICO
     */
    
    public String getMatricola() {
        return this.matricola;
    }

    /**
     * @brief "inserire qui descrizione breve"
     * "inserire qui descrizione specifica del metodo"
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @author ALDO MALINCONICO
     */
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    /**
     * @brief "inserire qui descrizione breve"
     * "inserire qui descrizione specifica del metodo"
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @return 
     * @author ALDO MALINCONICO
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * @brief "inserire qui descrizione breve"
     * "inserire qui descrizione specifica del metodo"
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @author ALDO MALINCONICO
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @brief "inserire qui descrizione breve"
     * "inserire qui descrizione specifica del metodo"
     * @pre
     * @post
     * @param[in]
     * @param[out]
     * @return 
     * @author ALDO MALINCONICO
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
