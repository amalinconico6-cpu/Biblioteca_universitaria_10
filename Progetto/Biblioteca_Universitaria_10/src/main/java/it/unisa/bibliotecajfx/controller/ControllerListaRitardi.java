/**
 * @file ControllerListaRitardi.java
 * @brief Controller JavaFX per la gestione e visualizzazione dei prestiti in ritardo.
 *
 * Questa classe gestisce la schermata "Lista Ritardi".
 * Permette di:
 * visualizza i prestiti in ritardo (calcolati dalla ListaPrestiti),
 * ordina i ritardi per data di scadenza (più vecchi prima),
 * cerca un ritardo per matricola o ISBN,
 * resetta il filtro di ricerca,
 * modifica un prestito in ritardo (apertura finestra ModificaPrestito),
 * rimuove un prestito in ritardo e ripristinare le copie del libro.
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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerListaRitardi {

    /**
     * @brief Pulsante per chiudere la finestra
     */
    @FXML
    private Button btnChiudi;

    /**
     * @brief Campo testo per cercare un ritardo
     */
    @FXML
    private TextField txtRicercaRitardo;

    /**
     * @brief Pulsante per avviare la ricerca ritardo
     */
    @FXML
    private Button btnCercaRitardo;

    /**
     * @brief Pulsante per resettare il filtro dei ritardi
     */
    @FXML
    private Button btnResetRitardi;

    /**
     * @brief Tabella che mostra l'elenco dei prestiti in ritardo
     */
    @FXML
    private TableView<Prestito> tabellaRitardi;

    /**
     * @brief Colonna ID prestito
     */
    @FXML
    private TableColumn<Prestito, Integer> colID;

    /**
     * @brief Colonna ISBN del libro associato al prestito
     */
    @FXML
    private TableColumn<Prestito, String> colISBN;

    /**
     * @brief Colonna matricola dell'utente associato al prestito
     */
    @FXML
    private TableColumn<Prestito, String> colMatricola;

    /**
     * @brief Colonna data di scadenza del prestito
     */
    @FXML
    private TableColumn<Prestito, LocalDate> colDataScadenza;

    /**
     * @brief Pulsante per modificare il ritardo selezionato
     */
    @FXML
    private Button btnModificaRitardo;

    /**
     * @brief Pulsante per rimuovere il ritardo selezionato
     */
    @FXML
    private Button btnRimuoviRitardo;

    /**
     * @brief Modello: lista prestiti
     */
    private Lista_Prestiti listaPrestiti;

    /**
     * @brief Modello lista utenti 
     */
    private Lista_Utenti listaUtenti;

    /**
     * @brief Modello lista libri (necessaria per aggiornare copie quando si rimuove un prestito)
     */
    private Lista_Libri listaLibri;

    /**
     * @brief Imposta i modelli condivisi e aggiorna la tabella dei ritardi
     *
     * @param lp lista prestiti
     * @param lu lista utenti
     * @param ll lista libri
     * @pre lp != null && lu != null && ll != null
     * @post I modelli sono disponibili nel controller e la tabella viene popolata con i ritardi correnti
     */
    public void setListe(Lista_Prestiti lp, Lista_Utenti lu, Lista_Libri ll) {
        this.listaPrestiti = lp;
        this.listaUtenti = lu;
        this.listaLibri = ll;
        aggiornaTabella();
    }

    /**
     * @brief Metodo di inizializzazione JavaFX (richiamato automaticamente)
     *
     * Inizializza le colonne della TableView tramite property wrapper
     * (SimpleObjectProperty / SimpleStringProperty) coerenti con i getter della classe Prestito.
     * Imposta anche un placeholder visibile quando non ci sono ritardi.
     *
     * @post La tabella è configurata e mostra un placeholder se vuota
     */
    @FXML
    private void initialize() {
        colID.setCellValueFactory(cd -> new SimpleObjectProperty<>(cd.getValue().getID()));
        colISBN.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getISBN()));
        colMatricola.setCellValueFactory(cd -> new SimpleObjectProperty<>(cd.getValue().getMatricola()));
        colDataScadenza.setCellValueFactory(cd -> new SimpleObjectProperty<>(cd.getValue().getDataScadenza()));

        tabellaRitardi.setPlaceholder(new Label("Nessun ritardo presente."));
    }

    /**
     * @brief Aggiorna la tabella con i prestiti in ritardo
     *
     * Recupera la lista dei ritardi dal modello (listaPrestiti.aggiornaRitardi()),
     * ordina per data di scadenza crescente (più vecchie prima),
     * e popola la TableView.
     *
     * @pre listaPrestiti != null
     * @post tabellaRitardi contiene solo prestiti in ritardo, ordinati per scadenza
     */
    private void aggiornaTabella() {
        if (listaPrestiti == null || tabellaRitardi == null) return;

        List<Prestito> ritardi = listaPrestiti.aggiornaRitardi();
        ritardi.sort(Comparator.comparing(Prestito::getDataScadenza));

        tabellaRitardi.setItems(FXCollections.observableArrayList(ritardi));
        tabellaRitardi.refresh();
    }

    /**
     * @brief Chiude la finestra corrente
     *
     * Recupera lo Stage dalla scena associata alla tabella e chiude la finestra.
     */
    @FXML
    private void handleChiudi() {
        Stage stage = (Stage) tabellaRitardi.getScene().getWindow();
        stage.close();
    }

    /**
     * @brief Cerca tra i soli prestiti in ritardo
     *
     * Se il campo è vuoto, ricarica tutti i ritardi (aggiornaTabella()).
     * Se il testo è numerico, filtra per matricola esatta.
     * Se il testo non è numerico, filtra per ISBN (contains case-insensitive).
     *
     * @param event 
     * @pre listaPrestiti != null
     * @post tabellaRitardi contiene i soli ritardi che soddisfano il criterio
     */
    @FXML
    private void handleCercaRitardo(ActionEvent event) {
        if (listaPrestiti == null || tabellaRitardi == null) return;

        String testo = (txtRicercaRitardo.getText() == null) ? "" : txtRicercaRitardo.getText().trim();
        if (testo.isEmpty()) {
            aggiornaTabella();
            return;
        }

        List<Prestito> ritardi = listaPrestiti.aggiornaRitardi();
        List<Prestito> risultati = new java.util.ArrayList<>();

        try {
            int num = Integer.parseInt(testo);

            for (Prestito p : ritardi) {
                if (p.getMatricola() != null && p.getMatricola().equals(testo)) {
                    risultati.add(p);
                }
            }
        } catch (NumberFormatException ex) {
            for (Prestito p : ritardi) {
                if (p.getISBN() != null && p.getISBN().toLowerCase().contains(testo.toLowerCase())) {
                    risultati.add(p);
                }
            }
        }

        tabellaRitardi.setItems(FXCollections.observableArrayList(risultati));
        tabellaRitardi.refresh();
    }

    /**
     * @brief Reset del filtro ritardi
     *
     * Pulisce il campo di ricerca e svuota la tabella.
     *
     * @param event 
     * @post Campo ricerca vuoto e tabella senza righe
     */
    @FXML
    private void handleResetFiltroRitardi(ActionEvent event) {
        if (txtRicercaRitardo != null)
            txtRicercaRitardo.clear();
        tabellaRitardi.getItems().clear();
    }

    /**
     * @brief Apre la finestra per modificare il prestito in ritardo selezionato
     *
     * Carica ModificaPrestito.fxml, passa al controller:
     * le stesse istanze dei modelli (prestiti, utenti, libri)
     * il prestito selezionato da modificare
     *
     * @param event 
     * @post tabellaRitardi aggiornata in base allo stato attuale dei ritardi
     */
    @FXML
    private void onModificaRitardo(ActionEvent event) {
        Prestito selezionato = tabellaRitardi.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModificaPrestito.fxml"));
            Parent root = loader.load();

            ControllerModificaPrestito c = loader.getController();
            c.setListe(listaPrestiti, listaUtenti, listaLibri);
            c.setPrestitoDaModificare(selezionato);

            Stage dialog = new Stage();
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialog.setScene(new Scene(root));
            dialog.showAndWait();

            aggiornaTabella();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Aggiorna le copie di un libro dato il suo ISBN
     *
     *
     * @param isbn 
     * @param delta 
     * @return true se l'ISBN viene trovato e l'aggiornamento è possibile, false altrimenti
     * @pre listaLibri != null
     * @post Se true: copie del libro aggiornate
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
     * @brief Rimuove il prestito in ritardo selezionato e ripristina una copia del libro
     *
     * Rimuove il prestito dalla ListaPrestiti; se l'operazione va a buon fine:
     * incrementa le copie del libro associato (+1)
     * mostra un popup di successo
     * aggiorna la tabella dei ritardi
     *
     * In caso di errore mostra un popup di errore.
     *
     * @param event 
     * @post Se successo: prestito rimosso, copie ripristinate e tabella aggiornata
     */
    @FXML
    private void onRimuoviRitardo(ActionEvent event) {
        Prestito selezionato = tabellaRitardi.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            ControllerPopup.showError(tabellaRitardi.getScene().getWindow(),
                    "Seleziona un ritardo da rimuovere.");
            return;
        }
        
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Conferma eliminazione");
    alert.setHeaderText(null);
    alert.setContentText(
    "Sei sicuro di voler rimuovere il ritardo: " + selezionato.getID() + " ?"
);
    
    Optional<ButtonType> res = alert.showAndWait();
    if (!res.isPresent() || res.get() != ButtonType.OK) return;

        boolean ok = listaPrestiti.rimuoviPrestito(selezionato);

        if (ok) {
            cambiaCopieLibro(selezionato.getISBN(), +1);

            ControllerPopup.showSuccess(tabellaRitardi.getScene().getWindow(),
                    "Ritardo rimosso. Copie aggiornate.");
            aggiornaTabella();
        } else {
            ControllerPopup.showError(tabellaRitardi.getScene().getWindow(),
                    "Errore nella rimozione del ritardo.");
        }
    }
}