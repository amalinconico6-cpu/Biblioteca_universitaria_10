/**
 * @file 
 * @brief
 * "Inserire qui descrizione specifica della classe"
 * @author ALDO MALINCONICO
 * @date
 * @version
 */

package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.utenti;

public class Utente {

    private String nome;
    private String cognome;
    private int matricola;
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
    public Utente(String nome, String cognome, int matricola, String mail) {
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
    public int getMatricola() {
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
    public void setMatricola(int matricola) {
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
        return this.matricola == altro.matricola;
    }
}
