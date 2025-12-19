/**
 * @file ControllerListaUtenti.java
 * @brief Controller JavaFX per la visualizzazione e gestione della lista utenti.
 *
 * Questa classe gestisce la finestra "Lista Utenti".
 * visualizza gli utenti in una TableView,
 * modifica l'utente selezionato (apertura finestra ModificaUtente),
 * rimuove l'utente selezionato e salvare su file,
 * chiude la finestra.
 *
 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.utenti.Lista_Utenti;
import it.unisa.bibliotecajfx.model.utenti.Utente;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControllerListaUtenti {

    /**
     * @brief Nome del file binario per la persistenza degli utenti
     */
    final String FILE_UTENTI = "FileEsternoUtenti.bin";

    /**
     * @brief Pulsante per aprire la finestra di modifica utente
     */
    @FXML
    private Button btnModificaUtente;

    /**
     * @brief Pulsante per rimuovere l'utente selezionato
     */
    @FXML
    private Button btnRimuoviUtente;

    /**
     * @brief Pulsante per chiudere la finestra
     */
    @FXML
    private Button btnChiudi;

    /**
     * @brief Tabella per la visualizzazione degli utenti
     */
    @FXML
    private TableView<Utente> tabellaUtenti;

    /**
     * @brief Modello lista utenti
     */
    private Lista_Utenti listaUtenti;

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
     * @brief Colonna della matricola
     */
    @FXML
    private TableColumn<Utente, String> colMatricola;

    /**
     * @brief Colonna dell'email
     */
    @FXML
    private TableColumn<Utente, String> colEmail;

    /**
     * @brief Imposta la lista utenti e popola la tabella
     *
     * Carica gli utenti del modello nella TableView e forza un refresh.
     *
     * @param listaUtenti 
     * @pre listaUtenti != null
     * @post tabellaUtenti contiene gli utenti presenti nel modello
     */
    public void setListaUtenti(Lista_Utenti listaUtenti) {
        this.listaUtenti = listaUtenti;
        tabellaUtenti.setItems(FXCollections.observableArrayList(listaUtenti.getUtenti()));
        tabellaUtenti.refresh();
    }

    /**
     * @brief Lista osservabile di supporto 
     */
    private ObservableList<Utente> data;


    /**
     * @brief Metodo di inizializzazione JavaFX 
     *
     * Verifica la corretta iniezione degli fx:id e configura le colonne della tabella.
     *
     * @post Le colonne sono configurate per mostrare i dati dell'utente
     */
    @FXML
    private void initialize() {
        if (tabellaUtenti == null || colNome == null || colCognome == null || colMatricola == null || colEmail == null) {
            System.out.println("ERRORE: fx:id NON combaciano in ListaPrestiti.fxml");
            return;
        }

        configuraColonneSePresenti();
    }

    /**
     * @brief Configura le colonne della tabella se presenti
     *
     * Esegue una configurazione basata sull'ordine delle colonne presenti nella TableView.
     * Collega le colonne ai campi del modello Utente (nome, cognome, matricola, mail).
     *
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
            TableColumn<Utente, Integer> c2 =
                    (TableColumn<Utente, Integer>) tabellaUtenti.getColumns().get(2);

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
     * @brief Apre la finestra di modifica dell'utente selezionato
     *
     * Carica ModificaUtente.fxml in una finestra modale, passa al controller:
     * la stessa ListaUtenti (modello condiviso)
     * l'utente selezionato
     * @param event 
     * @post Se l'utente viene modificato, la tabella viene aggiornata
     */
    @FXML
    private void onModificaUtente(ActionEvent event) {
        Utente selezionato = tabellaUtenti.getSelectionModel().getSelectedItem();
        if (selezionato == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModificaUtente.fxml"));
            Parent root = loader.load();

            ControllerModificaUtente controller = loader.getController();
            controller.setListaUtenti(listaUtenti);
            controller.setUtente(selezionato);

            Stage stage = new Stage();
            stage.setTitle("Modifica l'utente");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnModificaUtente.getScene().getWindow());
            stage.showAndWait();

            tabellaUtenti.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Rimuove l'utente selezionato e salva su file
     *
     * Chiede al modello di rimuovere l'utente. Se l'esito indica rimozione avvenuta:
     * Salva la lista utenti su file
     * Rimuove la riga dalla tabella
     * Mostra popup di successo
     *
     * In caso contrario mostra popup di errore.
     *
     * @param event 
     * @throws IOException errore durante operazioni di I/O (salvataggio)
     * @pre listaUtenti != null e deve esserci un utente selezionato
     * @post Se rimozione riuscita: file aggiornato e tabella aggiornata
     */
    @FXML
    private void onRimuoviUtente(ActionEvent event) throws IOException {
        Utente selezionato = tabellaUtenti.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
         ControllerPopup.showError(tabellaUtenti.getScene().getWindow(), "Seleziona un utente.");
        return;
    }

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Conferma eliminazione");
    alert.setHeaderText(null);
    alert.setContentText(
    "Sei sicuro di voler rimuovere l'utente: " + selezionato.getNome() + " ?"
);
        Optional<ButtonType> res = alert.showAndWait();
    if (!res.isPresent() || res.get() != ButtonType.OK) return;
        
        
        String esito = listaUtenti.rimuoviUtente(selezionato);
        if ("Utente rimosso correttamente".equals(esito) || esito.toLowerCase().contains("rimosso")) {
            listaUtenti.salvataggioUtenti(FILE_UTENTI);
            tabellaUtenti.getItems().remove(selezionato);
            ControllerPopup.showSuccess(tabellaUtenti.getScene().getWindow(), esito);
        } else {
            ControllerPopup.showError(tabellaUtenti.getScene().getWindow(), esito);
        }
    }

    /**
     * @brief Chiude la finestra corrente
     *
     * Recupera lo Stage dalla scena associata alla tabella e chiude la finestra.
     */
    @FXML
    private void handleChiudi() {
        Stage stage = (Stage) tabellaUtenti.getScene().getWindow();
        stage.close();
    }
}