/**
 * @file ControllerModificaLibro.java
 * @brief Controller JavaFX per la modifica di un libro e della sua lista di autori.
 *
 * Questa classe gestisce la finestra "Modifica Libro" (versione con gestione completa autori).
 * carica i dati del libro selezionato (titolo, ISBN, anno, autori),
 * gestisce una lista temporanea di autori (aggiunta/rimozione) tramite ListView,
 * valida i campi prima del salvataggio,
 * aggiorna l'oggetto Libro in memoria e salvare la ListaLibri su file,
 * chiude la finestra (annulla o dopo salvataggio).
 *
 * Gli autori vengono gestiti tramite un buffer temporaneo (autoriTemp) popolato da setLibro()
 * e mostrato nella ListView tramite refreshListaAutori(). Alla conferma della modifica,
 * la lista autori del libro viene aggiornata con una nuova ArrayList<>(autoriTemp).
 *
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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;


public class ControllerModificaLibro {

    /**
     * @brief Modello: lista libri
     * Deve essere impostato dall'esterno tramite setListaLibri().
     */
    private Lista_Libri listalibri;

    /**
     * @brief Libro attualmente selezionato da modificare
     */
    private Libro libro;

    /**
     * @brief Buffer temporaneo degli autori del libro
     * Viene popolato da setLibro() e modificato tramite onAggiungiAutore/onRimuoviAutore.
     */
    private final ObservableList<Autore> autoriTemp = FXCollections.observableArrayList();

    /**
     * @brief Nome del file binario per la persistenza dei libri
     */
    final String FILE_LIBRI = "FileEsternoLibri.bin";

    /**
     * @brief Pulsante per confermare la modifica del libro
     */
    @FXML
    private Button btnModificaLibro;

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
     * @brief Campo testo dell'anno di pubblicazione
     */
    @FXML
    private TextField txtAnno;

    /**
     * @brief Campo testo del codice ISBN
     */
    @FXML
    private TextField txtISBN;

    /**
     * @brief ListView che mostra gli autori attualmente associati al libro
     */
    @FXML
    private ListView<String> listAutori;

    /**
     * @brief Imposta il libro da modificare e popola i campi della form
     *
     * Carica nei TextField:
     * titolo
     * ISBN
     * anno
     *
     * @param libro 
     * @pre libro != null
     * @post I campi della UI sono valorizzati e autoriTemp contiene gli autori del libro
     */
    @FXML
    public void setLibro(Libro libro) {
        this.libro = libro;

        txtTitolo.setText(libro.getTitolo());
        txtISBN.setText(libro.getISBN());
        txtAnno.setText(String.valueOf(libro.getAnno()));

        autoriTemp.clear();
        if (libro.getAutori() != null) {
            autoriTemp.addAll(libro.getAutori());
        }
        refreshListaAutori();

        txtNomeAutore.clear();
        txtCognomeAutore.clear();
    }

    /**
     * @brief Imposta la lista libri usata dal controller
     *
     * Necessaria per salvare le modifiche su file.
     *
     * @param listalibri 
     * @pre listalibri != null
     * @post Il controller può salvare la lista su file
     */
    public void setListaLibri(Lista_Libri listalibri) {
        this.listalibri = listalibri;
    }

    /**
     * @brief Aggiorna la ListView degli autori in base a autoriTemp
     *
     * Converte ogni Autore in stringa "Nome Cognome" (saltando null e stringhe vuote)
     * e imposta gli elementi della ListView.
     *
     * @pre listAutori != null
     * @post listAutori mostra gli autori attualmente presenti in autoriTemp
     */
    private void refreshListaAutori() {
        List<String> view = new ArrayList<>();
        for (Autore a : autoriTemp) {
            if (a == null) continue;
            String nome = a.getNome() == null ? "" : a.getNome().trim();
            String cognome = a.getCognome() == null ? "" : a.getCognome().trim();
            String s = (nome + " " + cognome).trim();
            if (!s.isEmpty()) view.add(s);
        }
        listAutori.setItems(FXCollections.observableArrayList(view));
    }

    /**
     * @brief Metodo di inizializzazione JavaFX (richiamato automaticamente)
     *
     * Imposta i TextFormatter per limitare l'input:
     * ISBN: massimo 13 cifre numeriche
     * Anno: massimo 4 cifre numeriche
     *
     * @post I campi ISBN e Anno accettano solo input numerico con lunghezza massima valida
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
     * @brief Aggiunge un autore al buffer temporaneo autoriTemp
     *
     * Legge nome e cognome dai TextField, controlla che non siano vuoti,
     * evita duplicati (basandosi su equals di Autore), aggiorna la ListView
     * e pulisce i campi di input.
     *
     * @pre Nome e Cognome autore compilati
     * @post Autore aggiunto a autoriTemp e listAutori aggiornata
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

        if (autoriTemp.contains(nuovo)) {
            ControllerPopup.showError(txtNomeAutore.getScene().getWindow(),
                    "Autore già inserito.");
            return;
        }

        autoriTemp.add(nuovo);
        refreshListaAutori();

        txtNomeAutore.clear();
        txtCognomeAutore.clear();
    }

    /**
     * @brief Rimuove l'autore selezionato dalla ListView e dal buffer autoriTemp
     *
     * Usa l'indice selezionato nella ListView per rimuovere l'elemento corrispondente
     * in autoriTemp, poi aggiorna la ListView.
     *
     * @pre Deve essere selezionato un autore in listAutori
     * @post Autore rimosso da autoriTemp e listAutori aggiornata
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
        refreshListaAutori();
    }

    /**
     * @brief Salva la modifica del libro e dei suoi autori
     *
     * Esegue validazioni:
     * titolo/anno/isbn non vuoti
     * presenza di almeno un autore in autoriTemp
     * titolo deve contenere almeno una lettera
     * anno deve essere composto da 4 cifre ed essere un intero
     * ISBN deve essere composto da 13 cifre
     *
     * Se valido:
     * aggiorna i campi del libro (titolo, ISBN, anno)
     * aggiorna la lista autori del libro con autoriTemp
     * salva la lista libri su file
     * chiude la finestra
     *
     * @param event 
     * @throws IOException errore durante operazioni di I/O (salvataggio)
     * @pre libro != null && listalibri != null
     * @post Libro aggiornato e lista salvata su file; finestra chiusa
     */
    @FXML
    private void onModificaLibro(ActionEvent event) throws IOException {
 
        if (libro == null) {
            return;
        }

        String titolo = txtTitolo.getText().trim();
        String annoStr = txtAnno.getText().trim();
        String isbn = txtISBN.getText().trim();


        if (titolo.isEmpty() || annoStr.isEmpty() || isbn.isEmpty()) {
         
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Compila tutti i campi (Titolo, Autore, Anno, ISBN).");
            return;
        }

        if (autoriTemp.isEmpty()) {
            ControllerPopup.showError(txtTitolo.getScene().getWindow(),
                    "Inserisci almeno un autore.");
            return;
        }
        
if (!titolo.chars().anyMatch(Character::isLetter)) {
    ControllerPopup.showError(txtISBN.getScene().getWindow(),
        "Il titolo deve contenere almeno una lettera");
    return;
}

        if (!annoStr.matches("\\d{4}")) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "L'anno di pubblicazione deve essere composto da 4 cifre (es. 2024).");
            return;
        }

        int anno;
        try {
            anno = Integer.parseInt(annoStr);
        } catch (NumberFormatException e) {
            ControllerPopup.showError(txtAnno.getScene().getWindow(),
                    "Anno inserito non valido");
            return;
        }

        if (!isbn.matches("\\d{13}")) {
            ControllerPopup.showError(txtISBN.getScene().getWindow(),
                    "Il codice ISBN deve contenere esattamente 13 cifre.");
            return;
        }

        libro.setTitolo(titolo);
        libro.setISBN(isbn);
        libro.setAnno(anno);
        libro.setAutori(new ArrayList<>(autoriTemp));

        listalibri.salvataggioLibri(FILE_LIBRI);
        chiudiFinestra();
    }

    /**
     * @brief Annulla l'operazione di modifica
     *
     * Chiude la finestra senza salvare.
     */
    @FXML
    private void onCancella() {
        chiudiFinestra();
    }

    /**
     * @brief Chiude la finestra corrente
     *
     * Recupera lo Stage dalla scena associata al campo txtTitolo e chiude lo stage.
     */
    private void chiudiFinestra() {
        Stage stage = (Stage) txtTitolo.getScene().getWindow();
        stage.close();
    }
}
