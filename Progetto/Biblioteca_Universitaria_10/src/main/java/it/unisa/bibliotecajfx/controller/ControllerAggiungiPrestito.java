
/**
 * @file ControllerAggiungiPrestito.java
 * @brief Controller JavaFX per la creazione di un nuovo prestito.
 *
 * Questa classe gestisce la finestra di inserimento di un prestito.
 * inserisce ISBN del libro e matricola dell'utente (composta da prefisso + ultime 4 cifre),
 * seleziona una data di scadenza,
 * valida l'esistenza dell'utente e del libro,
 * controlla la disponibilità di copie del libro,
 * crea e aggiunge il prestito alla ListaPrestiti (max 3 prestiti per utente),
 * decrementa le copie del libro se l'inserimento va a buon fine,
 * salva prestiti e libri su file,
 * comunica al chiamante l'esito tramite il flag prestitoCreato.
 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.biblioteca.Libro;
import it.unisa.bibliotecajfx.model.biblioteca.Lista_Libri;
import it.unisa.bibliotecajfx.model.prestiti.Lista_Prestiti;
import it.unisa.bibliotecajfx.model.utenti.Lista_Utenti;
import it.unisa.bibliotecajfx.model.prestiti.Prestito;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerAggiungiPrestito {

    /**
     * @brief Nome del file per la persistenza dei prestiti
     */
    final String FILE_PRESTITI = "FileEsternoPrestiti.bin";

    /**
     * @brief Nome del file per la persistenza dei libri
     */
    final String FILE_LIBRI = "FileEsternoLibri.bin";

    /**
     * @brief Flag che indica se il prestito è stato creato correttamente
     */
    private boolean prestitoCreato = false;

    /**
     * @brief Restituisce se il prestito è stato creato
     * @return true se il prestito è stato inserito con successo, false altrimenti
     */
    public boolean isPrestitoCreato() {
        return prestitoCreato;
    }

    /**
     * @brief Campo testo per l'ISBN del libro
     */
    @FXML
    private TextField txtISBN;

    /**
     * @brief Campo testo per l'username email (parte prima del dominio)
     */
    @FXML
    private TextField txtEmail;

    /**
     * @brief Campo testo per le ultime 4 cifre della matricola
     */
    @FXML
    private TextField txtMatricola;

    /**
     * @brief Selettore della data di scadenza del prestito
     */
    @FXML
    private DatePicker dateScadenza;

    /**
     * @brief Pulsante per confermare l'inserimento del prestito
     */
    @FXML
    private Button btnAggiungi;

    /**
     * @brief Label che contiene il prefisso della matricola (parte iniziale)
     */
    @FXML
    private Label lblPrefisso;

    /**
     * @brief Riferimento alla lista prestiti 
     */
    private Lista_Prestiti listaPrestiti;

    /**
     * @brief Riferimento alla lista utenti 
     */
    private Lista_Utenti listaUtenti;

    /**
     * @brief Riferimento alla lista libri
     */
    private Lista_Libri listaLibri;

    /**
     * @brief Imposta le liste necessarie alla creazione del prestito
     * @param p lista dei prestiti
     * @param u lista degli utenti
     * @param l lista dei libri
     * @pre p != null && u != null && l != null
     * @post Il controller può validare dati e creare prestiti aggiornando i modelli
     */
    public void setListe(Lista_Prestiti p, Lista_Utenti u, Lista_Libri l) {
        this.listaPrestiti = p;
        this.listaUtenti = u;
        this.listaLibri = l;
    }

    /**
     * @brief Metodo di inizializzazione JavaFX (richiamato automaticamente)
     * Imposta i format per limitare l'input:
     * - Matricola (ultime 4 cifre): solo numeri, max 4
     * - ISBN: solo numeri, max 13
     * - Email username: solo caratteri consentiti (lettere, numeri, punto, underscore), max 50
     * @post I campi accettano solo input nel formato previsto
     */
    @FXML
    public void initialize() {

        // solo numeri, max 4
        txtMatricola.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,4}")) return change;
            return null;
        }));

        // ISBN: max 13 cifre
        txtISBN.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,13}")) return change;
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
     * @brief Conferma l'inserimento del prestito
     *
     * Verifica che le liste (utenti, libri, prestiti) siano inizializzate
     * Legge ISBN, data scadenza e costruisce la matricola completa (prefisso + ultime 4)
     * Controlla che i campi obbligatori siano compilati
     * Verifica che la matricola contenga solo numeri e che l'utente esista
     * Verifica che il libro esista e che ci siano copie disponibili
     * Crea il Prestito e tenta l'inserimento nella lista (max 3 prestiti per utente)
     * Se ok: decrementa copie, salva su file e chiude la finestra
     *
     * @throws IOException errore durante operazioni di I/O (salvataggio)
     * @pre listaPrestiti != null && listaUtenti != null && listaLibri != null
     * @post Se l'inserimento ha successo: prestitoCreato=true, copie decrementate e dati salvati
     */
    @FXML
    private void onAggiungi() throws IOException {

        if (listaUtenti == null || listaLibri == null || listaPrestiti == null) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Errore interno: dati non inizializzati");
            return;
        }

        String isbn = txtISBN.getText().trim();
        LocalDate scadenza = dateScadenza.getValue();

        String suffix = txtMatricola.getText().trim(); // ultimi 4 numeri
        String prefisso = (lblPrefisso.getText() == null) ? "" : lblPrefisso.getText().trim();

        // matricola completa
        String matricolaCompleta = (prefisso + suffix).replaceAll("\\s+", "");

        // controlli base
        if (suffix.isEmpty() || matricolaCompleta.isEmpty() || scadenza == null || isbn.isEmpty()) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Compila tutti i campi!");
            return;
        }

        if (!matricolaCompleta.matches("\\d+")) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "La matricola deve contenere solo numeri");
            return;
        }

        if (!listaUtenti.checkMatricola(matricolaCompleta)) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Utente non presente");
            return;
        }

        // Libro deve esistere
        if (!listaLibri.checkISBN(isbn)) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "ISBN non trovato!");
            return;
        }

        // Recupero oggetto Libro (per gestire copie)
        Libro libro = null;
        for (Libro l : listaLibri.getLibri()) {
            if (l != null && l.getISBN() != null && l.getISBN().equals(isbn)) {
                libro = l;
                break;
            }
        }

        if (libro == null) {
            // caso raro: checkISBN true ma non lo trovo
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Errore interno: libro non trovato in lista.");
            return;
        }

        if (libro.getCopie() <= 0) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Nessuna copia disponibile");
            return;
        }

        // Creo prestito
        Prestito p = new Prestito(isbn, matricolaCompleta, scadenza);

        // Inserimento (regola max 3 prestiti)
        boolean ok = listaPrestiti.aggiungiPrestito(p);

        if (ok) {
            libro.setCopie(libro.getCopie() - 1);
            listaPrestiti.salvataggioPrestiti(FILE_PRESTITI);
            listaLibri.salvataggioLibri(FILE_LIBRI);
            prestitoCreato = true;

            ControllerPopup.showSuccess(txtISBN.getScene().getWindow(),
                    "Prestito inserito correttamente! ");
            chiudiFinestra();
        } else {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "L'utente ha gi� 3 prestiti! ");
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
        Stage stage = (Stage) txtMatricola.getScene().getWindow();
        stage.close();
    }
}
