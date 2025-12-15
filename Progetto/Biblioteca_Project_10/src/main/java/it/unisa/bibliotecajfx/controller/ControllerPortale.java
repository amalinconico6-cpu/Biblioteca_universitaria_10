/**
 * @file ControllerPortale.java
 * @brief Controller JavaFX della schermata Portale
 * @author SAMUELE TORTORA
 */
package it.unisa.bibliotecajfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;


public class ControllerPortale {

    /**
     * @brief Pulsante per entrare nell'applicazione
     */
    @FXML
    private Button btnEntra;

    /**
     * @brief Metodo di inizializzazione JavaFX
     */
    @FXML
    private void initialize() {
        // Nessuna inizializzazione necessaria
    }

    /**
     * @brief Gestisce l'accesso alla Home dell'applicazione
     * @param event 
     * @pre event != null
     * @post La finestra Home è visibile e la finestra Portale è chiusa
     */
    @FXML
    private void handleEntra(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/Home.fxml")
            );

            Stage homeStage = new Stage();
            homeStage.setTitle("Biblioteca Universitaria - Home");
            homeStage.setScene(new Scene(root));
            homeStage.show();

            Stage currentStage =
                    (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
