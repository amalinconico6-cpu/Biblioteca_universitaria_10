


package navigazione;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class navigazione {

    private static AnchorPane mainPane;             
    private static final Deque<String> history = new ArrayDeque<>();
    private static String currentView = null;

    public static void setMainPane(AnchorPane pane) {
        mainPane = pane;
    }

    public static void loadInitial(String fxml) throws IOException {
        history.clear();
        currentView = fxml;
        loadIntoMain(fxml);
    }

   public static Object navigateTo(String fxml) throws IOException {
    if (currentView != null) {
        history.push(currentView);
    }
    currentView = fxml;
    return loadIntoMain(fxml);
}


    public static void goBack() throws IOException {
        if (history.isEmpty()) {
            return; 
        }
        String previous = history.pop();
        currentView = previous;
        loadIntoMain(previous);
    }

    public static Object loadIntoMain(String fxml) throws IOException {
    FXMLLoader loader = new FXMLLoader(navigazione.class.getResource("/" + fxml));
    Parent root = loader.load();
    mainPane.getChildren().setAll(root);
    AnchorPane.setTopAnchor(root, 0.0);
    AnchorPane.setRightAnchor(root, 0.0);
    AnchorPane.setBottomAnchor(root, 0.0);
    AnchorPane.setLeftAnchor(root, 0.0);
    return loader.getController();
}

}
