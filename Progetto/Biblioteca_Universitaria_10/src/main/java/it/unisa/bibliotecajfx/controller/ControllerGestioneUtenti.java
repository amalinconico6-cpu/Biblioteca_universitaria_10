

/**
 * @file ControllerGestioneUtenti.java
 * @brief Controller JavaFX per la gestione e la ricerca degli utenti.
 *
 * Questa classe gestisce la schermata di Gestione Utenti
 * visualizza utenti in una TableView,
 * cerca utenti per nome, cognome, matricola o email,
 * resetta il filtro di ricerca,
 * apre la finestra di aggiunta utente,
 * apre la finestra di visualizzazione lista utenti,
 * torna alla Dashboard
 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.utenti.Lista_Utenti;
import it.unisa.bibliotecajfx.model.utenti.Utente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import navigazione.navigazione;

public class ControllerGestioneUtenti {

    /**
     * @brief Campo di testo per la ricerca di un utente (nome/cognome/matricola/email)
     */
    @FXML
    private TextField txtRicercaUtente;

    /**
     * @brief Pulsante per avviare la ricerca utente
     */
    @FXML
    private Button btnCercaUtente;

    /**
     * @brief Pulsante per resettare il filtro utenti
     */
    
    @FXML
    private Button btnResetFiltroUtenti;

    /**
     * @brief Pulsante per aprire la finestra di visualizzazione completa utenti
     */
    @FXML
    private Button btnVisualizzaUtenti;

    /**
     * @brief Pulsante per aprire la finestra di aggiunta utente
     */
    @FXML
    private Button btnAggiungiUtente;

    /**
     * @brief Tabella per la visualizzazione dei risultati utenti
     */
    @FXML
    private TableView<Utente> tabellaUtenti;

    /**
     * @brief Colonna della matricola
     */
    @FXML
    private TableColumn<Utente, String> colMatricola;

    /**
     * @brief Colonna del nome
     */
    @FXML
    private TableColumn<Utente, String> colNome;

    /**
     * @brief Colonna del cognome
     */
    @FXML
    private TableColumn<Utente, String> colCognome;

    /**
     * @brief Colonna dell'email
     */
    @FXML
    private TableColumn<Utente, String> colEmail;

    /**
     * @brief Riferimento alla lista utenti (modello)
     */
    private Lista_Utenti listaUtenti;

    /**
     * @brief Imposta la lista utenti usata dal controller
     * @param listaUtenti 
     * @pre listaUtenti != null
     * @post Il controller può effettuare ricerche 
     */
    public void setListaUtenti(Lista_Utenti listaUtenti) {
        this.listaUtenti = listaUtenti;
    }

    /**
     * @brief Lista osservabile completa 
     */
    private ObservableList<Utente> masterData;

    /**
     * @brief Lista filtrata 
     */
    private FilteredList<Utente> filteredData;

    /**
     * @brief Metodo di inizializzazione JavaFX 
     * Collega le colonne della TableView ai campi della classe Utente.
     * @post La tabella è configurata per mostrare correttamente nome, cognome, matricola ed email
     */
    @FXML
    private void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colMatricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
    }

    /**
     * @brief Configura le colonne della tabella se presenti 
     * Esegue una configurazione alternativa basata sull'ordine delle colonne
     * presenti nella TableView. Utile in caso di colonne create via FXML con ordine noto.
     * @post Se la tabella contiene almeno 4 colonne, vengono configurate con i rispettivi campi di Utente
     */
    private void configuraColonneSePresenti() {
        if (tabellaUtenti != null && tabellaUtenti.getColumns().size() >= 4) {

            @SuppressWarnings("unchecked")
            TableColumn<Utente, String> c0 =
                    (TableColumn<Utente, String>) tabellaUtenti.getColumns().get(0);

            @SuppressWarnings("unchecked")
            TableColumn<Utente, String> c1 =
                    (TableColumn<Utente, String>) tabellaUtenti.getColumns().get(1);

            @SuppressWarnings("unchecked")
            TableColumn<Utente, String> c2 =
                    (TableColumn<Utente, String>) tabellaUtenti.getColumns().get(2);

            @SuppressWarnings("unchecked")
            TableColumn<Utente, String> c3 =
                    (TableColumn<Utente, String>) tabellaUtenti.getColumns().get(3);

            c0.setCellValueFactory(new PropertyValueFactory<>("nome"));
            c1.setCellValueFactory(new PropertyValueFactory<>("cognome"));
            c2.setCellValueFactory(new PropertyValueFactory<>("matricola"));
            c3.setCellValueFactory(new PropertyValueFactory<>("mail"));
        }
    }

    /**
     * @brief Torna alla Dashboard
     * @param event 
     * @throws IOException errore nel caricamento dell'FXML
     * @post La dashboard viene visualizzata nel pannello principale
     */
    @FXML
    private void onBack(ActionEvent event) throws IOException {
        navigazione.loadInitial("Dashboard.fxml");
    }

    /**
     * @brief Apre la finestra per aggiungere un nuovo utente
     * @param event evento generato dal click sul pulsante "Aggiungi Utente"
     */
    @FXML
    private void handleAggiungiUtente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AggiungiUtente.fxml"));
            Parent root = loader.load();

            ControllerAggiungiUtente c = loader.getController();
            c.setListaUtenti(listaUtenti);

            Stage dialog = new Stage();
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(btnAggiungiUtente.getScene().getWindow());
            dialog.setScene(new Scene(root));
            dialog.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Apre la finestra per visualizzare la lista completa degli utenti
     * @throws IOException errore nel caricamento dell'FXML
     * @post La finestra con la lista utenti viene mostrata in modalità modale
     */
    @FXML
    private void handleVisualizzaUtenti() throws IOException {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/ListaUtenti.fxml"));
        Parent root = loader.load();

        ControllerListaUtenti controller = loader.getController();
        controller.setListaUtenti(listaUtenti);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    /**
     * @brief Cerca utenti in base al testo inserito
     * Esegue una ricerca nella ListaUtenti confrontando con:
     * matricola
     * -ome, cognome ed email 
     * Se il campo è vuoto, mostra errore e pulisce la tabella.
     * Se non ci sono risultati, mostra errore e pulisce la tabella.
     * Se ci sono risultati, li mostra nella TableView.
     * @param event
     * @pre listaUtenti != null
     * @post La tabella mostra i risultati della ricerca oppure risulta vuota in caso di nessun match
     */
    @FXML
    private void handleCercaUtente(ActionEvent event) {
        String testo = txtRicercaUtente.getText();

        if (testo == null || testo.trim().isEmpty()) {
            ControllerPopup.showError(tabellaUtenti.getScene().getWindow(),
                    "Inserisci nome, cognome, matricola o email prima di cercare.");
            tabellaUtenti.getItems().clear();
            return;
        }

        testo = testo.trim();
        List<Utente> risultati = new ArrayList<>();

        for (Utente u : listaUtenti.getUtenti()) {
            if (u == null) continue;

            // matricola: confronto stringa-stringa
            if (u.getMatricola() != null && u.getMatricola().equals(testo)) {
                risultati.add(u);
                continue;
            }

            // nome/cognome/email
            if ((u.getNome() != null && u.getNome().equalsIgnoreCase(testo)) ||
                (u.getCognome() != null && u.getCognome().equalsIgnoreCase(testo)) ||
                (u.getMail() != null && u.getMail().equalsIgnoreCase(testo))) {
                risultati.add(u);
            }
        }

        if (risultati.isEmpty()) {
            tabellaUtenti.getItems().clear();
            ControllerPopup.showError(tabellaUtenti.getScene().getWindow(),
                    "Nessun utente trovato con il criterio inserito.");
            return;
        }

        tabellaUtenti.setItems(FXCollections.observableArrayList(risultati));
        tabellaUtenti.refresh();
    }

    /**
     * @brief Reset del filtro di ricerca utenti
     * Pulisce il campo di ricerca e svuota i risultati mostrati nella tabella.
     * @param event 
     * @post Campo ricerca vuoto e tabella senza risultati
     */
    @FXML
    private void handleResetFiltroUtenti(ActionEvent event) {
        txtRicercaUtente.clear();
        tabellaUtenti.getItems().clear();
    }
}
