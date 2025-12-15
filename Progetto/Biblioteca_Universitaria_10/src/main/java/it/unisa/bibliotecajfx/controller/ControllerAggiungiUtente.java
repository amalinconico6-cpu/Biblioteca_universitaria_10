/**
 * @file ControllerAggiungiUtente.java
 * @brief Controller JavaFX per l'aggiunta di un nuovo utente.
 *
 * Questa classe gestisce la finestra di inserimento di un nuovo utente.
 * inserisce nome, cognome, ultime 4 cifre della matricola ed username email,
 * valida i campi con controlli su formato e caratteri consentiti,
 * compone la matricola completa con prefisso fisso,
 * compone l'email completa con dominio istituzionale,
 * aggiunge l'utente alla ListaUtenti e salvarlo su file,
 * chiude la finestra in caso di conferma o annullamento.
 *
 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.utenti.Lista_Utenti;
import it.unisa.bibliotecajfx.model.utenti.Utente;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControllerAggiungiUtente {

    /**
     * @brief Nome del file binario per la persistenza degli utenti
     */
    final String FILE_UTENTI = "FileEsternoUtenti.bin";

    /**
     * @brief Prefisso fisso della matricola (prime cifre)
     * La matricola completa viene costruita concatenando questo prefisso
     * con le ultime 4 cifre inserite dall'utente.
     */
    private static final String PREFISSO_MATRICOLA = "06127";

    /**
     * @brief Dominio istituzionale per le email degli studenti
     * L'email completa viene costruita concatenando l'username inserito
     * con questo dominio.
     */
    private static final String DOMINIO_EMAIL = "@studenti.unisa.it";

    /**
     * @brief Riferimento alla lista utenti
     * Deve essere impostato dall'esterno tramite setListaUtenti().
     */
    private Lista_Utenti listaUtenti;

    /**
     * @brief Imposta la lista utenti usata dal controller
     * @param listaUtenti 
     * @pre listaUtenti != null
     * @post Il controller può operare sul modello ListaUtenti
     */
    public void setListaUtenti(Lista_Utenti listaUtenti) {
        this.listaUtenti = listaUtenti;
    }

    /**
     * @brief Campo testo del nome dell'utente
     */
    @FXML
    private TextField txtNome;

    /**
     * @brief Campo testo del cognome dell'utente
     */
    @FXML
    private TextField txtCognome;

    /**
     * @brief Campo testo delle ultime 4 cifre della matricola
     */
    @FXML
    private TextField txtMatricola;

    /**
     * @brief Campo testo dell'username email (parte prima del dominio)
     */
    @FXML
    private TextField txtEmail;

    /**
     * @brief Pulsante per confermare l'inserimento dell'utente
     */
    @FXML
    private Button btnAggiungi;

    /**
     * @brief Pulsante per annullare e chiudere la finestra
     */
    @FXML
    private Button btnCancella;

    /**
     * @brief Metodo di inizializzazione JavaFX
     * Imposta i format per limitare l'input:
     * Matricola: solo cifre, massimo 4 caratteri
     * Email username: solo caratteri consentiti (lettere, numeri, punto, underscore), max 50
     * @post I campi Matricola ed Email accettano solo input nel formato previsto
     */
    @FXML
    public void initialize() {

        // solo numeri, max 4
        txtMatricola.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,4}")) return change;
            return null;
        }));

        // email username: solo a-z A-Z 0-9 . _
        txtEmail.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z0-9._]{0,50}")) return change;
            return null;
        }));
    }

    /**
     * @brief Aggiunge un nuovo utente al sistema
     * Valida i campi inseriti:
     * tutti i campi obbligatori
     * nome e cognome: solo lettere, spazi e apostrofo
     * matricola: esattamente 4 cifre (ultime cifre)
     * email username: solo lettere, numeri, punto e underscore
     * Poi costruisce:
     * - matricola completa: PREFISSO_MATRICOLA + ultime4
     * - email completa: emailUser + DOMINIO_EMAIL
     * Infine crea l'oggetto Utente e tenta l'inserimento nella lista.
     * Se l'inserimento va a buon fine, salva su file e chiude la finestra.
     * @param event
     * @throws IOException errore durante operazioni di I/O (salvataggio)
     * @pre listaUtenti != null
     * @post Se l'inserimento ha successo l'utente salvato su file e finestra chiusa
     */
    @FXML
    private void onAggiungi(ActionEvent event) throws IOException {

        if (listaUtenti == null) {
            ControllerPopup.showError(txtNome.getScene().getWindow(),
                    "Errore interno: lista utenti non inizializzata.");
            return;
        }

        String nome = txtNome.getText().trim();
        String cognome = txtCognome.getText().trim();
        String ultime4 = txtMatricola.getText().trim();
        String emailUser = txtEmail.getText().trim();

        if (nome.isEmpty() || cognome.isEmpty() || ultime4.isEmpty() || emailUser.isEmpty()) {
            ControllerPopup.showError(txtNome.getScene().getWindow(),
                    "Compila tutti i campi :( ");
            return;
        }

        // Nome e cognome: solo lettere/spazi/apostrofo
        String regexNome = "^[\\p{L}' ]+$";;
        if (!nome.matches(regexNome)) {
            ControllerPopup.showError(txtNome.getScene().getWindow(),
                    "Nome non valido: non deve contenere numeri o simboli.");
            return;
        }
        if (!cognome.matches(regexNome)) {
            ControllerPopup.showError(txtNome.getScene().getWindow(),
                    "Cognome non valido: non deve contenere numeri o simboli.");
            return;
        }

        // Matricola: solo 4 cifre
        if (!ultime4.matches("\\d{4}")) {
            ControllerPopup.showError(txtNome.getScene().getWindow(),
                    "Matricola non valida: inserisci esattamente 4 cifre finali.");
            return;
        }
        String matricolaCompletaStr = PREFISSO_MATRICOLA + ultime4;

        try {
            String matricola = matricolaCompletaStr;
        } catch (NumberFormatException e) {
            ControllerPopup.showError(txtNome.getScene().getWindow(),
                    "Matricola non valida");
            return;
        }

        // Email username: solo caratteri consentiti
        if (!emailUser.matches("^[a-zA-Z0-9._]+$")) {
            ControllerPopup.showError(txtNome.getScene().getWindow(),
                    "Email non valida: usa solo lettere, numeri, punto e underscore.");
            return;
        }
        String email = emailUser + DOMINIO_EMAIL;

        Utente u = new Utente(nome, cognome, matricolaCompletaStr, email);
        String esito = listaUtenti.aggiungiUtente(u);

        if ("Utente aggiunto correttamente".equals(esito)) {
            listaUtenti.salvataggioUtenti(FILE_UTENTI);
            ControllerPopup.showSuccess(txtNome.getScene().getWindow(),
                    esito);
            chiudiFinestra();
        } else {
            ControllerPopup.showError(txtNome.getScene().getWindow(),
                    esito);
        }
    }

    /**
     * @brief Annulla l'operazione e chiude la finestra
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
