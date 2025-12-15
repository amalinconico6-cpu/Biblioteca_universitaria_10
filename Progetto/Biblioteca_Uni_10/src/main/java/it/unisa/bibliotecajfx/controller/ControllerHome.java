/**
 * @file ControllerHome.java
 * @brief Controller JavaFX della schermata Home
 *
 * Questa classe gestisce la schermata principale dell'applicazione dopo il Portale.
 * Funziona come contenitore centrale (contentPane) in cui vengono caricate le finestre
 * principali (Dashboard, Gestione Libri, Gestione Prestiti, Gestione Utenti)
 * inizializza il pannello di navigazione (main pane),
 * carica la dashboard iniziale,
 * legge i dati da file all'avvio (utenti, libri, prestiti),
 * salva i dati su file alla chiusura della finestra.
 *
 * @author SAMUELE TORTORA
 */
package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.biblioteca.Lista_Libri;
import it.unisa.bibliotecajfx.model.prestiti.Lista_Prestiti;
import it.unisa.bibliotecajfx.model.utenti.Lista_Utenti;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import navigazione.navigazione;

public class ControllerHome {    
    
    /**
     * @brief Pulsante per aprire la schermata Gestione Libri
     */
    @FXML
    private Button btnGestioneLibri;

    /**
     * @brief Pulsante per aprire la schermata Gestione Prestiti
     */
    @FXML
    private Button btnGestionePrestiti;

    /**
     * @brief Pulsante per aprire la schermata Gestione Utenti
     */
    @FXML
    private Button btnGestioneUtenti;

    /**
     * @brief Pulsante per tornare alla schermata Portale
     */
    @FXML
    private Button btnPortale;

    /**
     * @brief Pannello centrale in cui vengono caricate le viste
     */
    @FXML
    private AnchorPane contentPane;

    /**
     * @brief Lista degli utenti gestita dall'applicazione
     */
    
     private final Lista_Utenti listaUtenti = new Lista_Utenti();

    /**
     * @brief Lista dei libri gestita dall'applicazione
     */
    private final Lista_Libri listaLibri = new Lista_Libri();

    /**
     * @brief Lista dei prestiti gestita dall'applicazione
     */
    private final Lista_Prestiti listaPrestiti = new Lista_Prestiti();
 
    /**
     * @brief Nome del file binario per gli utenti
     */
    
    private static final String FILE_UTENTI = "FileEsternoUtenti.bin";

    /**
     * @brief Nome del file binario per i libri
     */
    private static final String FILE_LIBRI = "FileEsternoLibri.bin";

    /**
     * @brief Nome del file binario per i prestiti
     */
    private static final String FILE_PRESTITI = "FileEsternoPrestiti.bin";

    /**
     * @brief Metodo di inizializzazione JavaFX 
     * @throws IOException Errore nel caricamento della vista iniziale (FXML)
     * @post La dashboard è caricata nel contentPane e i dati sono stati letti dai file
     */
    @FXML
    private void initialize() throws IOException {     
        // Dico al Navigator qual è il pannello principale
        navigazione.setMainPane(contentPane);

        // Carico la schermata iniziale (la dashboard) al centro
        navigazione.loadInitial("Dashboard.fxml");

        leggiTutto();

        Platform.runLater(() -> {
            Stage stage = (Stage) contentPane.getScene().getWindow();
            stage.setOnCloseRequest(e -> salvaTutto());
        });
    }

    /**
     * @brief Legge utenti, libri e prestiti dai file
     * Effettua la lettura dei dati dal file binario associato.
     * @post Le liste (utenti, libri, prestiti) risultano popolate se i file sono validi
     */
    private void leggiTutto() {
        try { listaUtenti.letturaUtenti(FILE_UTENTI); } catch (Exception ignored) {}
        try { listaLibri.letturaLibri(FILE_LIBRI); } catch (Exception ignored) {}
        try { listaPrestiti.letturaPrestiti(FILE_PRESTITI); } catch (Exception ignored) {}
    }

    /**
     * @brief Salva utenti, libri e prestiti sui file di persistenza
     * Effettua il salvataggio delle liste correnti su file binario.
     * @post I file contengono lo stato aggiornato delle liste
     */
    private void salvaTutto() {
        try { listaUtenti.salvataggioUtenti(FILE_UTENTI); } catch (Exception e) { e.printStackTrace(); }
        try { listaLibri.salvataggioLibri(FILE_LIBRI); } catch (Exception e) { e.printStackTrace(); }
        try { listaPrestiti.salvataggioPrestiti(FILE_PRESTITI); } catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * @brief Torna alla schermata Portale
     * Carica il file FXML del Portale e sostituisce la scena corrente dello Stage.
     * @throws IOException Errore nel caricamento del file FXML
     * @post La finestra mostra la schermata Portale
     */
    @FXML
    private void onBackToPortale() throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/Portale.fxml")
        );

        Stage stage = (Stage) btnPortale.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.setTitle("Biblioteca Universitaria 10 - Portale");
        stage.show();
    }

    /**
     * @brief Carica la schermata di Gestione Libri nel pannello centrale
     * Carica la vista GestioneLibri.fxml nel contentPane tramite il sistema
     * di navigazione e passa al controller la lista dei libri.
     * @throws IOException Errore nel caricamento dell'FXML
     * @post La vista Gestione Libri è visibile nel contentPane
     */
    @FXML
    private void onGestioneLibri() throws IOException {
        ControllerGestioneLibri controller =
                (ControllerGestioneLibri) navigazione.loadIntoMain("GestioneLibri.fxml");
        controller.setListaLibri(listaLibri);
    }

    /**
     * @brief Carica la schermata di Gestione Prestiti nel pannello centrale
     * @throws IOException Errore nel caricamento dell'FXML
     * @post La vista Gestione Prestiti è visibile nel contentPane
     */
    @FXML
    private void onGestionePrestiti() throws IOException {
        ControllerGestionePrestiti controller =
                (ControllerGestionePrestiti) navigazione.loadIntoMain("GestionePrestiti.fxml");
        controller.setListe(listaPrestiti, listaUtenti, listaLibri);
    }

    /**
     * @brief Carica la schermata di Gestione Utenti nel pannello centrale
     * @throws IOException Errore nel caricamento dell'FXML
     * @post La vista Gestione Utenti è visibile nel contentPane
     */
    
    @FXML
    private void onGestioneUtenti() throws IOException {
        ControllerGestioneUtenti controller =
                (ControllerGestioneUtenti) navigazione.loadIntoMain("GestioneUtenti.fxml");
        controller.setListaUtenti(listaUtenti);
    }
}



