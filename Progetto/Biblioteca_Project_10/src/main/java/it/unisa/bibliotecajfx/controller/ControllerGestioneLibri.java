
/**
 * @file ControllerGestioneLibri.java
 * @brief Controller JavaFX per la gestione dei libri.
 *
 * Questa classe gestisce la schermata "Gestione Libri".
 * visualizza i libri nella TableView,
 * cerca libri per titolo, autore o ISBN,
 * resette il filtro di ricerca,
 * apre la finestra di modifica del libro selezionato,
 * rimuove il libro selezionato e salvare su file,
 * apre la finestra con la lista completa dei libri,
 * apre la finestra per aggiungere un nuovo libro,
 * torna alla Dashboard tramite il sistema di navigazione.
 *
 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;
import it.unisa.bibliotecajfx.model.biblioteca.Libro;
import it.unisa.bibliotecajfx.model.biblioteca.Lista_Libri;
import it.unisa.bibliotecajfx.model.prestiti.Prestito;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import navigazione.navigazione;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerGestioneLibri {

    /**
     * @brief Riferimento alla lista libri 
     */
    private Lista_Libri listalibri;

    /**
     * @brief Nome del file per i libri
     */
    final String FILE_LIBRI = "FileEsternoLibri.bin";

    /**
     * @brief Imposta la lista libri usata dal controller e inizializza i dati locali
     * Salva il riferimento e copia i libri nella lista osservabile locale (data).
     * @param listalibri 
     * @pre listalibri != null
     * @post Il controller può operare su ListaLibri e la lista osservabile data contiene i libri
     */
    public void setListaLibri(Lista_Libri listalibri) {
        this.listalibri = listalibri;
        data.setAll(listalibri.getLibri());
    }

    /**
     * @brief Pulsante per modificare il libro selezionato
     */
    @FXML
    private Button btnModificaLibro;

    /**
     * @brief Pulsante per rimuovere il libro selezionato
     */
    @FXML
    private Button btnRimuoviLibro;

    /**
     * @brief Pulsante per visualizzare la lista completa dei libri
     */
    @FXML
    private Button btnVisualizzaLibri;

    /**
     * @brief Pulsante per aprire la finestra di aggiunta libro
     */
    @FXML
    private Button btnAggiungiLibro;

    /**
     * @brief Campo di testo per la ricerca libro (titolo/autore/ISBN)
     */
    @FXML
    private TextField txtRicercaLibro;

    /**
     * @brief Tabella per la visualizzazione dei libri e/o risultati ricerca
     */
    @FXML
    private TableView<Libro> tabellaLibri;

    /**
     * @brief Colonna del titolo del libro
     */
    @FXML
    private TableColumn<Libro, String> colTitolo;

    /**
     * @brief Colonna dell'autore del libro
     */
    @FXML
    private TableColumn<Libro, String> colAutore;

    /**
     * @brief Colonna dell'ISBN del libro
     */
    @FXML
    private TableColumn<Libro, String> colISBN;

    /**
     * @brief Colonna dell'anno di pubblicazione del libro
     */
    @FXML
    private TableColumn<Libro, Integer> colAnno;

    /**
     * @brief Colonna del numero di copie disponibili del libro
     */
    @FXML
    private TableColumn<Libro, Integer> colCopie;

    /**
     * @brief Lista osservabile di supporto per i dati dei libri
     */
    private final ObservableList<Libro> data = FXCollections.observableArrayList();

    /**
     * @brief Pulsante per avviare la ricerca libro
     */
    @FXML
    private Button btnCercaLibro;

    /**
     * @brief Pulsante per resettare il filtro di ricerca libri
     */
    @FXML
    private Button btnResetFiltroLibri;

    /**
     * @brief Label per visualizzare il conteggio libri
     *
     * Nota: nel codice attuale non viene aggiornata automaticamente.
     */
    @FXML
    private Label lblConteggioLibri;

    /**
     * @brief Metodo di inizializzazione JavaFX 
     * Inizializza le colonne
     * e popola la tabella con i libri presenti nella lista.
     * @post Le colonne sono configurate e la tabella è pronta per mostrare i libri
     */
    @FXML
    private void initialize() {
        if (tabellaLibri == null || colTitolo == null || colISBN == null || colAutore == null || colAnno == null || colCopie == null) {
            System.out.println("ERRORE: qualche fx:id non combacia in ListaLibri.fxml");
            return;
        }

        inizializzaColonne();
        aggiornaTabella();
    }

    /**
     * @brief Inizializza le colonne della TableView
     * @post Ogni colonna mostra il dato corretto per ogni riga
     */
    private void inizializzaColonne() {
        colTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colAutore.setCellValueFactory(new PropertyValueFactory<>("autore"));
        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colAnno.setCellValueFactory(new PropertyValueFactory<>("anno"));
        colCopie.setCellValueFactory(new PropertyValueFactory<>("copie"));
    }

    /**
     * @brief Aggiorna i contenuti della tabella libri
     * @pre listalibri != null
     * @post La tabella riflette lo stato attuale della ListaLibri
     */
    private void aggiornaTabella() {
        if (tabellaLibri == null) return;
        if (listalibri == null) {
            return;
        }

        ObservableList<Libro> obs =
                FXCollections.observableArrayList(listalibri.getLibri());
        tabellaLibri.setItems(obs);
        tabellaLibri.refresh();
    }

    /**
     * @brief Apre la finestra di modifica del libro selezionato
     * Passa al controller:
     * - la stessa ListaLibri (modello condiviso)
     * - il libro selezionato
     * Dopo la chiusura della finestra aggiorna la tabella.
     * @param event 
     * @post Se la modifica è avvenuta, la tabella viene aggiornata
     */
    @FXML
    private void onModificaLibro(ActionEvent event) {
        Libro selezionato = tabellaLibri.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ModificaLibro.fxml")
            );
            Parent root = loader.load();

            ControllerModificaLibro controller = loader.getController();
            controller.setListaLibri(listalibri);
            controller.setLibro(selezionato);

            Stage stage = new Stage();
            stage.setTitle("Modifica il libro");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnModificaLibro.getScene().getWindow());
            stage.showAndWait();

            tabellaLibri.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Rimuove il libro selezionato dalla lista e salva su file
     * Rimuove il libro dalla ListaLibri, salva i dati su file e rimuove la riga dalla tabella
     * @param event 
     * @throws IOException errore durante operazioni di I/O (salvataggio)
     * @pre listalibri != null e deve esserci un libro selezionato
     * @post Se selezionato: libro rimosso e file aggiornato
     */
    @FXML
    private void onRimuoviLibro(ActionEvent event) throws IOException {
        Libro selezionato = tabellaLibri.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            return;
        }

        listalibri.rimuoviLibro(selezionato);
        listalibri.salvataggioLibri(FILE_LIBRI);
        tabellaLibri.getItems().remove(selezionato);
    }

    /**
     * @brief Torna alla Dashboard
     * Utilizza il sistema di navigazione per caricare la dashboard come vista iniziale.
     * @param event
     * @throws IOException errore nel caricamento dell'FXML
     * @post La dashboard viene visualizzata nel pannello principale
     */
    @FXML
    private void onBack(ActionEvent event) throws IOException {
        navigazione.loadInitial("Dashboard.fxml");
    }

    /**
     * @brief Apre la finestra con la lista completa dei libri
     *
     * Passa al controller la stessa ListaLibri
     * e mostra l'elenco completo dei libri.
     * Dopo la chiusura, aggiorna la tabella principale.
     * @param event 
     * @post La finestra viene mostrata in modalità modale e la tabella viene aggiornata alla chiusura
     */
    @FXML
    private void handleVisualizzaLista(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ListaLibri.fxml")
            );
            Parent root = loader.load();

            ControllerListaLibri c = loader.getController();
            c.setListaLibri(listalibri);
            c.mostraLibri(listalibri.getLibri());

            Stage stage = new Stage();
            stage.setTitle("Lista completa dei libri");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnVisualizzaLibri.getScene().getWindow());
            stage.showAndWait();

            tabellaLibri.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Apre la finestra per aggiungere un nuovo libro
     * Passa al controller la stessa istanza di ListaLibri.
     * @param event 
     * @post La finestra viene mostrata in modalità modale
     */
    @FXML
    private void handleAggiungiLibro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/AggiungiLibro.fxml")
            );
            Parent root = loader.load();

            ControllerAggiungiLibro c = loader.getController();
            c.setListaLibri(listalibri);

            Stage stage = new Stage();
            stage.setTitle("Aggiungi nuovo libro");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnAggiungiLibro.getScene().getWindow());
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Cerca libri per titolo, autore o ISBN
     * Esegue la ricerca nella ListaLibri tramite tre metodi:
     * cercaPerTitolo
     * cercaPerAutore
     * cercaPerISBN
     * @param event 
     * @pre listalibri != null
     * @post La tabella mostra i risultati della ricerca
     */
    @FXML
    private void handleCercaLibro(ActionEvent event) {
        String testo = txtRicercaLibro.getText();

        if (listalibri == null || tabellaLibri == null) {
            System.out.println("ListaLibri o tabellaLibri null");
            return;
        }

        if (testo == null || testo.trim().isEmpty()) {
            ControllerPopup.showError(tabellaLibri.getScene().getWindow(),
                    "Inserisci titolo, autore o ISBN prima di cercare.");
            return;
        }

        testo = testo.trim();
        List<Libro> risultati = new ArrayList<>();

        risultati.addAll(listalibri.cercaPerTitolo(testo));
        risultati.addAll(listalibri.cercaPerAutore(testo));
        risultati.addAll(listalibri.cercaPerISBN(testo));

        // rimuovo duplicati (stesso libro trovato più volte)
        Set<Libro> unici = new LinkedHashSet<>(risultati);
        risultati = new ArrayList<>(unici);

        System.out.println("Libri trovati: " + risultati.size());

        tabellaLibri.setItems(FXCollections.observableArrayList(risultati));
        tabellaLibri.refresh();
    }

    /**
     * @brief Reset del filtro di ricerca libri
     * @param event 
     * @post Campo ricerca vuoto e tabella senza risultati
     */
    @FXML
    private void handleResetFiltroLibri(ActionEvent event) {
        txtRicercaLibro.clear();
        tabellaLibri.getItems().clear();
    }
}
