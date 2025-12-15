/**
 * @file ControllerPopup.java
 * @brief Controller JavaFX per la visualizzazione di popup informativi (successo/errore).
 *
 * Questa classe gestisce la finestra grafica "PopupMessaggio.fxml".
 * Permette di mostrare un messaggio modale con:
 * icona (testuale) e colore diverso in base all'esito,
 * titolo del popup,
 * testo del messaggio,
 * pulsante OK per chiudere la finestra.
 *
 * La classe espone due metodi statici di utilità:
 * showSuccess(owner, messaggio)
 * showError(owner, messaggio)
 *
 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Window;


public class ControllerPopup {

    /**
     * @brief Label usata come icona del popup
     */
    @FXML
    private Label lblIcona;

    /**
     * @brief Label titolo del popup
     */
    @FXML
    private Label lblTitolo;

    /**
     * @brief Label del messaggio descrittivo da mostrare nel popup
     */
    @FXML
    private Label lblMessaggio;

    /**
     * @brief Pulsante OK per chiudere il popup
     */
    @FXML
    private Button btnOk;

    /**
     * @brief Configura il popup in modalità successo
     *
     * Imposta:
     * icona di conferma e colore verde,
     * titolo "Operazione completata",
     * testo del messaggio passato in input.
     *
     * @param messaggio 
     * @pre messaggio != null
     * @post Il popup risulta configurato per segnalare un successo
     */
    public void setSuccesso(String messaggio) {
        lblIcona.setText("?");
        lblIcona.setStyle("-fx-text-fill: #198754;");
        lblTitolo.setText("Operazione completata");
        lblMessaggio.setText(messaggio);
    }

    /**
     * @brief Configura il popup in modalità errore
     *
     * Imposta:
     * icona di errore e colore rosso,
     * titolo "Errore",
     * testo del messaggio passato in input.
     *
     * @param messaggio 
     * @pre messaggio != null
     * @post Il popup risulta configurato per segnalare un errore
     */
    public void setErrore(String messaggio) {
        lblIcona.setText("!");
        lblIcona.setStyle("-fx-text-fill: #dc3545;");
        lblTitolo.setText("Errore");
        lblMessaggio.setText(messaggio);
    }

    /**
     * @brief Gestisce il click sul pulsante OK
     *
     * Chiude la finestra del popup recuperando lo Stage dalla scena.
     */
    @FXML
    private void onOk() {
        Stage stage = (Stage) lblTitolo.getScene().getWindow();
        stage.close();
    }

    /**
     * @brief Mostra un popup di successo (metodo di utilità)
     *
     * @param owner 
     * @param messaggio 
     * @pre messaggio != null
     * @post Viene mostrata una finestra modale configurata come successo
     */
    public static void showSuccess(Window owner, String messaggio) {
        show(owner, true, messaggio);
    }

    /**
     * @brief Mostra un popup di errore (metodo di utilità)
     *
     * @param owner 
     * @param messaggio 
     * @pre messaggio != null
     * @post Viene mostrata una finestra modale configurata come errore
     */
    public static void showError(Window owner, String messaggio) {
        show(owner, false, messaggio);
    }

    /**
     * @brief Metodo interno per mostrare un popup modale configurato
     *
     * Carica l'FXML "PopupMessaggio.fxml", recupera il controller e imposta
     * la configurazione in base al parametro success.
     *
     * La finestra viene mostrata in modalità WINDOW_MODAL e, se owner è fornito,
     * viene impostato come proprietario della finestra.
     *
     * @param owner 
     * @param success 
     * @param messaggio 
     * @post Viene mostrato il popup e il metodo termina dopo la chiusura (showAndWait)
     */
    private static void show(Window owner, boolean success, String messaggio) {
        try {
            FXMLLoader loader = new FXMLLoader(ControllerPopup.class.getResource("/PopupMessaggio.fxml"));
            Parent root = loader.load();

            ControllerPopup controller = loader.getController();
            if (success) controller.setSuccesso(messaggio);
            else controller.setErrore(messaggio);

            Stage dialog = new Stage();
            dialog.setTitle(success ? "Messaggio" : "Errore");
            dialog.initModality(Modality.WINDOW_MODAL);
            if (owner != null) dialog.initOwner(owner);

            dialog.setScene(new Scene(root));
            dialog.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}