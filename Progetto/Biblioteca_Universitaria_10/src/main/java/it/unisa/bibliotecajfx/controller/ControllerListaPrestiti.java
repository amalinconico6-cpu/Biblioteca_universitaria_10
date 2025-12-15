
/**
 * @file ControllerListaPrestiti.java
 * @brief Controller JavaFX per gestione e visualizzazione dei prestiti
 *
 * Questa classe gestisce la schermata che mostra la lista dei prestiti.
 * Inizializza le colonne della TableView,
 * Aggiorna la tabella con i prestiti presenti nel modello,
 * Apre la finestra di modifica del prestito selezionato,
 * Rimuove un prestito e Aggiorna le copie del libro,
 * Salva su file le modifiche
 * Chiude la finestra corrente.
 *
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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerListaPrestiti {

    /**
     * @brief Nome del file per i  prestiti
     */
    final String FILE_PRESTITI = "FileEsternoPrestiti.bin";

    /**
     * @brief Nome del file per i libri
     */
    final String FILE_LIBRI = "FileEsternoLibri.bin";

    /**
     * @brief Tabella che mostra i prestiti
     */
    @FXML
    private TableView<Prestito> tabellaPrestiti;

    /**
     * @brief Pulsante per modificare il prestito selezionato
     */
    @FXML
    private Button btnModificaPrestito;

    /**
     * @brief Pulsante per rimuovere il prestito selezionato
     */
    @FXML
    private Button btnRimuoviPrestito;

    /**
     * @brief Pulsante per chiudere la finestra
     */
    @FXML
    private Button btnChiudi;

    /**
     * @brief Colonna ID del prestito
     */
    @FXML
    private TableColumn<Prestito, Integer> colID;

    /**
     * @brief Colonna ISBN del libro associato al prestito
     */
    @FXML
    private TableColumn<Prestito, String> colISBN;

    /**
     * @brief Colonna Matricola dell'utente associato al prestito
     */
    @FXML
    private TableColumn<Prestito, String> colMatricola;

    /**
     * @brief Colonna data di scadenza del prestito
     */
    @FXML
    private TableColumn<Prestito, LocalDate> colDataScadenza;


    /**
     * @brief Riferimento alla lista prestiti 
     */
    private Lista_Prestiti listaPrestiti;

    /**
     * @brief Riferimento alla lista libri
     */
    private Lista_Libri listaLibri;

    /**
     * @brief Riferimento alla lista utenti
     */
    private Lista_Utenti listaUtenti;

    /**
     * @brief Imposta i riferimenti ai modelli necessari alla schermata
     *
     * Assegna al controller le liste di prestiti, utenti e libri.
     * Riempie la TableView con i prestiti presenti e forza un refresh.
     * 
     *
     * @param lp Lista_Prestiti da visualizzare/gestire
     * @param lu Lista_Utenti usata per controlli o modifiche
     * @param ll ListaLibri usata per aggiornare le copie dei libri
     * @pre lp != null && lu != null && ll != null
     * @post La tabella contiene i prestiti presenti in lp
     */
    public void setListe(Lista_Prestiti lp, Lista_Utenti lu, Lista_Libri ll) {
        this.listaPrestiti = lp;
        this.listaUtenti = lu;
        this.listaLibri = ll;

        tabellaPrestiti.setItems(FXCollections.observableArrayList(listaPrestiti.getPrestiti()));
        tabellaPrestiti.refresh();
    }

    /**
     * @brief Metodo di inizializzazione 
     * @post Le colonne della tabella sono collegate ai campi del Prestito
     */
    @FXML
    private void initialize() {

        if (tabellaPrestiti == null || colID == null || colISBN == null || colMatricola == null || colDataScadenza == null) {
            System.out.println("ERRORE: fx:id NON combaciano in ListaPrestiti.fxml");
            return;
        }

        inizializzaColonne();
    }

    /**
     * @brief Inizializza le colonne della TableView
     * Associa ogni colonna ai valori dell'oggetto Prestito.
     * @post Ogni colonna mostra il dato corretto per ogni riga
     */
    private void inizializzaColonne() {
        colID.setCellValueFactory(cd -> new SimpleObjectProperty<>(cd.getValue().getID()));
        colISBN.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getISBN()));
        colMatricola.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getMatricola()));
        colDataScadenza.setCellValueFactory(cd -> new SimpleObjectProperty<>(cd.getValue().getDataScadenza()));
    }

    /**
     * @brief Aggiorna la tabella dei prestiti
     * @pre listaPrestiti != null
     * @post La tabella rispecchia la lista prestiti
     */
    private void aggiornaTabella() {
        if (listaPrestiti == null) return;
        tabellaPrestiti.setItems(
                FXCollections.observableArrayList(listaPrestiti.getPrestiti())
        );
        tabellaPrestiti.refresh();
    }

    /**
     * @brief Gestisce la modifica di un prestito selezionato
     *
     * Apre una finestra modale di modifica (ModificaPrestito.fxml),
     * passa al controller di modifica le liste e il prestito selezionato.
     * Dopo la chiusura della finestra, aggiorna la tabella per mostrare
     * eventuali cambiamenti.
     * @param event 
     * @throws IOException Errore nel caricamento dell'FXML
     * @pre Deve essere selezionato un prestito nella tabella
     * @post La tabella viene aggiornata
     */
    @FXML
    private void onModificaPrestito(ActionEvent event) throws IOException {
        Prestito selezionato = tabellaPrestiti.getSelectionModel().getSelectedItem();

        if (selezionato == null) {
            ControllerPopup.showError(tabellaPrestiti.getScene().getWindow(),
                    "Seleziona un prestito da modificare.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/ModificaPrestito.fxml")
        );
        Parent root = loader.load();

        ControllerModificaPrestito controller = loader.getController();
        controller.setListe(listaPrestiti, listaUtenti, listaLibri);
        controller.setPrestitoDaModificare(selezionato);

        Stage dialog = new Stage();
        dialog.setTitle("Modifica Prestito");
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(tabellaPrestiti.getScene().getWindow());
        dialog.setScene(new Scene(root));

        dialog.showAndWait();

        aggiornaTabella();
        tabellaPrestiti.refresh();
    }

    /**
     * @brief Aggiorna il numero di copie di un libro dato l'ISBN
     * Cerca il libro nella listaLibri e modifica il numero di copie
     * @param isbn ISBN del libro da aggiornare
     * @param delta Variazione da applicare alle copie (positiva o negativa)
     * @return true se l'aggiornamento è avvenuto correttamente, false altrimenti
     * @pre listaLibri != null && isbn != null
     * @post Se ritorna true, le copie del libro sono aggiornate
     */
    private boolean cambiaCopieLibro(String isbn, int delta) {
        if (listaLibri == null || isbn == null) return false;

        for (Libro l : listaLibri.getLibri()) {
            if (l != null && l.getISBN() != null && l.getISBN().equals(isbn)) {

                if (delta < 0 && l.getCopie() < (-delta)) {
                    return false;
                }

                l.setCopie(l.getCopie() + delta);
                return true;
            }
        }
        return false;
    }

    /**
     * @brief Gestisce la rimozione del prestito selezionato
     *
     * Procedura:
     * 1) Controlla che un prestito sia selezionato
     * 2) Aggiorna la copia sul libro associato (+1 copie)
     * 3) Rimuove il prestito dalla listaPrestiti
     * 4) Salva prestiti e libri su file
     * 5) Aggiorna la tabella
     * @param event 
     * @throws IOException Errore durante il salvataggio su file
     * @pre Deve essere selezionato un prestito nella tabella
     * @post Se la rimozione va a buon fine il prestito non esiste più e le copie sono aggiornate
     */
    @FXML
    private void onRimuoviPrestito(ActionEvent event) throws IOException {
        Prestito p = tabellaPrestiti.getSelectionModel().getSelectedItem();
        if (p == null) {
            ControllerPopup.showError(tabellaPrestiti.getScene().getWindow(),
                    "Seleziona un prestito.");
            return;
        }

        boolean okCopia = cambiaCopieLibro(p.getISBN(), +1);
        if (!okCopia) {
            ControllerPopup.showError(tabellaPrestiti.getScene().getWindow(),
                    "ISBN del prestito non trovato nei libri.");
            return;
        }

        boolean ok = listaPrestiti.rimuoviPrestito(p);

        if (ok) {
            listaPrestiti.salvataggioPrestiti(FILE_PRESTITI);
            listaLibri.salvataggioLibri(FILE_LIBRI);

            ControllerPopup.showSuccess(tabellaPrestiti.getScene().getWindow(),
                    "Prestito rimosso.");
            aggiornaTabella();
        } else {
            cambiaCopieLibro(p.getISBN(), -1);
            ControllerPopup.showError(tabellaPrestiti.getScene().getWindow(),
                    "Errore nella rimozione.");
        }
    }

    /**
     * @brief Chiude la finestra corrente
     */
    @FXML
    private void handleChiudi() {
        Stage stage = (Stage) tabellaPrestiti.getScene().getWindow();
        stage.close();
    }
}
