
/**
 * @file ControllerAggiungiLibro.java
 * @brief Controller JavaFX per l'aggiunta di un nuovo libro
 *
 * Questa classe gestisce la finestra di inserimento di un nuovo libro.
 * inserisce titolo, anno e ISBN,
 * gestisce una lista temporanea di autori (aggiunta/rimozione),
 * valida i campi
 * aggiunge un nuovo libro nella ListaLibri e salvarlo su file,
 * se l'ISBN √® gi√† presente, incrementare le copie del libro esistente.
 *
 * La lista degli autori viene gestita tramite un buffer temporaneo
 * che viene mostrato nella ListView.
 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.biblioteca.Autore;
import it.unisa.bibliotecajfx.model.biblioteca.Libro;
import it.unisa.bibliotecajfx.model.biblioteca.Lista_Libri;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerAggiungiLibro {

    /**
     * @brief Nome del file per il salvataggio dei libri
     */
    final String FILE_LIBRI = "FileEsternoLibri.bin";

    /**
     * @brief Riferimento alla lista libri 
     */
    private Lista_Libri listalibri;

    /**
     * @brief Imposta la lista libri usata dal controller
     * @param listalibri
     * @pre listalibri != null
     * @post Il controller pu√≤ operare su ListaLibri
     */
    public void setListaLibri(Lista_Libri listalibri) {
        this.listalibri = listalibri;
    }

    /**
     * @brief Campo testo del titolo del libro
     */
    @FXML
    private TextField txtTitolo;

    /**
     * @brief Campo testo del nome autore 
     */
    @FXML
    private TextField txtNomeAutore;

    /**
     * @brief Campo testo del cognome autore
     */
    @FXML
    private TextField txtCognomeAutore;

    /**
     * @brief Campo dell'anno di pubblicazione
     */
    @FXML
    private TextField txtAnno;

    /**
     * @brief Campo del codice ISBN
     */
    @FXML
    private TextField txtISBN;

    /**
     * @brief Pulsante per confermare l'aggiunta del libro
     */
    @FXML
    private Button btnAggiungiLibro;

    /**
     * @brief Pulsante per aggiungere un autore alla lista
     */
    @FXML
    private Button btnAggiungiAutore;

    /**
     * @brief Pulsante per rimuovere l'autore selezionato dalla lista 
     */
    @FXML
    private Button btnRimuoviAutore;

    /**
     * @brief ListView che mostra gli autori attualmente inseriti
     */
    @FXML
    private ListView<String> listAutori;

    /**
     * @brief Buffer temporaneo degli autori inseriti
     */
    private final ObservableList<Autore> autoriTemp = FXCollections.observableArrayList();

    /**
     * @brief Metodo di inizializzazione JavaFX 
     * Imposta i format per limitare l'input:
     * - ISBN: massimo 13 cifre numeriche
     * - Anno: massimo 4 cifre numeriche
     * @post I campi ISBN e Anno accettano solo numeri con una lunghezza massima valida
     */
    
    @FXML
    public void initialize() {

        txtISBN.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,13}")) return change;
            return null;
        }));

        txtAnno.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,4}")) return change;
            return null;
        }));
    }

    /**
     * @brief Aggiunge un autore alla lista temporanea
     * Legge nome e cognome dai TextField, valida i campi,
     * evita duplicatie aggiorna la ListView.
     * @pre I campi nome e cognome devono essere compilati
     * @post Se valido e non duplicato, l'autore viene aggiunto ad autoriTemp e mostrato in listAutori
     */
    
    @FXML
    private void onAggiungiAutore() {
        String nome = txtNomeAutore.getText() == null ? "" : txtNomeAutore.getText().trim();
        String cognome = txtCognomeAutore.getText() == null ? "" : txtCognomeAutore.getText().trim();

        if (nome.isEmpty() || cognome.isEmpty()) {
            ControllerPopup.showError(txtNomeAutore.getScene().getWindow(),
                    "Inserisci Nome e Cognome dell'autore.");
            return;
        }

        Autore nuovo = new Autore(nome, cognome);

        // evita duplicati (equals di Autore gi√† confronta nome+cognome)
        if (autoriTemp.contains(nuovo)) {
            ControllerPopup.showError(txtNomeAutore.getScene().getWindow(),
                    "Autore gi‡† inserito.");
            return;
        }

        // nome e cognome devono contenere almeno una lettera
         if (!nome.matches(".*\\p{L}.*") || !cognome.matches(".*\\p{L}.*")){
            ControllerPopup.showError(txtNomeAutore.getScene().getWindow(),
                    "Nome e Cognome autore devono contenere lettere.");
            return;
        }

        autoriTemp.add(nuovo);

        // aggiorna ListView
        listAutori.getItems().setAll(
                autoriTemp.stream()
                        .map(a -> a.getNome() + " " + a.getCognome())
                        .collect(Collectors.toList())
        );

        txtNomeAutore.clear();
        txtCognomeAutore.clear();
    }
    
    /**
     * @brief Rimuove l'autore selezionato dalla lista
     * Rimuove dall'ObservableList l'autore corrispondente alla selezione nella ListView
     * e aggiorna il contenuto visibile.
     * @pre Deve essere selezionato un autore in listAutori
     * @post L'autore selezionato viene rimosso da autoriTemp e listAutori viene aggiornata
     */
    
    @FXML
    private void onRimuoviAutore() {
        int idx = listAutori.getSelectionModel().getSelectedIndex();
        if (idx < 0) {
            ControllerPopup.showError(listAutori.getScene().getWindow(),
                    "Seleziona un autore da rimuovere.");
            return;
        }

        autoriTemp.remove(idx);

        listAutori.getItems().setAll(
                autoriTemp.stream()
                        .map(a -> a.getNome() + " " + a.getCognome())
                        .collect(Collectors.toList())
        );
    }

    /**
     * @brief Aggiunge un libro alla lista (o incrementa copie se ISBN gi√† presente)
     * Esegue validazioni sui campi:
     * - titolo, anno e ISBN obbligatori
     * - titolo deve contenere almeno una lettera
     * - deve essere presente almeno un autore in autoriTemp
     * - anno deve essere composto da 4 cifre ed essere un intero
     * - ISBN deve essere composto da esattamente 13 cifre
     * Se l'ISBN esiste gi√†, incrementa le copie del libro esistente e salva su file.
     * Se l'ISBN non esiste, crea un nuovo Libro (copie=1) e lo inserisce nella lista
     * @param event 
     * @throws IOException Errore durante operazioni di I/O (salvataggio)
     * @pre listalibri != null
     * @post Se valido il libro √® aggiunto oppure copie incrementate; i dati sono salvati su file
     */
    @FXML
    private void onAggiungiLibro(ActionEvent event) throws IOException {

        if (listalibri == null) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Errore interno: lista libri non inizializzata.");
            return;
        }

        String titolo = (txtTitolo.getText() == null) ? "" : txtTitolo.getText().trim();
        String annoStr = (txtAnno.getText() == null) ? "" : txtAnno.getText().trim();
        String isbn = (txtISBN.getText() == null) ? "" : txtISBN.getText().trim();

        // Campi obbligatori (autore NON √® un campo singolo: si usa la lista)
        if (titolo.isEmpty() || annoStr.isEmpty() || isbn.isEmpty()) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Compila tutti i campi (Titolo, Anno, ISBN).");
            return;
        }

        // Titolo deve contenere almeno una lettera
if (!titolo.matches(".*\\p{L}.*")) {
    ControllerPopup.showError(txtISBN.getScene().getWindow(),
            "Il titolo deve contenere almeno una lettera (non solo numeri).");
    return;
}


        // Deve esserci almeno 1 autore
        if (autoriTemp.isEmpty()) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Aggiungi almeno un autore con il pulsante +.");
            return;
        }

        // Anno: 4 cifre
        if (!annoStr.matches("\\d{4}")) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "L'anno di pubblicazione deve essere composto da 4 cifre (es. 2024).");
            return;
        }

        int anno;
        try {
            anno = Integer.parseInt(annoStr);
        } catch (NumberFormatException e) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "L'anno deve essere un numero intero.");
            return;
        }

        // ISBN: 13 cifre
        if (!isbn.matches("\\d{13}")) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Il codice ISBN deve contenere esattamente 13 cifre.");
            return;
        }

        // Se ISBN gi√† presente -> aumenta copie e salva
        if (listalibri.checkISBN(isbn)) {

            Libro esistente = null;
            for (Libro l : listalibri.getLibri()) {
                if (l != null && l.getISBN() != null && l.getISBN().equals(isbn)) {
                    esistente = l;
                    break;
                }
            }

            if (esistente == null) {
                ControllerPopup.showError(txtISBN.getScene().getWindow(),
                        "Errore interno: ISBN presente ma libro non trovato in lista.");
                return;
            }
            
               boolean uguali = stessiDati(
            esistente,
            titolo,
            anno,
            new ArrayList<>(autoriTemp)
    );
               if (!uguali) {
        ControllerPopup.showError(txtISBN.getScene().getWindow(),
                "ISBN gi‡ presente con dati diversi.\n"
              + "Non puoi inserire un altro libro: modifica quello esistente.");
        return;
    }


            esistente.setCopie(esistente.getCopie() + 1);
            listalibri.salvataggioLibri(FILE_LIBRI);

            ControllerPopup.showSuccess(txtISBN.getScene().getWindow(),
                    "Libro gi‡† presente: copie aumentate di 1!");

            pulisciCampiLibro();
            return;
        }

        // Creo lista autori dal buffer temporaneo
        List<Autore> autori = new ArrayList<>(autoriTemp);

        // Creo Libro (copie=1, disponibile=true se copie>0)
        int copie = 1;
        boolean disponibile = copie > 0;

        Libro nuovo = new Libro(titolo, autori, isbn, anno, disponibile, copie);

        // Aggiungo e salvo
        listalibri.aggiungiLibro(nuovo);
        listalibri.salvataggioLibri(FILE_LIBRI);

        ControllerPopup.showSuccess(txtISBN.getScene().getWindow(),
                "Libro aggiunto correttamente! Buona lettura :)");

        pulisciCampiLibro();
    }

    /**
     * @brief Pulisce i campi e resetta la lista degli autori
     * Svuota tutti i TextField, svuota autoriTemp e la ListView associata.
     * @post Tutti i campi della form risultano vuoti e la lista autori ÔøΩ resettata
     */
    private void pulisciCampiLibro() {
        txtTitolo.clear();
        txtAnno.clear();
        txtISBN.clear();

        txtNomeAutore.clear();
        txtCognomeAutore.clear();

        autoriTemp.clear();
        listAutori.getItems().clear();
    }
    
    /**
     * @brief Verifica se i dati di un libro esistente coincidono con quelli inseriti
     * Questo metodo confronta i dati fondamentali di un libro gi‡ presente con quelli forniti in fase di inserimento di un libro.
     * Il metodo viene utilizzato per garantire l'integrit‡ dei dati associati a un ISBN, impedendo l'inserimento di libri
     * con lo stesso ISBN ma con dati diversi
     * @post 
     */
    
    
    private boolean stessiDati(Libro esistente, String titolo, int anno, List<Autore> autoriNuovi) {

    if (!esistente.getTitolo().trim().equalsIgnoreCase(titolo.trim()))
        return false;

    if (esistente.getAnno() != anno)
        return false;

    if (esistente.getAutori().size() != autoriNuovi.size())
        return false;

    List<String> a1 = esistente.getAutori().stream()
            .map(a -> (a.getNome() + " " + a.getCognome()).toLowerCase().trim())
            .sorted()
            .collect(Collectors.toList());

    List<String> a2 = autoriNuovi.stream()
            .map(a -> (a.getNome() + " " + a.getCognome()).toLowerCase().trim())
            .sorted()
            .collect(Collectors.toList());

    return a1.equals(a2);
}



    /**
     * @brief Gestisce l'azione di cancellazione/annullamento
     */
    @FXML
    private void onCancella() {
        chiudiFinestra();
    }

    /**
     * @brief Chiude la finestra corrente
     */
    private void chiudiFinestra() {
        Stage stage = (Stage) txtTitolo.getScene().getWindow();
        stage.close();
    }
}
