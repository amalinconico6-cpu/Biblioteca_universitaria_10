/**
 * @file ControllerGestionePrestiti.java
 * @brief Controller JavaFX per la gestione dei prestiti.
 *
 * Questa classe gestisce la schermata "Gestione Prestiti".
 * visualizza i prestiti nella TableView,
 * cerca prestiti per matricola o ISBN,
 * resetta il filtro di ricerca,
 * apre la finestra con la lista completa dei prestiti,
 * apre la finestra di aggiunta prestito,
 * apre la finestra di visualizzazione dei ritardi,
 * torna alla Dashboard tramite il sistema di navigazione.
 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.biblioteca.Lista_Libri;
import it.unisa.bibliotecajfx.model.prestiti.Lista_Prestiti;
import it.unisa.bibliotecajfx.model.utenti.Lista_Utenti;
import it.unisa.bibliotecajfx.model.prestiti.Prestito;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import navigazione.navigazione;


public class ControllerGestionePrestiti {

    /**
     * @brief Pulsante per visualizzare la lista completa dei prestiti
     */
    @FXML
    private Button btnVisualizzaPrestiti;

    /**
     * @brief Pulsante per aprire la finestra di aggiunta prestito
     */
    @FXML
    private Button btnAggiungiPrestito;

    /**
     * @brief Campo di testo per la ricerca prestito (matricola o ISBN)
     */
    @FXML
    private TextField txtRicercaPrestito;

    /**
     * @brief Pulsante per aprire la lista dei ritardi
     */
    @FXML
    private Button btnVisualizzaRitardi;

    /**
     * @brief Pulsante per avviare la ricerca prestito
     */
    @FXML
    private Button btnCercaPrestito;

    /**
     * @brief Pulsante per resettare il filtro prestiti
     */
    @FXML
    private Button btnResetFiltroPrestiti;

    /**
     * @brief Tabella per la visualizzazione dei prestiti e/o risultati ricerca
     */
    @FXML
    private TableView<Prestito> tabellaPrestiti;

    /**
     * @brief Colonna matricola dell'utente associato al prestito
     */
    @FXML
    private TableColumn<Prestito, String> colMatricola;

    /**
     * @brief Colonna ID del prestito
     */
    @FXML
    private TableColumn<Prestito, String> colID;

    /**
     * @brief Colonna ISBN del libro associato al prestito
     */
    @FXML
    private TableColumn<Prestito, String> colISBN;

    /**
     * @brief Colonna della data di scadenza del prestito
     */
    @FXML
    private TableColumn<Prestito, LocalDate> colDataScadenza;

    /**
     * @brief Modello: lista prestiti
     */
    private Lista_Prestiti listaPrestiti;

    /**
     * @brief Modello lista utenti (necessaria per passaggio dati ad altre viste)
     */
    private Lista_Utenti listaUtenti;

    /**
     * @brief Modello lista libri (necessaria per passaggio dati ad altre viste)
     */
    private Lista_Libri listaLibri;

    /**
     * @brief Imposta la lista prestiti e aggiorna la tabella
     * @param listaPrestiti 
     * @pre listaPrestiti != null
     * @post tabellaPrestiti aggiornata con i prestiti correnti
     */
    public void setListaPrestiti(Lista_Prestiti listaPrestiti) {
        this.listaPrestiti = listaPrestiti;
        aggiornaTabella();
    }

    /**
     * @brief Imposta tutte le liste necessarie al controller
     *
     * @param lp lista dei prestiti
     * @param lu lista degli utenti
     * @param ll lista dei libri
     * @pre lp != null && lu != null && ll != null
     * @post Il controller dispone dei modelli condivisi per aprire le viste collegate
     */
    public void setListe(Lista_Prestiti lp, Lista_Utenti lu, Lista_Libri ll) {
        this.listaPrestiti = lp;
        this.listaUtenti = lu;
        this.listaLibri = ll;
    }

    /**
     * @brief Metodo di inizializzazione JavaFX 
     * @post Colonne inizializzate e tabella pronta a mostrare i prestiti
     */
    @FXML
    private void initialize() {

        if (tabellaPrestiti == null || colID == null || colISBN == null || colMatricola == null || colDataScadenza == null) {
            System.out.println("ERRORE: qualche fx:id non combacia in ListaPrestiti.fxml");
            return;
        }

        inizializzaColonne();
        aggiornaTabella();
    }

    /**
     * @brief Inizializza le colonne della TableView prestiti
     * @post Ogni colonna mostra il dato corretto per ogni riga
     */
    private void inizializzaColonne() {
        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colMatricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));
        colDataScadenza.setCellValueFactory(new PropertyValueFactory<>("dataScadenza"));
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    }

    /**
     * @brief Aggiorna i contenuti della tabella prestiti
     *
     * Ricarica la lista dei prestiti dal modello e aggiorna la TableView.
     *
     * @pre listaPrestiti != null
     * @post La tabella riflette lo stato attuale della ListaPrestiti
     */
    private void aggiornaTabella() {
        if (tabellaPrestiti == null) return;
        if (listaPrestiti == null) {
            return;
        }

        ObservableList<Prestito> obs =
                FXCollections.observableArrayList(listaPrestiti.getPrestiti());
        tabellaPrestiti.setItems(obs);
        tabellaPrestiti.refresh();
    }

    /**
     * @brief Torna alla Dashboard
     *
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
     * @brief Apre la finestra con la lista completa dei prestiti
     * @param event 
     * @throws IOException errore nel caricamento dell'FXML
     * @post La finestra viene mostrata in modalità modale
     */
    @FXML
    private void handleVisualizzaListaPrestiti(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListaPrestiti.fxml"));
        Parent root = loader.load();

        ControllerListaPrestiti c = loader.getController();
        c.setListe(listaPrestiti, listaUtenti, listaLibri);

        Stage dialog = new Stage();
        dialog.setScene(new Scene(root));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }

    /**
     * @brief Apre la finestra per aggiungere un nuovo prestito
     * @param e 
     * @post La finestra viene mostrata e alla chiusura eventuali modifiche sono già nel modello condiviso
     */
    @FXML
    private void handleAggiungiPrestito(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/AggiungiPrestito.fxml")
            );
            Parent root = loader.load();

            ControllerAggiungiPrestito c = loader.getController();
            c.setListe(listaPrestiti, listaUtenti, listaLibri);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @brief Apre la finestra con la lista dei ritardi
     *
     * Carica ListaRitardi.fxml e passa al controller le stesse istanze dei modelli.
     * La finestra viene mostrata con modalità WINDOW_MODAL.
     *
     * @param event 
     * @post La finestra dei ritardi viene mostrata
     */
    @FXML
    private void handleVisualizzaRitardi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ListaRitardi.fxml")
            );
            Parent root = loader.load();

            ControllerListaRitardi c = loader.getController();
            c.setListe(listaPrestiti, listaUtenti, listaLibri);

            Stage stage = new Stage();
            stage.setTitle("Lista completa dei ritardi");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnVisualizzaRitardi.getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Cerca prestiti per matricola o ISBN
     *
     * Esegue la ricerca nella ListaPrestiti tramite:
     * cercaPrestitoPerMatricola
     * cercaPerISBN
     * @param event 
     * @pre listaPrestiti != null
     * @post La tabella mostra i risultati della ricerca oppure viene svuotata se non ci sono match
     */
    @FXML
    private void handleCercaPrestito(ActionEvent event) {
        String testo = txtRicercaPrestito.getText();

        if (listaPrestiti == null || tabellaPrestiti == null) {
            System.out.println("Lista o tabella null");
            return;
        }

        if (testo == null || testo.trim().isEmpty()) {
            ControllerPopup.showError(tabellaPrestiti.getScene().getWindow(),
                    "Inserisci una matricola o un ISBN prima di cercare.");
            return;
        }

        testo = testo.trim();
        List<Prestito> risultati = new ArrayList<>();

        risultati.addAll(listaPrestiti.cercaPrestitoPerMatricola(testo));
        risultati.addAll(listaPrestiti.cercaPerISBN(testo));

        Set<Prestito> unici = new LinkedHashSet<>(risultati);
        risultati = new ArrayList<>(unici);

        if (risultati.isEmpty()) {
            tabellaPrestiti.getItems().clear();
            ControllerPopup.showError(tabellaPrestiti.getScene().getWindow(),
                    "Nessun utente trovato con il criterio inserito.");
            return;
        }

        tabellaPrestiti.setItems(FXCollections.observableArrayList(risultati));
        tabellaPrestiti.refresh();
    }

    /**
     * @brief Reset del filtro di ricerca prestiti
     *
     * Pulisce il campo di ricerca e svuota i risultati mostrati nella tabella.
     *
     * @param event 
     * @post Campo ricerca vuoto e tabella senza risultati
     */
    @FXML
    private void handleResetFiltroPrestiti(ActionEvent event) {
        txtRicercaPrestito.clear();
        tabellaPrestiti.getItems().clear();
    }
}