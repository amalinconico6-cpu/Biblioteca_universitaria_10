
package it.unisa.bibliotecajfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author HOME
 */
public class Main extends Application{

@Override
public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(
            getClass().getResource("/Portale.fxml")
    );

    Scene scene = new Scene(root);
    stage.setTitle("Biblioteca Universitaria 10");
    stage.setScene(scene);
    stage.show();
}

    public static void main(String[] args) {
            launch(args);
        }
}
