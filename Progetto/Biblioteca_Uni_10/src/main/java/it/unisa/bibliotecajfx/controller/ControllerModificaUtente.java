/**
 * @file ControllerModificaUtente.java
 * @brief Controller JavaFX per la modifica dei dati di un utente.
 *
 * Questa classe gestisce la finestra "Modifica Utente".
 * visualizza i dati dell'utente selezionato (nome, cognome, matricola, email),
 * modifica nome, cognome ed email,
 * mantiene la matricola non modificabile (chiave univoca),
 * salva le modifiche nella ListaUtenti e su file,
 * chiude la finestra (annulla o dopo salvataggio).
 *
 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.utenti.Lista_Utenti;
import it.unisa.bibliotecajfx.model.utenti.Utente;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;


public class ControllerModificaUtente {

    /**
     * @brief Nome del file binario per la persistenza degli utenti
     */
    final String FILE_UTENTI = "FileEsternoUtenti.bin";

    /**
     * @brief Pulsante per confermare la modifica dell'utente
     */
    @FXML
    private Button btnModificaUtente;

    /**
     * @brief Campo testo del nome utente
     */
    @FXML
    private TextField txtNome;

    /**
     * @brief Campo testo del cognome utente
     */
    @FXML
    private TextField txtCognome;

    /**
     * @brief Campo testo della matricola 
     */
    @FXML
    private TextField txtMatricola;

    /**
     * @brief Campo testo dell'email utente
     */
    @FXML
    private TextField txtEmail;

    /**
     * @brief Modello: lista utenti
     *
     * Deve essere impostato dall'esterno tramite setListaUtenti().
     */
    private Lista_Utenti listaUtenti;

    /**
     * @brief Imposta la lista utenti usata dal controller
     *
     * @param listaUtenti 
     * @pre listaUtenti != null
     * @post Il controller può salvare le modifiche nella lista
     */
    public void setListaUtenti(Lista_Utenti listaUtenti) {
        this.listaUtenti = listaUtenti;
    }

    /**
     * @brief Utente attualmente selezionato da modificare
     */
    private Utente utente;

    /**
     * @brief Imposta l'utente da modificare e popola i campi della form
     *
     * Carica nei TextField i valori correnti dell'utente.
     * La matricola viene impostata non modificabile perchè chiave univoca.
     *
     * @param utente 
     * @pre utente != null
     * @post I campi della UI mostrano i dati dell'utente; la matricola � disabilitata
     */
    public void setUtente(Utente utente) {
        this.utente = utente;

        txtNome.setText(utente.getNome());
        txtCognome.setText(utente.getCognome());
        txtMatricola.setText(utente.getMatricola());
        txtEmail.setText(utente.getMail());
    }
    
  
    
    /**
     * @brief Salva la modifica dell'utente
     *
     * Legge i campi (nome, cognome, email), controlla che non siano vuoti,
     * aggiorna l'oggetto Utente, invoca la modifica nel modello e salva su file.
     * Infine chiude la finestra.
     *
     * @param event 
     * @throws IOException errore durante operazioni di I/O (salvataggio)
     * @pre utente != null && listaUtenti != null
     * @post Dati dell'utente aggiornati nella lista e salvati su file; finestra chiusa
     */
    @FXML
    private void onModificaUtente(ActionEvent event) throws IOException {
        if (utente == null) return;

        String nome = txtNome.getText().trim();
        String cognome = txtCognome.getText().trim();
        String email = txtEmail.getText().trim();

        if (nome.isEmpty() || cognome.isEmpty() || email.isEmpty()) {
            return; // stesso stile ModificaLibro (niente popup qui)
        }

        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setMail(email);

        if (utente == null || listaUtenti == null) return;

        listaUtenti.modificaUtente(utente);
        listaUtenti.salvataggioUtenti(FILE_UTENTI);
        chiudiFinestra();
    }

    /**
     * @brief Annulla l'operazione di modifica
     */
    @FXML
    private void onCancella() {
        chiudiFinestra();
    }

    /**
     * @brief Chiude la finestra corrente
     */
    private void chiudiFinestra() {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
}