
package it.unisa.bibliotecajfx.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * @file ControllerDashboard.java
 * @brief Controller JavaFX della schermata Dashboard
 * @author SAMUELE TORTORA
 */

public class ControllerDashboard {

    /**
     * @brief Pulsante per tornare alla schermata Portale
     */
    @FXML
    private Button btnPortale;

    /**
     * @brief Torna alla schermata Portale
     * Carica il file FXML del Portale e sostituisce
     * la scena corrente dello Stage con quella del Portale.
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
        stage.setTitle("Biblioteca Universitaria - Portale");
        stage.show();
    }
}
